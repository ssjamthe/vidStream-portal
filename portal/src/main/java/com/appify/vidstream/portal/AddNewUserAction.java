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
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Nileh Diore
 */
public class AddNewUserAction extends ActionSupport implements
        ServletRequestAware {
    
    public AddNewUserAction() {
    }
    
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
    private String username, password, user_role,sql_image;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
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
            String usernme, pwd, urole;

            usernme = username;
            pwd = password;
            urole = user_role;
            Integer.parseInt(urole);
            con = conn.appify.vidstream.portal.DataConnection.getConnection();

            sql_image = "INSERT INTO images(name,contents) values(?,?)";
            sql = "INSERT INTO portal_user(user_name,password,role_id,user_image_id) VALUES (?,?,?,?)";
            prest = con.prepareStatement(sql);
            prest_1 = con.prepareStatement(sql_image);
            prest_1.setString(1, usernme);
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
            String sql_img_id = "select id from images where name='" + usernme + "' order by id desc limit 1";
            PreparedStatement prepareStatement = con.prepareStatement(sql_img_id);
            ResultSet executeQuery = prepareStatement.executeQuery();
            executeQuery.next();
            int img_id = executeQuery.getInt(1);

            prest.setString(1, usernme);
            prest.setString(2, pwd);
            prest.setInt(3, Integer.parseInt(urole));
             prest.setInt(4, img_id);
            prest.executeUpdate();
            System.out.println("User added successfully");
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