package com.appify.vidstream.portal.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by swapnil on 15/10/16.
 */
public class CategoryHelper {

    public static String getCategorizationIdForCategoryId(String categoryId, Connection conn) {

        try {
            String sql = "select categorization_id from category where id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, categoryId);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            String categorization = resultSet.getString(1);
            return categorization;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get categorization for category", e);
        }
    }

    public static String getAppIdForCategoryId(String categoryId, Connection conn) {
        String categorizationId = getCategorizationIdForCategoryId(categoryId, conn);
        if (categorizationId == null)
            return null;

        return CategorizationHelper.getAppIdForCategorizationId(categoryId, conn);

    }
}
