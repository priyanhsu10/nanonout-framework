package nanonout.filters;

import nanonout.app.ApplicationBuilder;
import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.AppPipeLineBuilder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

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
