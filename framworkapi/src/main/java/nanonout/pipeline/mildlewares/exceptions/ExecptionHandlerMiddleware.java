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
    public void handle(ActionContext actionContext) throws IOException {
        try {

            _action.next(actionContext);
        } catch (Exception exception) {
            Error e = new Error();

            e.setMessage(exception.getLocalizedMessage());
            e.setErrorCode(500);
            ExceptionResult result = new ExceptionResult(e);
            String data = JsonHelper.serialize(result, ExceptionResult.class);
            actionContext.getResponse().getWriter().println(data);
            actionContext.getResponse().setStatus(500);


        }

    }
}
