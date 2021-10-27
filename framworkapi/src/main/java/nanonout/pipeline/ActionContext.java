package nanonout.pipeline;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class ActionContext {
    private final HashMap<String, Object> data;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HashMap<String, Object> getData() {
        return data;
    }

    public Object getRequest() {
        return request;
    }


    public ActionContext(ServletRequest servletRequest, ServletResponse servletResponse) {
        this.request = (HttpServletRequest)request;
        this.response =(HttpServletResponse) response;
        data = new HashMap<>();
    }

    ActionContext() {
        data = new HashMap<>();
    }
}
