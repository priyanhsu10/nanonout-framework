package app;

import nanonout.ioc.IServiceCollection;
import nanonout.pipeline.AppPipeLineBuilder;
import nanonout.pipeline.mildlewares.IStartup;

public class Startup implements IStartup {
    @Override
    public void configureServices(IServiceCollection iServiceCollection) {
     //   iServiceCollection.register(IServiceCollection.class,ServiceCollection.class);
    }

    @Override
    public void configurePipeline(AppPipeLineBuilder builder) {
        //builder.addPipe(First.class);
        //builder.addPipe(Second.class);
        //builder.addPipe((Wrap.class));
    }
}
