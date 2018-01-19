package filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/", servletNames = "/libraryDispatcher")
public class EncodingFilter implements Filter {
    private static final Logger Log = LogManager.getLogger(EncodingFilter.class.getSimpleName());
    private static final String UTF_ENCODING = "UTF-8";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Log.debug("Encoding filter has been created");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Log.info("Encoding filter doFilter()");
        servletRequest.setCharacterEncoding(UTF_ENCODING);
        servletResponse.setCharacterEncoding(UTF_ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Log.debug("Encoding filter has been destroyed");
    }
}
