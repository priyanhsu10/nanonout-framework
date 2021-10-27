package nanonout.pipeline.mildlewares;

import nanonout.ioc.IServiceCollection;
import nanonout.pipeline.AppPipeLineBuilder;

public interface  IStartup{
    void configureServices( IServiceCollection iServiceCollection);
    void  configurePipeline(AppPipeLineBuilder builder);
}