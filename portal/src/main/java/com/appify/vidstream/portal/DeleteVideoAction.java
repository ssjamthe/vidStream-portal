/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nileh Diore
 */
public class DeleteVideoAction extends ActionSupport {

    private Connection con;
    private String hidden_video_id, hidden_video_category_id;

    public String getHidden_video_category_id() {
        return hidden_video_category_id;
    }

    public void setHidden_video_category_id(String hidden_video_category_id) {
        this.hidden_video_category_id = hidden_video_category_id;
    }

    public DeleteVideoAction() {
    }

    public String getHidden_video_id() {
        return hidden_video_id;
    }

    public void setHidden_video_id(String hidden_video_id) {
        this.hidden_video_id = hidden_video_id;
    }

    @Override
    public String execute() throws Exception {
        try {
            System.out.println("hidden_video_category_id-----------------" + hidden_video_category_id);
            con = null;
            String vid = getHidden_video_id();
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            try {
                String SQL_Check_Video_mapping_Category = "SELECT category_id FROM youtube_video_category_mapping where video_id='" + vid + "'";
                PreparedStatement pst_check_video_cat_mapping = con.prepareStatement(SQL_Check_Video_mapping_Category);
                ResultSet rs_check_video_cat_mapping = pst_check_video_cat_mapping.executeQuery();
                if (!rs_check_video_cat_mapping.next()) {
                    String sql = "DELETE FROM youtube_video WHERE id ='" + vid + "'";
                    System.out.println("sql_for delete youtube_video:::" + sql);
                    Statement createStatement = con.createStatement();
                    createStatement.execute(sql);
                } else {
                    String SQL_Delete_video_category_mapping = "DELETE FROM youtube_video_category_mapping where category_id='" + hidden_video_category_id + "' and video_id='" + vid + "' ";
                    System.out.println("SQL_Delete_video_category_mapping:::" + SQL_Delete_video_category_mapping);
                    Statement createStatement_mapping_cat = con.createStatement();
                    createStatement_mapping_cat.execute(SQL_Delete_video_category_mapping);


                    String SQL_Inside_Check_Video_mapping_Category = "SELECT category_id FROM youtube_video_category_mapping where video_id='" + vid + "'";
                    PreparedStatement pst_Inside_check_video_cat_mapping = con.prepareStatement(SQL_Inside_Check_Video_mapping_Category);
                    ResultSet rs_Inside_check_video_cat_mapping = pst_Inside_check_video_cat_mapping.executeQuery();

                    if (!rs_Inside_check_video_cat_mapping.next()) {
                        String sql_lnside = "DELETE FROM youtube_video WHERE id ='" + vid + "'";
                        System.out.println("sql_lnside delete youtube_video :::" + sql_lnside);
                        Statement createStatement_inside = con.createStatement();
                        createStatement_inside.execute(sql_lnside);

                    } else {
                    }


                }

            } catch (Exception expty) {
                System.err.println("Exception------------------>>>" + expty);
            }

            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR;
        } finally {
            try {


                con.close();
            } catch (Exception e) {
                try {
                    if (null != con) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        }
    }
}