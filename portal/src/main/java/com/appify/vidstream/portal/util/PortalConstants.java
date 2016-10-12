/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal.util;

/**
 *
 * @author Nileh Diore
 */
public final class PortalConstants {

	public static final String PROPERTIES_URL = "/appify/conf/vidStreamPortal/conf.properties";

	public static final String SESSION_ATTR_USER_NAME = "uName";

    public static final String SESSION_ATTR_ROLE_NAME = "user_role_name";

    public static final String ADMIN_ROLE_NAME = "Admin";


	// public static final String
	// YOUTUBE_API_KEY="AIzaSyAEBs_3Va1D1edITLw8hND9IJAcnh50Lx4";
	//TODO : Need to move this id to conf file
	public static final String YOUTUBE_API_KEY = "AIzaSyADHy3TFjaTezCUBD1KwSyK_Oe9NaW9Zto";

	public static String getYOUTUBE_API_KEY() {
		return YOUTUBE_API_KEY;
	}

}