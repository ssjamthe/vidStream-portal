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

/**
 *
 * @author Nileh Diore
 */
public class UpdateVideoAction extends ActionSupport {

    private Connection con;
    private ResultSet rs_upload_img;
    private String sql, Edit_Video_Id, Hidden_Edit_Video_Id, Edit_Video_Title;
    PreparedStatement prest;

    public UpdateVideoAction() {
    }

    public String getEdit_Video_Id() {
        return Edit_Video_Id;
    }

    public void setEdit_Video_Id(String Edit_Video_Id) {
        this.Edit_Video_Id = Edit_Video_Id;
    }

    public String getHidden_Edit_Video_Id() {
        return Hidden_Edit_Video_Id;
    }

    public void setHidden_Edit_Video_Id(String Hidden_Edit_Video_Id) {
        this.Hidden_Edit_Video_Id = Hidden_Edit_Video_Id;
    }

    public String getEdit_Video_Title() {
        return Edit_Video_Title;
    }

    public void setEdit_Video_Title(String Edit_Video_Title) {
        this.Edit_Video_Title = Edit_Video_Title;
    }

    @Override
    public String execute() throws Exception {
        try {
            con = null;
            sql = null;
            prest = null;
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String vid = getHidden_Edit_Video_Id();
            String VName = getEdit_Video_Title();
            System.out.println("vid:::::" + vid);
            sql = "update youtube_video set name =? where id='" + vid + "'";
            prest = con.prepareStatement(sql);
            prest.setString(1, VName);
            prest.executeUpdate();
            System.out.println("Update Successfully in youtube_video Table with Videiid " + vid);

        } catch (Exception exp) {
            System.out.println("Exception in UpdateVideoAction::::" + exp);
            return ERROR;
        } finally {
            try {
                prest.close();

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
        return SUCCESS;


    }
}