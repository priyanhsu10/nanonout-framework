package nanonout.endpointrouting.endpointprocessing;

import nanonout.endpointrouting.ControllerBase;

import java.lang.reflect.Method;

public class EndPoint {
    public Method ActionMethod;
    public Class<? extends ControllerBase> ControllerClass;
    public String HttpMethod;
    public Object[] RouteParameters;
    public String DisplayName;

    public Class getReturnType() {
        return ActionMethod.getReturnType();
    }

    public Object Invoke(ControllerBase object) {
        return null;
    }

    public EndPoint(Method actionMethod, Class<? extends ControllerBase> controller) {
        ActionMethod = actionMethod;
        this.ControllerClass = controller;

    }
}
