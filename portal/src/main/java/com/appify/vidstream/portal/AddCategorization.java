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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Nileh Diore
 */
public class AddCategorization extends ActionSupport implements
        ServletRequestAware {

    public AddCategorization() {
    }
    private File[] fileUpload;
    private String[] fileUploadFileName;
    private String[] fileUploadContentType;
    private String saveDirectory;
    File uploadedFile, destFile, destFile1;
    String fileName, fileName1;
    FileInputStream fis, fis1;
    File uploadedFile1;
    private String  categorization, app_name,app_id;
    private HttpServletRequest servletRequest;
    private Connection con;
    private ResultSet rs_st;
    private String sql, sql_img;
    PreparedStatement prest, prest_up, prest_img;
    Statement stmt;
    int app_id22;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public int getApp_id22() {
        return app_id22;
    }

    public void setApp_id22(int app_id22) {
        this.app_id22 = app_id22;
    }

   

    public String getCategorization() {
        return categorization;
    }

    public void setCategorization(String categorization) {
        this.categorization = categorization;
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

    @Override
    public String execute() throws Exception {
        con = null;
        sql = null;
        prest = null;

        try {
            String  cat, apn;
            int apd;

           // apd = app_id22;
            cat = categorization;
            //apn = app_name;
            String current_app_id=app_id;
           // System.out.println("current_app_id...................."+current_app_id);
         int app_id=  Integer.parseInt(current_app_id);

            con = conn.appify.vidstream.portal.DataConnection.getConnection();
            sql_img = "INSERT INTO images(name,contents) values(?,?)";
            sql = "INSERT INTO categorization(app_id,name,image) VALUES (?,?,?)";
            prest_img = con.prepareStatement(sql_img);
            prest_img.setString(1, cat);

            for (int i = 0; i < fileUpload.length; i++) {
                System.out.println("----------------->" + fileUpload[i]);
                uploadedFile = fileUpload[i];
                fileName = fileUploadFileName[i];
                destFile = new File(saveDirectory + File.separator + fileName);
                FileUtils.copyFile(uploadedFile, destFile);
                fis = new FileInputStream(uploadedFile);
                prest_img.setBinaryStream(2, fis, (int) uploadedFile.length());
            }
            prest_img.executeUpdate();
            
             String sql_img_id = "select id from images where name='" + cat + "' order by id desc limit 1";
            PreparedStatement prepareStatement = con.prepareStatement(sql_img_id);
            ResultSet executeQuery = prepareStatement.executeQuery();
            executeQuery.next();
            int img_id = executeQuery.getInt(1);
            prest = con.prepareStatement(sql);
            prest.setInt(1, app_id);
            prest.setString(2, cat);
            prest.setInt(3, img_id);
            prest.executeUpdate();
            System.out.println("Categorization added successfully");
            try{
            String getapp="SELECT app_id  FROM categorization where id='3'";
            PreparedStatement pst_app = con.prepareStatement(getapp);
            ResultSet rs_app = pst_app.executeQuery();
            rs_app.next();
            
            
            
                     SimpleDateFormat originalFormat;
            java.util.Date date = new java.util.Date();
            System.out.println(new Timestamp(date.getTime()));
            Timestamp mod_date = new Timestamp(date.getTime());

             
                String sql_update_mod_date = "UPDATE application set date_modified=?  where id='" + rs_app.getInt(1) + "'";
                
                PreparedStatement pst_update_mod_date = con.prepareStatement(sql_update_mod_date);
                pst_update_mod_date.setTimestamp(1, mod_date);
                System.out.println("sql_update_mod_date-----------"+sql_update_mod_date);
                pst_update_mod_date.executeUpdate();
            }catch(Exception exp){
              System.out.println("Exception-----------"+exp);
            }
            
            
            con.close();
            return SUCCESS;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception" + e);
            return "error";
        } finally {
            try {

                stmt.close();
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
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;//To change body of generated methods, choose Tools | Templates.
    }
}
