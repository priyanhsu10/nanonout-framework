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

           ApplicationBuilder.BuildApp(startupClass);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(servletRequest);
        //trigger framework
        ActionContext actionContext = new ActionContext(servletRequest, servletResponse);
        ApplicationBuilder.getAction() .next(actionContext);
    }

    @Override
    public void destroy() {

    }
}
