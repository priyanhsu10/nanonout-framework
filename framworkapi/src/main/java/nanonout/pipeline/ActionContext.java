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

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public Object getRequest() {
        return request;
    }


    public ActionContext(ServletRequest servletRequest, ServletResponse servletResponse) {
        this.request = (HttpServletRequest)servletRequest;
        this.response =(HttpServletResponse) servletResponse;
        data = new HashMap<>();
    }

    ActionContext() {
        data = new HashMap<>();
    }
}
