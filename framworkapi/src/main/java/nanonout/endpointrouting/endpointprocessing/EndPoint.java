package nanonout.endpointrouting.endpointprocessing;

import nanonout.endpointrouting.ControllerBase;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndPoint {
    public Method ActionMethod;
    public Class<? extends ControllerBase> ControllerClass;
    public String HttpMethod;
    public String [] PatternParamters;
    public String [] UrlTokens;
    public String DisplayName;
    public Map<String,Class<?>> ParameterNameTypes = new HashMap<>();
    public  String Pattern;
    public boolean isPattern=false;

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
