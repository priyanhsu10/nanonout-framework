package nanonout.pipeline.mildlewares.endpointexecuting;

import nanonout.app.ApplicationBuilder;
import nanonout.json.JsonHelper;
import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.Pipe;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class EndPointExecuterMidleware extends Pipe {
    public EndPointExecuterMidleware(Action action) {
        super(action);
    }

    @Override
    public void handle(ActionContext actionContext) {

        try {
            //1. controller initialization
            Object c = ApplicationBuilder.getServiceCollection().resolve(actionContext.getEndpoint().ControllerClass);
            //2. Model Binding
            Object[] parameter = resolveParameter(actionContext);

            //2. Model validation

            //basically prepare pipeline for filters same as midleware

            // 4. filter execution (in next phase)

            //action execution
            Object result = actionContext.getEndpoint().ActionMethod.invoke(c, parameter);

            //5.result processing
            Object processResult = ProcessResult(result, actionContext);

            actionContext.setActionResult(result);
            actionContext.getResponse().addHeader("Content-Type","application/json");
            actionContext.getResponse().getWriter().println(actionContext.getActionResult());

        } catch (IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Object ProcessResult(Object result, ActionContext actionContext) {
        String contentType = actionContext.getRequest().getHeader("Content-Type");
        if (contentType == null || contentType.equals("application/json")) {
            return JsonHelper.serialize(result);
        }
        if (contentType.equals("application/xml")) {
//            JAXBContext jaxbContext = JAXBContext.newInstance(actionContext.getEndpoint().getReturnType());
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createMarshaller();
//            Tutorials tutorials = (Tutorials) jaxbUnmarshaller.unmarshal(this.getFile());
            return JsonHelper.serialize(result);
        }
        return "";
    }

    private Object[] resolveParameter(ActionContext actionContext) {
        Method m = actionContext.getEndpoint().ActionMethod;
        if (m.getParameterCount() == 0) {
            return new Object[]{};
        }
        Class<?>[] types = m.getParameterTypes();
        Parameter[] parameters = m.getParameters();
        // if(actionContext.getEndpoint().ActionMethod.toString().equals("GET"))
//        for(Parameter p:parameters){
//
//        }
//        actionContext.getRequest().getRequest
        return new Object[]{};
    }
}
