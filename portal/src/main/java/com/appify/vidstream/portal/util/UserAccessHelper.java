package com.appify.vidstream.portal.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by swapnil on 12/10/16.
 */
public class UserAccessHelper {

    public static boolean canUserViewCategory(String userName, String categoryId,Connection conn) {



        return false;
    }

    public static boolean canUserEditCategory(String userName, String categoryId) {
        return false;
    }

    public static boolean canUserViewCategorization(String userName, String categoryId) {
        return false;
    }

    public static boolean canUserEditCategorization(String userName, String categoryId) {
        return false;
    }

    public static boolean canUserViewApp(String userName, String appId) {
        return false;
    }

    public static boolean canUserEditApp(String userName, String appId) {
        return false;
    }
}
