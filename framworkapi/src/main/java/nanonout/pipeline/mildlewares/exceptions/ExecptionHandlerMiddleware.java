package nanonout.pipeline.mildlewares.exceptions;

import nanonout.json.JsonHelper;
import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.Pipe;

import java.io.IOException;

public class ExecptionHandlerMiddleware extends Pipe {
    public ExecptionHandlerMiddleware(Action action) {
        super(action);
    }

    @Override
    public void handle(ActionContext actionContext) {
        try {

            _action.next(actionContext);
        } catch (Exception exception) {
            Error e = new Error();

            e.setMessage(exception.getMessage());
            e.setErrorCode(500);
            ExceptionResult result=new ExceptionResult();
            try {
                actionContext.getResponse().getWriter().println(JsonHelper.serialize(result));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }
}
