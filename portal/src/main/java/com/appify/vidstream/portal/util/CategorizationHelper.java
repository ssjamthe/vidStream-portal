package com.appify.vidstream.portal.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by swapnil on 16/10/16.
 */
public class CategorizationHelper {

    public static String getAppIdForCategorizationId(String categorizationId, Connection conn)
    {
        try {
            String sql = "select app_id from categorization where id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, categorizationId);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            String appId = resultSet.getString(1);
            return appId;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get categorization for category", e);
        }
    }
}
