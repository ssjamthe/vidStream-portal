/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Nileh Diore
 */
public class UpdateCategorization extends ActionSupport implements
        ServletRequestAware {

    private File[] fileUpload;
    private String[] fileUploadFileName;
    private String[] fileUploadContentType;
    private HttpServletRequest servletRequest;
    File uploadedFile, destFile, destFile1;
    String fileName, fileName1;
    FileInputStream fis, fis1;
    File uploadedFile1;
    private String saveDirectory;
    private Connection con;
    private ResultSet rs_upload_img;
    private String sql;
    PreparedStatement prest, pst_upload_img;
    Statement stmt;
    private String categorization_appid, hidden_categorization_appid, edit_categorization_Name, hidden_edit_categorization_ID;

    public String getHidden_edit_categorization_ID() {
        return hidden_edit_categorization_ID;
    }

    public void setHidden_edit_categorization_ID(String hidden_edit_categorization_ID) {
        this.hidden_edit_categorization_ID = hidden_edit_categorization_ID;
    }

    public UpdateCategorization() {
    }

    public String getCategorization_appid() {
        return categorization_appid;
    }

    public void setCategorization_appid(String categorization_appid) {
        this.categorization_appid = categorization_appid;
    }

    public String getHidden_categorization_appid() {
        return hidden_categorization_appid;
    }

    public void setHidden_categorization_appid(String hidden_categorization_appid) {
        this.hidden_categorization_appid = hidden_categorization_appid;
    }

    public String getEdit_categorization_Name() {
        return edit_categorization_Name;
    }

    public void setEdit_categorization_Name(String edit_categorization_Name) {
        this.edit_categorization_Name = edit_categorization_Name;
    }

    public File[] getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File[] fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String[] getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String[] fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String[] getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String[] fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String execute() throws Exception {
        try {
            con = null;
            sql = null;
            prest = null;

            SimpleDateFormat originalFormat;
            java.util.Date date = new java.util.Date();
            System.out.println(new Timestamp(date.getTime()));
            Timestamp mod_date = new Timestamp(date.getTime());

            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            String hideen_app_id = getHidden_categorization_appid();

            String updte_cat_name = getEdit_categorization_Name();

            String catId = getHidden_edit_categorization_ID();
            System.out.println("catId::::::::::::" + catId);


            if (fileUpload == null) {
                String sql = "update categorization set app_id=?,name=?,date_modified=?  where id='" + catId + "' ";

                prest = con.prepareStatement(sql);

                prest.setInt(1, Integer.parseInt(hideen_app_id));
                prest.setString(2, updte_cat_name);
                prest.setTimestamp(3, mod_date);
                prest.executeUpdate();
            } else {
                String sql = "update categorization set app_id=?,name=?,image=?, date_modified=? where id='" + catId + "' ";

                prest = con.prepareStatement(sql);
             prest.setInt(1, Integer.parseInt(hideen_app_id));
                prest.setString(2, updte_cat_name);


                String SQL_upload_image = "INSERT INTO images(name,contents) values(?,?) ";
                pst_upload_img = con.prepareStatement(SQL_upload_image);
                pst_upload_img.setString(1, updte_cat_name);

                for (int i = 0; i < fileUpload.length; i++) {
                    System.out.println("----------------->" + fileUpload[i]);
                    uploadedFile = fileUpload[i];
                    fileName = fileUploadFileName[i];
                    destFile = new File(saveDirectory + File.separator + fileName);
                    FileUtils.copyFile(uploadedFile, destFile);
                    fis = new FileInputStream(uploadedFile);
                    pst_upload_img.setBinaryStream(2, fis, (int) uploadedFile.length());

                }
                pst_upload_img.executeUpdate();
                System.out.println("Update in images Table");
                String sql_img_id = "select id from images where name='" + updte_cat_name + "' order by id desc limit 1";
                PreparedStatement prepareStatement = con.prepareStatement(sql_img_id);
                ResultSet executeQuery = prepareStatement.executeQuery();
                executeQuery.next();
                int img_id = executeQuery.getInt(1);

                prest.setInt(3, img_id);
                prest.setTimestamp(4, mod_date);
                prest.executeUpdate();
                System.out.println("Update in categorization table");

            }



        } catch (SQLException e) {
            e.printStackTrace(System.out);
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

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest; //To change body of generated methods, choose Tools | Templates.
    }
}