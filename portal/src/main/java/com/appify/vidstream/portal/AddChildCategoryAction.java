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
public class AddChildCategoryAction extends ActionSupport implements
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
    private int parent_category_ID;

    public AddChildCategoryAction() {
    }

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

    @Override
    public String execute() throws Exception {
        con = null;
        sql = null;
        prest = null;

        try {
            String apn, cat_name2, cat_gir, prntcat;


            System.err.println("parent_cat_name::::::::::::::-" + parent_cat_name);
            parent_category_ID = Integer.parseInt(parent_cat_name);
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

            try {
                String oldChar = "\'";
                String newChar = "\'\'";
                if (cat_gir.contains("'")) {
                    cat_gir = cat_gir.replace(oldChar, newChar);
                    System.out.println("New Generated Category_name" + cat_gir.replace(oldChar, newChar));
                } else {
                }
            } catch (Exception exp1) {
                System.out.println("Exception in New Generating Category_name");
            }

            String sql_img_id = "select id from images where name='" + cat_gir + "' order by id desc limit 1";
            PreparedStatement prepareStatement = con.prepareStatement(sql_img_id);
            ResultSet executeQuery = prepareStatement.executeQuery();
            executeQuery.next();
            int img_id = executeQuery.getInt(1);
            sql = "INSERT INTO category(name,image,categorization_id) VALUES (?,?,?)";
            prest = con.prepareStatement(sql);
            prest.setString(1, cat_goriesnew);
            prest.setInt(2, img_id);

            prest.setInt(3, cat_id);
            prest.executeUpdate();
            //System.out.println("sql:::" + sql);
            System.out.println("Categories added successfully");



            String get_new_child_id = "select id from category where name='" + cat_gir + "' order by id desc limit 1";
            PreparedStatement pst_get_new_child_id = con.prepareStatement(get_new_child_id);
            ResultSet rs_new_child_id = pst_get_new_child_id.executeQuery();
            rs_new_child_id.next();
            new_inserted_child_category_id = rs_new_child_id.getString(1);


            if (!parent_cat_name.equals("Parent Category")) {
                // hide from 03-Sept-16 Saturday
               /* String get_parent_category = "select id from category where name='" + parent_cat_name + "' order by id ";
                 PreparedStatement pst_get_parent_category = con.prepareStatement(get_parent_category);
                 ResultSet rs_get_parent_category = pst_get_parent_category.executeQuery();
                 rs_get_parent_category.next();
                 String new_get_parent_category_id = rs_get_parent_category.getString(1);*/


                String SQL_parent_child_category_mapping = "insert into parent_child_category_mappings(parent_category_id,child_category_id)values(?,?)";
                PreparedStatement pst_parent_child_category_mapping = con.prepareStatement(SQL_parent_child_category_mapping);
                pst_parent_child_category_mapping.setInt(1, parent_category_ID);
                pst_parent_child_category_mapping.setInt(2, Integer.parseInt(new_inserted_child_category_id));
                pst_parent_child_category_mapping.executeUpdate();
                System.out.println("parent_child_category_mappings added successfully");
            }


            try {

                boolean test_loop = true;
                boolean loop_break_test = true;
                int loop_parent_cat_id = 0;
                int check_parent_cat_id;


                SimpleDateFormat originalFormat;
                java.util.Date date = new java.util.Date();
                System.out.println(new Timestamp(date.getTime()));
                Timestamp mod_date = new Timestamp(date.getTime());

                while (test_loop) {
                    String SQl_get_Parent_cat = "SELECT parent_category_id FROM parent_child_category_mappings where child_category_id=(select id from category where name ='" + cat_gir + "')";
                    PreparedStatement pst_get_Parent_cat = con.prepareStatement(SQl_get_Parent_cat);
                    ResultSet rs_get_Parent_cat = pst_get_Parent_cat.executeQuery();
                    if (!rs_get_Parent_cat.next()) {
                        test_loop = false;
                        loop_break_test = false;
                        break;

                    } else {
                        loop_parent_cat_id = rs_get_Parent_cat.getInt(1);
                        check_parent_cat_id = loop_parent_cat_id;

                        String SQl_Upadte_Parent_cat = "UPDATE category set date_modified=? where id='" + loop_parent_cat_id + "'";
                        PreparedStatement pst_Upadte_Parent_cat = con.prepareStatement(SQl_Upadte_Parent_cat);
                        pst_Upadte_Parent_cat.setTimestamp(1, mod_date);
                        pst_Upadte_Parent_cat.executeUpdate();
                    }


                    if (loop_break_test == false) {
                        test_loop = false;
                        // inner_loop_test = false;
                        break;
                    }


                }




                String SQl_get_categorization = "SELECT categorization_id FROM category where name='" + cat_gir + "'";
                PreparedStatement pst_get_catzion = con.prepareStatement(SQl_get_categorization);
                ResultSet rs_get_catzion = pst_get_catzion.executeQuery();
                rs_get_catzion.next();

                String SQl_getappid = "select app_id from categorization where id='" + rs_get_catzion.getInt(1) + "'";
                PreparedStatement pst_getappid = con.prepareStatement(SQl_getappid);
                ResultSet rs_getappid = pst_getappid.executeQuery();
                rs_getappid.next();


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