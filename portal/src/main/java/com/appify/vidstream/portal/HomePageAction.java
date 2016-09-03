/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Nileh Diore
 */
public class HomePageAction extends ActionSupport {
    
    public HomePageAction() {
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
       
    }
}