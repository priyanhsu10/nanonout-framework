package nanonout.endpointrouting.endpointprocessing;

import nanonout.endpointrouting.ControllerBase;
import nanonout.endpointrouting.annotations.*;
import nanonout.ioc.IServiceCollection;
import nanonout.ioc.container.Scope;
import nanonout.pipeline.mildlewares.IStartup;
import org.reflections.Reflections;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

 public class EndPointManger {
    public static Map<String, List<EndPoint>> endPointMap = new HashMap<>();
    private final Set<Class<? extends ControllerBase>> controllerClasses;
     private IServiceCollection serviceCollection;

     public EndPointManger(Class<? extends IStartup> aClass,IServiceCollection serviceCollection) {
         this.serviceCollection = serviceCollection;
         System.out.println(aClass.getPackage().getName());
        Reflections reflections = new Reflections(aClass.getPackage().getName());

        this.controllerClasses = reflections.getSubTypesOf(ControllerBase.class);
        registerControllers();
        process();

    }

     private void registerControllers() {
         for (Class<? extends ControllerBase> c:controllerClasses) {
             serviceCollection.register(c, Scope.RequestScope);
         }

     }

     private void process() {
        for (Class<? extends ControllerBase> controller : controllerClasses) {

            //get base path from route annotation

            Route r = controller.getAnnotation(Route.class);
            String BaseControllerPath = r == null ? "" : r.path();
            prepareEndPoint(controller, BaseControllerPath);


        }

    }

    private void prepareEndPoint(Class<? extends ControllerBase> controller, String BaseControllerPath) {
        Method[] declaredMethods = controller.getDeclaredMethods();
        for (int j = 0, declaredMethodsLength = declaredMethods.length; j < declaredMethodsLength; j++) {
            Method m = declaredMethods[j];

            if (!Modifier.isPublic(m.getModifiers())) {
                continue;
            }

            EndPoint endPoint = new EndPoint(m, controller);
            if (m.isAnnotationPresent(Get.class)) {
                endPoint.HttpMethod = "GET";
                endPoint.DisplayName = m.getAnnotation(Get.class).path();


            } else if (m.isAnnotationPresent(Post.class)) {
                endPoint.HttpMethod = "POST";
                endPoint.DisplayName = m.getAnnotation(Post.class).path();


            } else if (m.isAnnotationPresent(Put.class)) {
                endPoint.HttpMethod = "PUT";
                endPoint.DisplayName = m.getAnnotation(Put.class).path();

            } else if (m.isAnnotationPresent(Delete.class)) {
                endPoint.HttpMethod = "DELETE";
                endPoint.DisplayName = m.getAnnotation(Delete.class).path();


            }
            String key = BaseControllerPath + endPoint.DisplayName;
            if(key==null|| key.equals("") ){
                continue;
            }
            if (endPointMap.containsKey(key)) {
                endPointMap.get(key).add(endPoint);
            } else {
                ArrayList<EndPoint> endPoints = new ArrayList<>();
                endPoints.add(endPoint);
                endPointMap.put(key, endPoints);
            }


        }
    }

     public EndPoint getEndPoint(String routPath, String method) {
        if(endPointMap.containsKey(routPath)){
            if(routPath.contains(":")){
                //parameter present
            }
         return endPointMap.get(routPath).stream()
                   .filter(x->x.HttpMethod.equals(method))
                           .findFirst().orElseThrow(()->
                         new RuntimeException("no action associated with route"));
        }
        else{
            throw  new RuntimeException("no action associated with route");
        }

     }
 }


