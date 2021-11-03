package nanonout.pipeline;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class Pipe {
    protected Action _action;

    public Pipe(Action action) {
        _action = action;
    }

    public abstract void handle(ActionContext actionContext) throws  Exception;

}
