package nanonout.ioc;

import java.util.HashMap;

public class ServiceCollection  implements IServiceCollection{
    private  final HashMap<Class,Class> container;
    public ServiceCollection() {
        this.container = new HashMap<>();
        this.container.put(IServiceCollection.class,ServiceCollection.class);
    }

    @Override
    public <TSource> void register(Class<TSource> source, Class<? extends TSource> target) {
        container.put(source,target);
    }
}