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
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Nileh Diore
 */
public class UpdateApplicationAction extends ActionSupport implements
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
    private String appid, hidden_appid, application_Name, hidden_Editapplication_Status, previous_application_name;

    public UpdateApplicationAction() {
    }

    public String[] getFileUploadFileName() {
        return fileUploadFileName;
    }

    public String getHidden_Editapplication_Status() {
        return hidden_Editapplication_Status;
    }

    public String getPrevious_application_name() {
        return previous_application_name;
    }

    public void setPrevious_application_name(String previous_application_name) {
        this.previous_application_name = previous_application_name;
    }

    public void setHidden_Editapplication_Status(String hidden_Editapplication_Status) {
        this.hidden_Editapplication_Status = hidden_Editapplication_Status;
    }

    public File[] getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File[] fileUpload) {
        this.fileUpload = fileUpload;
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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getHidden_appid() {
        return hidden_appid;
    }

    public void setHidden_appid(String hidden_appid) {
        this.hidden_appid = hidden_appid;
    }

    public String getApplication_Name() {
        return application_Name;
    }

    public void setApplication_Name(String application_Name) {
        this.application_Name = application_Name;
    }

    @Override
    public String execute() throws Exception {
        try {
            con = null;
            sql = null;
            prest = null;



            con = conn.appify.vidstream.portal.DataConnection.getConnection();
            SimpleDateFormat originalFormat;
            java.util.Date date = new java.util.Date();
            System.out.println(new Timestamp(date.getTime()));
            Timestamp mod_date = new Timestamp(date.getTime());

            String hiden_appid = getHidden_appid();
            String App_Name = getApplication_Name();
            String App_Status = getHidden_Editapplication_Status();
            String prv_app_name = previous_application_name;
            System.out.println("Application Name Before----------"+prv_app_name);
            System.out.println("Application Name After----------"+App_Name);

            if (fileUpload == null) {
                String sql = "update application set id=?,name=?,status=?,date_modified=? where id='" + hiden_appid + "'";
                prest = con.prepareStatement(sql);

                prest.setInt(1, Integer.parseInt(hiden_appid));
                prest.setString(2, App_Name);
                prest.setString(3, App_Status);
                prest.setTimestamp(4, mod_date);
                prest.executeUpdate();
                System.out.println("Update in Application table");
                String Sql_app_name_update_in_image_table = "update images set name=? where name='" + prv_app_name + "' ";
                PreparedStatement pst_app_name_update_in_image_table = con.prepareStatement(Sql_app_name_update_in_image_table);
                pst_app_name_update_in_image_table.setString(1, App_Name);
                pst_app_name_update_in_image_table.executeUpdate();
                  System.out.println("Update APplication Name in images Table");
                

            } else {
                String sql = "update application set id=?,name=?,status=?,bg_image=?,date_modified=? where id='" + hiden_appid + "'";
                prest = con.prepareStatement(sql);

                prest.setInt(1, Integer.parseInt(hiden_appid));
                prest.setString(2, App_Name);
                prest.setString(3, App_Status);
                
                  String sql_img_id = "select id from images where name='" + prv_app_name + "' order by id desc limit 1";
                PreparedStatement prepareStatement = con.prepareStatement(sql_img_id);
                ResultSet executeQuery = prepareStatement.executeQuery();
                executeQuery.next();
                int img_id = executeQuery.getInt(1);
                prest.setInt(4, img_id);

               // String SQL_upload_image = "INSERT INTO images(name,contents) values(?,?) ";
                String SQL_upload_image="update images set name=?, contents=? where name='"+prv_app_name+"'";
                pst_upload_img = con.prepareStatement(SQL_upload_image);
                pst_upload_img.setString(1, App_Name);

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

             
                prest.setTimestamp(5, mod_date);
                prest.executeUpdate();
                System.out.println("Update in Applications Table");

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
    public void setServletRequest(HttpServletRequest hsr) {
        this.servletRequest = servletRequest;//To change body of generated methods, choose Tools | Templates.
    }
}