package nanonout.ioc;

import nanonout.ioc.container.AppContainer;
import nanonout.ioc.container.Descriptor;
import nanonout.ioc.container.Scope;

import java.util.HashMap;

public class ServiceCollection implements IServiceCollection {
    private final AppContainer container;

    public AppContainer getContainer() {
        return container;
    }

    public ServiceCollection() {
        container = new AppContainer();

    }

    @Override
    public <TSource> void addTransient(Class<TSource> source, Class<? extends TSource> target) {
        container.addTransient(source, target);
    }

    @Override
    public <TSource> void addSingleton(Class<TSource> source, Class<? extends TSource> target) {
        container.addSington(source, target);
    }

    public <T> T resolve(Class<T> source) {
        return container.resolve(source);
    }

    @Override
    public <TSource> void AddRequestScope(Class<TSource> source, Class<? extends TSource> target) {
        container.addScope(source, target);
    }

    @Override
    public <TSource> void register(Class<TSource> source, Scope scope) {
        container.register(new Descriptor(source,source,scope));
    }
}