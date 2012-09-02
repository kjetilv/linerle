package vkode.linerle;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Linerle implements Filter {

    private static String prefix;

    private static String contextPath;

    public static String getPrefix() {
        return prefix;
    }

    public static String getContextPath() {
        return contextPath;
    }

    @Override
    public void destroy() { 
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        prefix = filterConfig.getInitParameter("prefix");
        contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, 
                         ServletResponse servletResponse, 
                         FilterChain filterChain) 
        throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest &&
            servletResponse instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse res = (HttpServletResponse) servletResponse;
            if (!LinerleCallbacks.processed(req, res)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}