package app;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebFilter("*")
public class TestServelt implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       Reflections reflections=new Reflections("app.controllers");
//        Reflections reflections = new Reflections(
//                new ConfigurationBuilder()
//                        .forPackage("app.controllers")
//                        .filterInputsBy(new FilterBuilder().includePackage("app.controllers")));

        System.out.println(reflections);
        servletResponse.getWriter().println("test");
        return;
    }

    @Override
    public void destroy() {

    }
}
