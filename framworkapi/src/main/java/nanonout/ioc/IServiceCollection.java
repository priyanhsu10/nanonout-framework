package nanonout.ioc;

public interface IServiceCollection{
    <TSource>  void register(Class<TSource> source, Class<? extends TSource> target);
}