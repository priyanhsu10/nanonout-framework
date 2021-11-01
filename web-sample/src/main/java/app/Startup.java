package app;

import nanonout.app.ApplicationBuilder;
import nanonout.endpointrouting.ControllerBase;
import nanonout.ioc.IServiceCollection;
import nanonout.pipeline.AppPipeLineBuilder;
import nanonout.pipeline.mildlewares.AppStartup;
import nanonout.pipeline.mildlewares.IStartup;
import org.reflections.Reflections;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

class AccessingAllClassesInPackage {

    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .filter(x->x.isAssignableFrom(ControllerBase.class))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
public class Startup extends AppStartup {
    public Startup(String basePackage) {
        super(basePackage);
    }

    public void configureServices(IServiceCollection iServiceCollection) {
        //   iServiceCollection.register(IServiceCollection.class,ServiceCollection.class);

    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      // Set<Class> s= new AccessingAllClassesInPackage ().findAllClassesUsingClassLoader("app.controllers");

      //  ApplicationBuilder.
        super.init(filterConfig);
    }

    public void configurePipeline(AppPipeLineBuilder builder) {
        builder.addPipe(MiddleWare1.class);
        builder.addPipe(MiddleWare2.class);
    }
}
