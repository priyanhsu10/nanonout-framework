package nanonout.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import nanonout.app.ApplicationBuilder;
import nanonout.pipeline.ActionContext;

public class AppFilter implements Filter {

    private String BaseClass="";

    public AppFilter(String baseClass) {
        BaseClass = baseClass;
    }

    @Override

    public void init(FilterConfig filterConfig) throws ServletException {
        String startupClass = BaseClass;

        if (ApplicationBuilder.getAction() == null) {

            try {
                ApplicationBuilder.BuildApp(startupClass);
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(servletRequest);
        //trigger framework
        ActionContext actionContext = new ActionContext(servletRequest, servletResponse);
        try {
            ApplicationBuilder.getAction() .next(actionContext);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
