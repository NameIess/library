package com.epam.training.library.viewlayer.filters;

import com.epam.training.library.daolayer.model.dto.impl.UserDto;
import com.epam.training.library.viewlayer.command.Page;
import com.epam.training.library.daolayer.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.training.library.viewlayer.util.RequestManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/", servletNames = "/libraryDispatcher")
public class SecurityFilter implements Filter {
    private static final Logger Log = LogManager.getLogger(SecurityFilter.class.getSimpleName());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Log.debug("Security filter has been created");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Log.debug("SecurityFilter doFilter()");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        UserDto userDto = RequestManager.getUserFromSession(request);
        Long userRoleId = Role.ANONYMOUS_ROLE_ID;
        if (userDto != null) {
            userRoleId = userDto.getRoleId();
            Log.debug("User info: " + userDto);
        }

        Interceptor interceptor = Interceptor.getInstance();
        String actionName = RequestManager.getActionName(request);
        boolean hasActionAccess = interceptor.isAccessAllowed(actionName, userRoleId);
        if (hasActionAccess) {
            Log.debug("Access to " + actionName + " to " + userRoleId + " granted");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Log.debug("Access denied to " + userRoleId + " to action " + actionName);
            String safetyPath = Page.CHANGE_PAGE.toString() + Page.USER_SIGN_IN.toString();
            response.sendRedirect(safetyPath);
        }
    }

    @Override
    public void destroy() {
        Log.debug("Security filter has been destroyed");
    }
}
