package nanonout.filters;

import nanonout.app.ApplicationBuilder;
import nanonout.pipeline.Action;
import nanonout.pipeline.ActionContext;
import nanonout.pipeline.AppPipeLineBuilder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("*")
public class AppFilter implements Filter {
    public  static Action action;
    @Override

    public void init(FilterConfig filterConfig) throws ServletException {
      String startupClass=  filterConfig.getInitParameter("Startup");
        try {
            Class c= Class.forName(startupClass);
            if(action==null){

                 action= ApplicationBuilder.BuildApp(c);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(servletRequest);
        //trigger framework
        ActionContext actionContext= new ActionContext(servletRequest,servletResponse);
        action.next(actionContext);

        System.out.println("complete request");
    }

    @Override
    public void destroy() {

    }
}
