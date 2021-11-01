package nanonout.ioc.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

public class AppContainer {
    public final HashMap<Class, Descriptor> tank = new HashMap<>();

    public <Source> void addTransient(Class<Source> source, Class<? extends Source> target) {
        validate(target);
        register(new Descriptor(source, target, Scope.Transient));
    }

    public <Source> void addSington(Class<Source> source, Class<? extends Source> target) {
        validate(target);
        register(new Descriptor(source, target, Scope.Singleton));
    }

    private void validate(Class target) {
        if (Modifier.isAbstract(target.getModifiers())) {
            throw new RuntimeException("Invalid type to register");
        }
    }

    public <Source> void addScope(Class<Source> source, Class<? extends Source> target) {
        validate(target);
        register(new Descriptor(source, target, Scope.RequestScope));
    }

    public <Source> void register(Descriptor descriptor) {
        validate(descriptor.getImplemetor());
        tank.put(descriptor.getSource(), descriptor);
    }

    public <T> T resolve(Class<T> source) {
        if (tank.containsKey(source)) {
            Descriptor discripter = tank.get(source);
            return source.cast(createInstance(discripter));
        } else {
            throw new RuntimeException("Type Not regitser in container");
        }
    }

    public Object createInstance(Descriptor discripter) {

        Optional<Constructor<?>> constructor = Arrays.stream(discripter.getImplemetor().getConstructors())
                .max(Comparator.comparingInt(Constructor::getParameterCount));

        if (!constructor.isPresent()) {
            Object o = null;
            try {
                o = discripter.getImplemetor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return o;
        }
        Class[] typeParameters = constructor.get().getParameterTypes();
        Object[] paramerters = Arrays.stream(typeParameters).map(y -> resolve(y)).toArray();

        try {
            Object o = discripter.getImplemetor()
                    .getConstructor(typeParameters)
                    .newInstance(paramerters);
            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }


}
