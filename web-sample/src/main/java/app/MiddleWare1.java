package app;

import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.Pipe;

import java.io.IOException;

public class MiddleWare1 extends Pipe {
    public MiddleWare1(Action action) {
        super(action);
    }

    @Override
    public void handle(ActionContext actionContext) {
        try {
            actionContext.getResponse().getWriter().println("starting first middleware");

        actionContext.getData().put("first","first middle waire");
        _action.next(actionContext);
        actionContext.getResponse().getWriter().println("ending first middleware");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
