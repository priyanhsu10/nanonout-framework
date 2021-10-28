package app;

import nanonout.ioc.IServiceCollection;
import nanonout.pipeline.AppPipeLineBuilder;
import nanonout.pipeline.mildlewares.AppStartup;
public class Startup extends AppStartup {
    public void configureServices(IServiceCollection iServiceCollection) {
        //   iServiceCollection.register(IServiceCollection.class,ServiceCollection.class);
    }

    public void configurePipeline(AppPipeLineBuilder builder) {
        builder.addPipe(MiddleWare1.class);
        builder.addPipe(MiddleWare2.class);
    }
}
