/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Nileh Diore
 */
public class AddApplicationNameAction extends ActionSupport implements
        ServletRequestAware {

    private Connection con;
    private File[] fileUpload;
    private String[] fileUploadFileName;
    private String[] fileUploadContentType;
    private String saveDirectory;
    File uploadedFile, destFile, destFile1;
    String fileName, fileName1;
    FileInputStream fis, fis1;
    private HttpServletRequest servletRequest;
    File uploadedFile1;
    private ResultSet rs_st;
    private String sql;
    PreparedStatement prest, prest_up, prest_1;
    private String app_name_1, app_status, sql_image,hidden_app_status;

    public String getHidden_app_status() {
        return hidden_app_status;
    }

    public void setHidden_app_status(String hidden_app_status) {
        this.hidden_app_status = hidden_app_status;
    }

    public String getApp_status() {
        return app_status;
    }

    public void setApp_status(String app_status) {
        this.app_status = app_status;
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

    public String getApp_name_1() {
        return app_name_1;
    }

    public void setApp_name_1(String app_name_1) {
        this.app_name_1 = app_name_1;
    }

    public AddApplicationNameAction() {
    }

    @Override
    public String execute() throws Exception {
        con = null;
        sql = null;
        prest = null;

        try {
            String apd, cat, apn, sta;

            apn = app_name_1;
            sta = hidden_app_status;
            System.out.println("hidden_app_status::::"+hidden_app_status);
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            sql_image = "INSERT INTO images(name,contents) values(?,?)";
            sql = "INSERT INTO application(name,status,bg_image) VALUES (?,?,?)";
            prest = con.prepareStatement(sql);
            prest_1 = con.prepareStatement(sql_image);
            prest_1.setString(1, apn);
            for (int i = 0; i < fileUpload.length; i++) {
                System.out.println("----------------->" + fileUpload[i]);
                uploadedFile = fileUpload[i];
                fileName = fileUploadFileName[i];
                destFile = new File(saveDirectory + File.separator + fileName);
                FileUtils.copyFile(uploadedFile, destFile);
                fis = new FileInputStream(uploadedFile);
                prest_1.setBinaryStream(2, fis, (int) uploadedFile.length());

            }
            prest_1.executeUpdate();
            String sql_img_id = "select id from images where name='" + apn + "' order by id desc limit 1";
            PreparedStatement prepareStatement = con.prepareStatement(sql_img_id);
            ResultSet executeQuery = prepareStatement.executeQuery();
            executeQuery.next();
            int img_id = executeQuery.getInt(1);

            prest.setString(1, apn);
            prest.setString(2, sta);
            prest.setInt(3, img_id);
            prest.executeUpdate();
            System.out.println("Application Master added successfully");
            con.close();
            return SUCCESS;
            
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception" + e);
            return "error";
        } finally {
            try {
                prest.close();
            } catch (Exception e) {
            }
            try {
                rs_st.close();

            } catch (Exception e) {
            }
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Exception" + ex);
            }
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.servletRequest = servletRequest; //To change body of generated methods, choose Tools | Templates.
    }
}