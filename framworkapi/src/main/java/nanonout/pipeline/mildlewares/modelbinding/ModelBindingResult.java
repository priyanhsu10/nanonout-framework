package nanonout.pipeline.mildlewares.modelbinding;

import nanonout.endpointrouting.endpointprocessing.EndPoint;
import nanonout.pipeline.ActionContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelBindingResult {
    private  Map<String, Object> queryParamiters = new HashMap<>();
    private String bodyData;
   private Map<String, Object> routeData = new HashMap<>();
    private  Map<String, String> header = new HashMap<>();

    public Map<String, Object> getQueryParamiters() {
        return queryParamiters;
    }

    public String getBodyData() {
        return bodyData;
    }

    public Map<String, Object> getRouteData() {
        return routeData;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public ModelBindingResult(ActionContext actionContext) {

        proccesBinding(actionContext);
    }

    private void proccesBinding(ActionContext actionContext) {


        String method = actionContext.getEndpoint().HttpMethod;
//        if(method.equals("GET") || method.equals("DELETE") ){
        //no need to read the body form request
        String query = actionContext.getRequest().getQueryString();
        if (query != null && !query.isEmpty()) {

            Arrays.stream(query.split("&")).forEach(x -> {
                if (x.contains("=")) {
                    String[] keyValue = x.split("=");
                    queryParamiters.put(keyValue[0], keyValue[1]);
                }

            });
        }
        if (actionContext.getEndpoint().isPattern) {
            routeData = getRouteData(actionContext.getEndpoint(),
                    actionContext.getRequest().getRequestURI());
        }
        if (method.equals("PUT") || method.equals("POST")) {
            try {
                bodyData = actionContext.getRequest()
                        .getReader()
                        .lines()
                        .collect(Collectors.joining());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private Map<String, Object> getRouteData(EndPoint endPoint, String routePath) {

        Map<String, Object> routData = new HashMap<>();
        String[] segments = routePath.split("/");
        String[] tokens = endPoint.UrlTokens;
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].startsWith(":")) {
                String value = segments[i];
                String parameterName = tokens[i].substring(1);
                Class<?> paramtertype = endPoint.ParameterNameTypes.get(parameterName);
                routData.put(parameterName, paramtertype.cast(value));
            }
        }
        return routData;

    }

}
