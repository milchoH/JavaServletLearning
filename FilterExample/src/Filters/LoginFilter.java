package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by milcho on 10/22/16.
 */
@WebFilter("/DynamicServlet")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String username = (String) httpRequest.getSession().getAttribute("username");
        String requestUsername = servletRequest.getParameter("user");

        if(username==null && requestUsername == null){
            ((HttpServletResponse) servletResponse).sendRedirect("login.html");
        }else if(requestUsername != null){
            httpRequest.getSession().setAttribute("username", requestUsername);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
