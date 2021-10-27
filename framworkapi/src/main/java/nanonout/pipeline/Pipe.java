package nanonout.pipeline;

public abstract class Pipe {
    protected Action _action;

    public Pipe(Action action) {
        _action = action;
    }

    public abstract void handle(ActionContext actionContext);

}
