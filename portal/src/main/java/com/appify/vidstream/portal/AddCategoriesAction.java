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
public class AddCategoriesAction extends ActionSupport implements
        ServletRequestAware {

    private File[] fileUpload;
    private String[] fileUploadFileName;
    private String[] fileUploadContentType;
    private String saveDirectory;
    File uploadedFile, destFile, destFile1;
    String fileName, fileName1;
    FileInputStream fis, fis1;
    File uploadedFile1;
    private HttpServletRequest servletRequest;
    private Connection con;
    private ResultSet rs_st, rs_img;
    PreparedStatement prest, prest_up, prest_img;
    Statement stmt;
    private String cat_name, cat_goriesnew, sql, sub_categories, sql_img, categorization_id;
    private String parent_cat_name, new_inserted_child_category_id;

    public String getParent_cat_name() {
        return parent_cat_name;
    }

    public void setParent_cat_name(String parent_cat_name) {
        this.parent_cat_name = parent_cat_name;
    }

    public String getCategorization_id() {
        return categorization_id;
    }

    public void setCategorization_id(String categorization_id) {
        this.categorization_id = categorization_id;
    }

    public String getSub_categories() {
        return sub_categories;
    }

    public void setSub_categories(String sub_categories) {
        this.sub_categories = sub_categories;
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

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_goriesnew() {
        return cat_goriesnew;
    }

    public void setCat_goriesnew(String cat_goriesnew) {
        this.cat_goriesnew = cat_goriesnew;
    }

    public AddCategoriesAction() {
    }

    @Override
    public String execute() throws Exception {
        con = null;
        sql = null;
        prest = null;

        try {
            String apn, cat_name2, cat_gir, prntcat;
            System.err.println("parent_cat_name::::::::::::::-" + parent_cat_name);
            //apn = appl_name222;
            cat_gir = cat_goriesnew;
            //cat_name2 = cat_name;
            //prntcat = sub_categories;
            int cat_id = Integer.parseInt(categorization_id);

            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            sql_img = "INSERT INTO images(name,contents) values(?,?)";
            prest_img = con.prepareStatement(sql_img);
            prest_img.setString(1, cat_gir);
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
            String sql_img_id = "select id from images where name='" + cat_gir + "' order by id desc limit 1";
            PreparedStatement prepareStatement = con.prepareStatement(sql_img_id);
            ResultSet executeQuery = prepareStatement.executeQuery();
            executeQuery.next();
            int img_id = executeQuery.getInt(1);



            sql = "INSERT INTO category(name,image,categorization_id) VALUES (?,?,?)";

            prest = con.prepareStatement(sql);
            prest.setString(1, cat_gir);
            prest.setInt(2, img_id);

            prest.setInt(3, cat_id);
            prest.executeUpdate();
            System.out.println("Parent Categories added successfully");

            try {
                String SQl_get_categorization = "SELECT categorization_id FROM category where name='" + cat_gir + "'";
                PreparedStatement pst_get_catzion = con.prepareStatement(SQl_get_categorization);
                ResultSet rs_get_catzion = pst_get_catzion.executeQuery();
                rs_get_catzion.next();

                String SQl_getappid = "select app_id from categorization where id='" + rs_get_catzion.getInt(1) + "'";
                PreparedStatement pst_getappid = con.prepareStatement(SQl_getappid);
                ResultSet rs_getappid = pst_getappid.executeQuery();
                rs_getappid.next();
                SimpleDateFormat originalFormat;
                java.util.Date date = new java.util.Date();
                System.out.println(new Timestamp(date.getTime()));
                Timestamp mod_date = new Timestamp(date.getTime());
                String sql_update_mod_date = "UPDATE categorization set date_modified=?  where id='" + rs_get_catzion.getInt(1) + "'";

                PreparedStatement pst_update_mod_date = con.prepareStatement(sql_update_mod_date);
                pst_update_mod_date.setTimestamp(1, mod_date);
                System.out.println("sql_update_mod_date-----------" + sql_update_mod_date);
                pst_update_mod_date.executeUpdate();

                String sql_app_update_mod_date = "UPDATE application set date_modified=?  where id='" + rs_getappid.getInt(1) + "'";

                PreparedStatement pst_update_appmod_date = con.prepareStatement(sql_app_update_mod_date);
                pst_update_appmod_date.setTimestamp(1, mod_date);
                System.out.println("sql_update_mod_date-----------" + sql_app_update_mod_date);
                pst_update_appmod_date.executeUpdate();
            } catch (Exception exp) {
                System.out.println("Exception----" + exp);
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
    public void setServletRequest(HttpServletRequest hsr) {
        this.servletRequest = servletRequest; //To change body of generated methods, choose Tools | Templates.
    }
}