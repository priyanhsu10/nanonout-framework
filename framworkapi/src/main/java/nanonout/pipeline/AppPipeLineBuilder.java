package nanonout.pipeline;

import nanonout.pipeline.mildlewares.endpointexecuting.EndPointExecuterMidleware;
import nanonout.ioc.IServiceCollection;
import nanonout.pipeline.mildlewares.IStartup;
import nanonout.pipeline.mildlewares.exceptions.ExecptionHandlerMiddleware;
import nanonout.pipeline.mildlewares.routemap.UseRouteingMiddleware;

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

    public void useStartup(Class<? extends IStartup> startupClass, IServiceCollection iServiceCollection) {
        try {
            IStartup startup = (IStartup) startupClass.newInstance();
            startup.configureServices(iServiceCollection);
            addInitialFilters();
            startup.configurePipeline(this);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private void addInitialFilters() {

        //_pipeTypes.add(UseRouteingMiddleware.class);
    }
    public void useEndPointSelector() {

        _pipeTypes.add(UseRouteingMiddleware.class);
    }
    public void useExecuteEndpoint() {

        _pipeTypes.add(EndPointExecuterMidleware.class);
    }
    public void useExceptionHandler() {

        _pipeTypes.add(ExecptionHandlerMiddleware.class);
    }
    public AppPipeLineBuilder addPipe(Class<? extends Pipe> pipeType) {
        _pipeTypes.add(pipeType);
        return this;
    }

    public Action createPipe(int index) {
        if (index < _pipeTypes.size() - 1) {
            Action childPipeHandler = createPipe(index + 1);
            return build(index, childPipeHandler);

        } else {
            return build(index, null);
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
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        return null;
    }
}