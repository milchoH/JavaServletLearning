package Servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by milcho on 10/21/16.
 */
@WebServlet(name = "DynamicServlet",urlPatterns={"/DynamicServlet"})
public class DynamicServlet implements Servlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().println(((HttpServletRequest) servletRequest).getSession().getId());
        ((HttpServletRequest) servletRequest).getSession().setAttribute("testAttr", "test");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
