package nanonout.app;

import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.AppPipeLineBuilder;
import nanonout.pipeline.mildlewares.IStartup;

public class ApplicationBuilder {
    public  static Action BuildApp(Class<? extends IStartup> startupClass){
        AppPipeLineBuilder builder= new AppPipeLineBuilder(ApplicationBuilder::initalAction);
        builder.useStartup(startupClass);
       return  builder.build();

    }
    public static void initalAction(ActionContext actionContext) {
        System.out.println("executing first middler ware");
        actionContext.getData().put("first", "first midile ware");
    }
}
