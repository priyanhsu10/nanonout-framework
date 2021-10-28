package app;

import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.Pipe;

import java.io.IOException;

public class MiddleWare2 extends Pipe {
    public MiddleWare2(Action action) {
        super(action);
    }

    @Override
    public void handle(ActionContext actionContext) {
        try {
            actionContext.getResponse().getWriter().println("starting second middleware");

            actionContext.getData().put("second","second middle waire");
            _action.next(actionContext);
            actionContext.getResponse().getWriter().println("ending second middleware");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
