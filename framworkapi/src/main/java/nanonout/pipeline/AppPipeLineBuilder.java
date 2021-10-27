package nanonout.pipeline;

import nanonout.pipeline.mildlewares.IStartup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class AppPipeLineBuilder {
    Action _mainAction;
    List<Class<? extends Pipe>> _pipeTypes;

    public AppPipeLineBuilder(Action mainAction) {
        _mainAction = mainAction;
        _pipeTypes = new ArrayList<>();
    }
    public  void useStartup(Class<? extends IStartup> startup){
        try {
            IStartup startup1 = (IStartup) startup.newInstance();
            startup1.configurePipeline(this);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public AppPipeLineBuilder addPipe(Class<? extends Pipe> pipeType) {
        _pipeTypes.add(pipeType);
        return this;
    }

    public Action createPipe(int index) {
        if (index < _pipeTypes.size()-1) {
            Action childPipeHandler = createPipe(index + 1);
            return build(index, childPipeHandler);

        } else {
            return build(index,null );
        }
    }

    public Action build() {
        return createPipe(0);
    }

    public Action build(int index, Action action) {
        try {
            if (action == null) {
                action = _mainAction;
            }
            Pipe c = _pipeTypes.get(index)
                    .getConstructor(Action.class).newInstance(action);

            return c::handle;
        } catch (InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;
    }
}