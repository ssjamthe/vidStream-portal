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
public class UpdateCategoriesAction extends ActionSupport implements
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
    private String Edit_category_ID_NEW, hidden_Edit_category_ID_NEW, Edit_category_Name;

    public String getEdit_category_ID_NEW() {
        return Edit_category_ID_NEW;
    }

    public void setEdit_category_ID_NEW(String Edit_category_ID_NEW) {
        this.Edit_category_ID_NEW = Edit_category_ID_NEW;
    }

    public String getHidden_Edit_category_ID_NEW() {
        return hidden_Edit_category_ID_NEW;
    }

    public void setHidden_Edit_category_ID_NEW(String hidden_Edit_category_ID_NEW) {
        this.hidden_Edit_category_ID_NEW = hidden_Edit_category_ID_NEW;
    }

   

    public String getEdit_category_Name() {
        return Edit_category_Name;
    }

    public void setEdit_category_Name(String Edit_category_Name) {
        this.Edit_category_Name = Edit_category_Name;
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
            

            String updte_cat_name = getEdit_category_Name();

            String catId = getHidden_Edit_category_ID_NEW();
            System.out.println("catId::::::::::::" + catId);


            if (fileUpload == null) {
                String sql = "update category set id=?,name=?,date_modified=?  where id='" + catId + "' ";

                prest = con.prepareStatement(sql);

                prest.setInt(1, Integer.parseInt(catId));
                prest.setString(2, updte_cat_name);
                prest.setTimestamp(3, mod_date);
                prest.executeUpdate();
            } else {
                String sql = "update category set id=?,name=?,date_modified=?,image=? where id='" + catId + "' ";

                prest = con.prepareStatement(sql);
                prest.setInt(1, Integer.parseInt(catId));
                prest.setString(2, updte_cat_name);
                 prest.setTimestamp(3, mod_date);


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

                prest.setInt(4, img_id);
                
                prest.executeUpdate();
                System.out.println("Update in category table");

            }



        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR;
        } finally {
            try {
                prest.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest; //To change body of generated methods, choose Tools | Templates.
    }
}