package cz.muni.fi.pa165.deliveryservice.security;

/**
 * Created by Jamik on 16.12.2016.
 */
import javax.servlet.annotation.WebFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/shipment/*", "/courier/*", "/product/*", "/customer/detail/*", "/customer/list", "/customer/new"})
public class ProtectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        Object auth = request.getSession().getAttribute("authenticatedUser");
        if (auth == null) {
            response401(response, request);
            return;
        }

        chain.doFilter(request, response);
    }



    private void response401(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.sendRedirect(request.getContextPath()+"/customer/login_page");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}