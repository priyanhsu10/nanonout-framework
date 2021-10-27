package nanonout.pipeline.mildlewares.routemap;

import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.Pipe;

public class UseRouteingMiddleware extends Pipe {
    public UseRouteingMiddleware(Action action) {
        super(action);
    }

    @Override
    public void handle(ActionContext actionContext) {

    }
}
