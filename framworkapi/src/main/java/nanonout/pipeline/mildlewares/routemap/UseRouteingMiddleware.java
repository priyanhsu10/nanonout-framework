package nanonout.pipeline.mildlewares.routemap;

import nanonout.app.ApplicationBuilder;
import nanonout.endpointrouting.endpointprocessing.EndPoint;
import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.Pipe;

import java.io.IOException;
import java.io.PrintWriter;

public class UseRouteingMiddleware extends Pipe {
    public UseRouteingMiddleware(Action action) {
        super(action);
    }

    @Override
    public void handle(ActionContext actionContext) throws Exception {


        setRoutingData(actionContext);

        _action.next(actionContext);


    }

    private void setRoutingData(ActionContext actionContext) throws IOException {
        PrintWriter p = actionContext.getResponse().getWriter();
        String routPath = actionContext.getRequest().getRequestURI()
                .substring(actionContext.getRequest().getContextPath().length()+1);
        actionContext.setRoute(routPath);
        EndPoint e = ApplicationBuilder.getEndPointManger().getEndPoint(routPath, actionContext.getRequest().getMethod());
        actionContext.setEndpoint(e);
    }
}
