package vkode.linerle;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Linerle implements Filter {

    static {
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z"));
        LinerleCallbacks.objectMapper = om;
    }
    
    @Override
    public void destroy() { 
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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