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
    public void handle(ActionContext actionContext) {

        try {

            PrintWriter p = actionContext.getResponse().getWriter();
            String routPath = actionContext.getRequest().getRequestURI()
                    .substring(actionContext.getRequest().getContextPath().length());
            EndPoint e = ApplicationBuilder.getEndPointManger().getEndPoint(routPath, actionContext.getRequest().getMethod());
            actionContext.setEndpoint(e);

            _action.next(actionContext);

        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
