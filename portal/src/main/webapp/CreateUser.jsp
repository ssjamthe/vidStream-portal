<%-- 
    Document   : CreateApplication
    Created on : Apr 22, 2016, 12:11:11 AM
    Author     : Nileh Diore
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/jquery-ui.js" type="text/javascript"></script>
        <link href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/blitzer/jquery-ui.css"
              rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="css/styles.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
        <title>Create User </title>
        <script src="http://code.jquery.com/jquery-1.8.3.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/demos.css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,600,400' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Merriweather+Sans' rel='stylesheet' type='text/css'>
          <style>


            .breadcrumb {
                /*centering*/
                display: inline-block;
                box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.35);
                overflow: hidden;
                border-radius: 5px;
                /*Lets add the numbers for each link using CSS counters. flag is the name of the counter. to be defined using counter-reset in the parent element of the links*/
                counter-reset: flag; 
            }

            .breadcrumb a {
                text-decoration: none;
                outline: none;
                display: block;
                float: left;
                font-size: 12px;
                line-height: 36px;
                color: white;
                /*need more margin on the left of links to accomodate the numbers*/
                padding: 0 10px 0 60px;
                background: #666;
                background: linear-gradient(#666, #333);
                position: relative;
            }
            /*since the first link does not have a triangle before it we can reduce the left padding to make it look consistent with other links*/
            .breadcrumb a:first-child {
                padding-left: 46px;
                border-radius: 5px 0 0 5px; /*to match with the parent's radius*/
            }
            .breadcrumb a:first-child:before {
                left: 14px;
            }
            .breadcrumb a:last-child {
                border-radius: 0 5px 5px 0; /*this was to prevent glitches on hover*/
                padding-right: 20px;
            }

            /*hover/active styles*/
            .breadcrumb a.active, .breadcrumb a:hover{
                background: #333;
                background: linear-gradient(#333, #000);
            }
            .breadcrumb a.active:after, .breadcrumb a:hover:after {
                background: #333;
                background: linear-gradient(135deg, #333, #000);
            }

            /*adding the arrows for the breadcrumbs using rotated pseudo elements*/
            .breadcrumb a:after {
                content: '';
                position: absolute;
                top: 0; 
                right: -18px; /*half of square's length*/
                /*same dimension as the line-height of .breadcrumb a */
                width: 36px; 
                height: 36px;
                /*as you see the rotated square takes a larger height. which makes it tough to position it properly. So we are going to scale it down so that the diagonals become equal to the line-height of the link. We scale it to 70.7% because if square's: 
                length = 1; diagonal = (1^2 + 1^2)^0.5 = 1.414 (pythagoras theorem)
                if diagonal required = 1; length = 1/1.414 = 0.707*/
                transform: scale(0.707) rotate(45deg);
                /*we need to prevent the arrows from getting buried under the next link*/
                z-index: 1;
                /*background same as links but the gradient will be rotated to compensate with the transform applied*/
                background: #666;
                background: linear-gradient(135deg, #666, #333);
                /*stylish arrow design using box shadow*/
                box-shadow: 
                    2px -2px 0 2px rgba(0, 0, 0, 0.4), 
                    3px -3px 0 2px rgba(255, 255, 255, 0.1);
                /*
                        5px - for rounded arrows and 
                        50px - to prevent hover glitches on the border created using shadows*/
                border-radius: 0 5px 0 50px;
            }
            /*we dont need an arrow after the last link*/
            .breadcrumb a:last-child:after {
                content: none;
            }
            /*we will use the :before element to show numbers*/
            .breadcrumb a:before {
                content: counter(flag);
                counter-increment: flag;
                /*some styles now*/
                border-radius: 100%;
                width: 20px;
                height: 20px;
                line-height: 20px;
                margin: 8px 0;
                position: absolute;
                top: 0;
                left: 30px;
                background: #444;
                background: linear-gradient(#444, #222);
                font-weight: bold;
            }


            .flat a, .flat a:after {
                background: white;
                color: black;
                transition: all 0.5s;
            }
            .flat a:before {
                background: white;
                box-shadow: 0 0 0 1px #ccc;
            }
            .flat a:hover, .flat a.active, 
            .flat a:hover:after, .flat a.active:after{
                background:lightskyblue;
            }


            * {margin: 0; padding: 0;}

            html, body {min-height: 100%;}

            body {



                font-family: 'Merriweather Sans', arial, verdana;
            }
        </style>
        <style>

            .button1 {
                background-color: #E3E1B8; 
                padding: 2px 4px;
                font: 13px sans-serif;
                text-decoration: none;
                border: 1px solid #000;
                border-color: #aaa #444 #444 #aaa;
                color: #ffff
            }
           

            .footer
            {
                margin-top:70px;
                text-align:center;
                color:#666;
                font:bold 14px Arial;
                margin-left: 516px;
                width: 25%;
                alignment-adjust: central;

            }
            .footer a
            {color:#999;
             text-decoration:none
            }
            .login-form
            {

                margin: 50px auto;
            }
            .header-user-dropdown {
                background-color:#00bfff;
                box-shadow:0 1px 1px #ccc;
                padding: 20px 40px;
                height: 80px;
                color: #ffffff;
                box-sizing: border-box;
            }

            label {
                display: block;
                color: #999;
            }
            .cf:before,
            .cf:after {
                content: ""; 
                display: table;
            }

            .cf:after {
                clear: both;
            }
            .cf {
                *zoom: 1;
            }
            :focus {
                outline: 0;
            }
            .loginform {
                width: 610px;
                margin: 2px auto;
                padding: 15px;
                background-color: rgba(250,250,250,0.5);
                border-radius: 5px;


            }
            .loginform ul {
                padding: 0;
                margin: 0;
            }
            .loginform li {
                display: inline;
                float: left;
            }
            .loginform input:not([type=submit]) {
                padding: 5px;
                margin-right: 10px;
                border: 1px solid rgba(0, 0, 0, 0.3);
                border-radius: 3px;
                box-shadow: inset 0px 1px 3px 0px rgba(0, 0, 0, 0.1), 
                    0px 1px 0px 0px rgba(250, 250, 250, 0.5) ;
            }
            .loginform input[type=submit] {
                border: 1px solid rgba(0, 0, 0, 0.3);
                background: #64c8ef; /* Old browsers */
                background: -moz-linear-gradient(top,  #64c8ef 0%, #00a2e2 100%); /* FF3.6+ */
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#64c8ef), color-stop(100%,#00a2e2)); /* Chrome,Safari4+ */
                background: -webkit-linear-gradient(top,  #64c8ef 0%,#00a2e2 100%); /* Chrome10+,Safari5.1+ */
                background: -o-linear-gradient(top,  #64c8ef 0%,#00a2e2 100%); /* Opera 11.10+ */
                background: -ms-linear-gradient(top,  #64c8ef 0%,#00a2e2 100%); /* IE10+ */
                background: linear-gradient(to bottom,  #64c8ef 0%,#00a2e2 100%); /* W3C */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#64c8ef', endColorstr='#00a2e2',GradientType=0 ); /* IE6-9 */
                color: #fff;
                padding: 5px 15px;
                margin-right: 0;
                margin-top: 15px;
                border-radius: 3px;
                text-shadow: 1px 1px 0px rgba(0, 0, 0, 0.3);
            }
            .loginform input:not([type=reset]) {
                padding: 5px;
                margin-right: 10px;
                border: 1px solid rgba(0, 0, 0, 0.3);
                border-radius: 3px;
                box-shadow: inset 0px 1px 3px 0px rgba(0, 0, 0, 0.1), 
                    0px 1px 0px 0px rgba(250, 250, 250, 0.5) ;
            }
            .loginform input[type=reset] {
                border: 1px solid rgba(0, 0, 0, 0.3);
                background: #64c8ef; /* Old browsers */
                background: -moz-linear-gradient(top,  #64c8ef 0%, #00a2e2 100%); /* FF3.6+ */
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#64c8ef), color-stop(100%,#00a2e2)); /* Chrome,Safari4+ */
                background: -webkit-linear-gradient(top,  #64c8ef 0%,#00a2e2 100%); /* Chrome10+,Safari5.1+ */
                background: -o-linear-gradient(top,  #64c8ef 0%,#00a2e2 100%); /* Opera 11.10+ */
                background: -ms-linear-gradient(top,  #64c8ef 0%,#00a2e2 100%); /* IE10+ */
                background: linear-gradient(to bottom,  #64c8ef 0%,#00a2e2 100%); /* W3C */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#64c8ef', endColorstr='#00a2e2',GradientType=0 ); /* IE6-9 */
                color: #fff;
                padding: 5px 15px;
                margin-right: 0;
                margin-top: 15px;
                border-radius: 3px;
                text-shadow: 1px 1px 0px rgba(0, 0, 0, 0.3);
            }

        </style>
        <script type="text/javascript">
            function checksize() {
               

                var pic = document.getElementById("fileUpload");
              
                if (pic.files[0].size >= 1000020) {
                       alert('Images Size Should Be Less Than 1MB');
                    return false;
                }
                else {

                }
            }

        </script>
    </head>

    <body>
        <%

            ArrayList user_role_list = (ArrayList) session.getAttribute("list_user_role");
              session.removeAttribute("nested_list");
            session.removeAttribute("category_name");

        %>
        <div class="container" style="width:100%;">

         
            <%@include file="HeaderAndMenu.jsp"%>
            <br>
            
<div class="breadcrumb flat">
                <a href="login">Home</a>
                <a href="useraction" class="active" >Create User</a>

            </div>
            <div class="row">
                <div class="col-sm-3 col-md-3 col-lg-3 ">

                </div>
                <div class="col-sm-6 col-md-6 col-lg-6 ">
                    <div class="panel panel-primary " style="overflow-y: auto; font-size: 9pt;text-align: center">
                        <div class="panel-heading" >
                            <center>  <h4 class="panel-title">Create New User</h4></center>
                        </div>
                        <form action="add_user"  method="post" enctype="multipart/form-data">

                            <center>

                                <div class="step well" style="">
                                    <table class="table table-striped" style="font-size: 10pt;" height="50%">
                                        <tr class="primary">
                                            <td>
                                                <b>Enter UserName:</b>
                                            </td>
                                            <td>
                                                <input type="text" class="form-control input-sm"  name="username" value="" id="username" 
                                                       placeholder="Enter UserName"    required="true" style="width: 100%" />
                                            </td>
                                        </tr>

                                        <tr class="primary">
                                            <td>
                                                <b>Password :</b>
                                            </td>
                                            <td >
                                                <input type="text" class="form-control input-sm"  name="password" value="" id="password" 
                                                       placeholder="Enter Password"    required="true" style="width: 100%" />

                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <b>Select Role :</b>
                                            </td>
                                            <td >
                                                <select class="form-control input-sm" name="user_role" id="user_role" 
                                                        style="width: 100%" required="true">
                                                    <option>Select</option>
                                                    <%
                                                        int counter = 0;
                                                        for (int j = 0; j < (user_role_list.size()) / 2; j++) {
                                                    %>
                                                    <option value="<%=user_role_list.get(counter)%>">
                                                        <%
                                                            out.print(user_role_list.get(++counter));
                                                            counter++;
                                                        %>
                                                    </option>
                                                    <%}%>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr class="primary">
                                            <td> <b>User Image:</b> </td>
                                            <td>
                                                <input type="file" name="fileUpload" id="fileUpload"  onchange="checksize();"/>
                                            </td>
                                        </tr>

                                        <tr class="primary" >

                                            <td>
                                                <button class="btn btn-info" type="submit" name="submitButton" value="Submit" onclick="return checksize();" >Submit</button></td>
                                            <td> <button class="btn btn-info" type="reset" >Reset</button>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </center>
                        </form>
                    </div>

                </div>
            </div>

        </div>
    </body>
</html>
