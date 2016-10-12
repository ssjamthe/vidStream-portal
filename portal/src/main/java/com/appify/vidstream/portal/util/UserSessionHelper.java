package com.appify.vidstream.portal.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by swapnil on 12/10/16.
 */
public class UserSessionHelper {

    public static boolean isUserLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute(PortalConstants.SESSION_ATTR_USER_NAME) != null;
    }

    public static boolean isUserAdmin(HttpServletRequest request) {
        return PortalConstants.ADMIN_ROLE_NAME.equals(request.getSession().getAttribute(PortalConstants.SESSION_ATTR_ROLE_NAME));
    }
}
