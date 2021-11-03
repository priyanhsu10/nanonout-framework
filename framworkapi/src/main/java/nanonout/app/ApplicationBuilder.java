package nanonout.app;

import nanonout.endpointrouting.endpointprocessing.EndPointManger;
import nanonout.ioc.IServiceCollection;
import nanonout.ioc.ServiceCollection;
import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.AppPipeLineBuilder;
import nanonout.pipeline.mildlewares.IStartup;

public  class ApplicationBuilder {
    private static Action action;
    private static EndPointManger endPointManger;
    private static IServiceCollection serviceCollection;

    public static Action getAction() {
        return action;
    }

    public static EndPointManger getEndPointManger() {
        return endPointManger;
    }

    public static IServiceCollection getServiceCollection() {
        return serviceCollection;
    }

    public static void BuildApp(String basePath) throws Exception {
        try {
            Class startupClass = Class.forName(basePath);
            serviceCollection=new ServiceCollection();
            endPointManger = new EndPointManger(startupClass,serviceCollection);
            AppPipeLineBuilder builder = new AppPipeLineBuilder(ApplicationBuilder::initalAction);
            builder.useStartup(startupClass,serviceCollection);
             action = builder.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void initalAction(ActionContext actionContext) {
        System.out.println("executing first middler ware");
        actionContext.getData().put("first", "first midile ware");
    }
}
