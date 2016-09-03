<%-- 
    Document   : HomePage
    Created on : Mar 10, 2016, 6:31:35 PM
    Author     : Nileh Diore
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/jquery-ui.js" type="text/javascript"></script>
        <link href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/blitzer/jquery-ui.css"
              rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" type="text/css" href="css/jsgrid.min.css" />
        <link rel="stylesheet" type="text/css" href="css/jsgrid-theme.min.css" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/cupertino/jquery-ui.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
        <title>HomePage </title>
        <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/demos.css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,600,400' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Merriweather+Sans' rel='stylesheet' type='text/css'>
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.4.1/jsgrid.min.css" />
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.4.1/jsgrid-theme.min.css" />
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.4.1/jsgrid.min.js"></script>
        <script src="js/db.js"></script>
        <script src="js/jsgrid.min.js"></script>
        <script src="js/i18n/jsgrid-fr.js"></script>

        <script src="js/jsgrid.core.js"></script>
        <script src="js/jsgrid.load-indicator.js"></script>
        <script src="js/jsgrid.load-strategies.js"></script>
        <script src="js/jsgrid.sort-strategies.js"></script>
        <script src="js/jsgrid.field.js"></script>
        <script src="js/jsgrid.field.text.js"></script>
        <script src="js/jsgrid.field.number.js"></script>
        <script src="js/jsgrid.field.select.js"></script>
        <script src="js/jsgrid.field.checkbox.js"></script>
        <script src="js/jsgrid.field.control.js"></script>
        <style>


            * {margin: 0; padding: 0;}

            html, body {min-height: 100%;}

            body {
                text-align: center;
                padding-top: 100px;

                font-family: 'Merriweather Sans', arial, verdana;
            }

        </style>
        <style>
            .config-panel {
                padding: 10px;
                margin: 10px 0;
                background: #fcfcfc;
                border: 1px solid #e9e9e9;
                display: inline-block;
            }

            .config-panel label {
                margin-right: 10px;
            }
            .jsgrid-grid-header,
            .jsgrid-grid-body,
            .jsgrid-header-row > th,
            .jsgrid-filter-row > td,
            .jsgrid-insert-row > td,
            .jsgrid-edit-row > td {
                border: 1px solid #e9e9e9;
            }

            .jsgrid-header-row > th {
                border-top: 0;
            }

            .jsgrid-header-row > th, .jsgrid-filter-row > td, .jsgrid-insert-row > td {
                border-bottom: 0;
            }

            .jsgrid-header-row > th:first-child, .jsgrid-filter-row > td:first-child, .jsgrid-insert-row > td:first-child {
                border-left: none;
            }

            .jsgrid-header-row > th:last-child, .jsgrid-filter-row > td:last-child, .jsgrid-insert-row > td:last-child {
                border-right: none;
            }

            .jsgrid-header-row .jsgrid-align-right,
            .jsgrid-header-row .jsgrid-align-left {
                text-align: center;
            }

            .jsgrid-grid-header {
                background: #f9f9f9;
            }

            .jsgrid-header-scrollbar {
                scrollbar-arrow-color: #f1f1f1;
                scrollbar-base-color: #f1f1f1;
                scrollbar-3dlight-color: #f1f1f1;
                scrollbar-highlight-color: #f1f1f1;
                scrollbar-track-color: #f1f1f1;
                scrollbar-shadow-color: #f1f1f1;
                scrollbar-dark-shadow-color: #f1f1f1;
            }

            .jsgrid-header-scrollbar::-webkit-scrollbar {
                visibility: hidden;
            }

            .jsgrid-header-scrollbar::-webkit-scrollbar-track {
                background: #f1f1f1;
            }

            .jsgrid-header-sortable:hover {
                cursor: pointer;
                background: #fcfcfc;
            }

            .jsgrid-header-row .jsgrid-header-sort {
                background: #c4e2ff;
            }

            .jsgrid-header-sort:before {
                content: " ";
                display: block;
                float: left;
                width: 0;
                height: 0;
                border-style: solid;
            }

            .jsgrid-header-sort-asc:before {
                border-width: 0 5px 5px 5px;
                border-color: transparent transparent #009a67 transparent;
            }

            .jsgrid-header-sort-desc:before {
                border-width: 5px 5px 0 5px;
                border-color: #009a67 transparent transparent transparent;
            }

            .jsgrid-grid-body {
                border-top: none;
            }

            .jsgrid-grid-body td {
                border: #f3f3f3 1px solid;
            }

            .jsgrid-grid-body tr:first-child td {
                border-top: none;
            }

            .jsgrid-grid-body tr td:first-child {
                border-left: none;
            }

            .jsgrid-grid-body tr td:last-child {
                border-right: none;
            }

            .jsgrid-row > td {
                background: #fff; 
            }

            .jsgrid-alt-row > td {
                background: #fcfcfc;
            }

            .jsgrid-header-row > th {
                background: #f9f9f9;
            }

            .jsgrid-filter-row > td {
                background: #fcfcfc;
            }

            .jsgrid-insert-row > td {
                background: #e3ffe5;
            }

            .jsgrid-edit-row > td {
                background: #fdffe3;
            }

            .jsgrid-selected-row > td {
                background: #c4e2ff;
                border-color: #c4e2ff;
            }

            .jsgrid-nodata-row td {
                background: #fff;
            }

            .jsgrid-pager-current-page {
                font-weight: bold;
            }

            .jsgrid-pager-nav-inactive-button a {
                color: #d3d3d3;
            }

            .jsgrid-button + .jsgrid-button {
                margin-left: 5px;
            }

            .jsgrid-button:hover {
                opacity: .5;
                transition: opacity 200ms linear;
            }

            .jsgrid .jsgrid-button {
                width: 16px;
                height: 16px;
                border: none;
                cursor: pointer;
                background-image: url(images/icons.png);
                background-repeat: no-repeat;
                background-color: transparent;

            }

            @media only screen and (-webkit-min-device-pixel-ratio: 2), only screen and (min-device-pixel-ratio: 2) {
                .jsgrid .jsgrid-button {
                    background-image: url(images/icons-2x.png);
                    background-size: 24px 352px;

                }
            }

            .jsgrid .jsgrid-mode-button {
                width: 24px;
                height: 24px;
            }

            .jsgrid-mode-on-button {
                opacity: .5;
            }

            .jsgrid-cancel-button { background-position: 0 0; width: 16px; height: 16px; }
            .jsgrid-clear-filter-button { background-position: 0 -40px; width: 16px; height: 16px; }
            .jsgrid-delete-button { background-position: 0 -80px; width: 16px; height: 16px; }
            .jsgrid-edit-button { background-position: 0 -120px; width: 16px; height: 16px; }
            .jsgrid-insert-mode-button { background-position: 0 -160px; width: 24px; height: 24px; }
            .jsgrid-insert-button { background-position: 0 -208px; width: 16px; height: 16px; color: #1f97c7; }
            .jsgrid-search-mode-button { background-position: 0 -248px; width: 24px; height: 24px; }
            .jsgrid-search-button { background-position: 0 -296px; width: 16px; height: 16px; }
            .jsgrid-update-button { background-position: 0 -336px; width: 16px; height: 16px; }


            .jsgrid-load-shader {
                background: #ddd;
                opacity: .5;
                filter: alpha(opacity=50);
            }

            .jsgrid-load-panel {
                width: 15em;
                height: 5em;
                background: #fff;
                border: 1px solid #e9e9e9;
                padding-top: 3em;
                text-align: center;
            }

            .jsgrid-load-panel:before {
                content: ' ';
                position: absolute;
                top: .5em;
                left: 50%;
                margin-left: -1em;
                width: 2em;
                height: 2em;
                border: 2px solid #009a67;
                border-right-color: transparent;
                border-radius: 50%;
                -webkit-animation: indicator 1s linear infinite;
                animation: indicator 1s linear infinite;
            }

            @-webkit-keyframes indicator
            {
                from { -webkit-transform: rotate(0deg); }
            50%  { -webkit-transform: rotate(180deg); }
            to   { -webkit-transform: rotate(360deg); }
            }
            .jsgrid-load-panel:before {
                display: none\9;
            }


            /* old IE */

            .pager-panel {
                padding: 10px;
                margin: 10px 0;
                background: #fcfcfc;
                border: 1px solid #e9e9e9;
                display: inline-block;
            }

            .jsgrid {
                position: relative;
                overflow: hidden;
                font-size: 9pt;
            }

            .jsgrid, .jsgrid *, .jsgrid *:before, .jsgrid *:after {
                box-sizing: border-box;
            }

            .jsgrid input,
            .jsgrid textarea,
            .jsgrid select {
                font-size: 9pt;
            }

            .jsgrid-grid-header {
                overflow-x: hidden;
                overflow-y: scroll;
                -webkit-user-select: none;
                -khtml-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                -o-user-select: none;
                user-select: none;
            }

            .jsgrid-grid-body {
                overflow-x: auto;
                overflow-y: scroll;
                -webkit-overflow-scrolling: touch;
            }

            .jsgrid-table {
                width: 100%;
                table-layout: fixed;
                border-collapse: collapse;
                border-spacing: 0;
            }

            .jsgrid-table td {
                padding: 0.5em 0.5em;
            }

            .jsgrid-table td,
            .jsgrid-table th {
                box-sizing: border-box;
            }

            .jsgrid-align-left {
                text-align: left;
            }

            .jsgrid-align-center,
            .jsgrid-align-center input,
            .jsgrid-align-center textarea,
            .jsgrid-align-center select {
                text-align: center;
            }

            .jsgrid-align-right,
            .jsgrid-align-right input,
            .jsgrid-align-right textarea,
            .jsgrid-align-right select {
                text-align: right;
            }

            .jsgrid-header-row > th {
                padding: .5em .5em;
            }

            .jsgrid-filter-row input,
            .jsgrid-filter-row textarea,
            .jsgrid-filter-row select,
            .jsgrid-edit-row input,
            .jsgrid-edit-row textarea,
            .jsgrid-edit-row select,
            .jsgrid-insert-row input,
            .jsgrid-insert-row textarea,
            .jsgrid-insert-row select {
                width: 100%;
                padding: .3em .5em;
            }

            .jsgrid-filter-row input[type='checkbox'],
            .jsgrid-edit-row input[type='checkbox'],
            .jsgrid-insert-row input[type='checkbox'] {
                width: auto;
            }


            .jsgrid-selected-row td {
                cursor: pointer;
            }

            .jsgrid-nodata-row td {
                padding: .5em 0;
                text-align: center;
            }

            .jsgrid-header-sort {
                cursor: pointer;
            }

            .jsgrid-pager {
                padding: .5em 0;
            }

            .jsgrid-pager-nav-button {
                padding: .2em .6em;
            }

            .jsgrid-pager-nav-inactive-button {
                display: none;
                pointer-events: none;
            }

            .jsgrid-pager-page {
                padding: .2em .6em;
            }

            .pager-panel {
                padding: 10px;
                margin: 10px 0;
                background: #fcfcfc;
                border: 1px solid #e9e9e9;
                display: inline-block;
            }
        </style>

    </style>
    <style>



    </style>
    <style>
        .circular {

            border-radius: 150px;
            -webkit-border-radius: 150px;
            -moz-border-radius: 150px;
        }
        .ui-dialog-titlebar {
            background-color:#4897D8;
            background-image: none;
            color: #000;
            font-size: 9pt;

        }
        .ui-dialog .ui-dialog-titlebar-close { 
            background: none;
            border-color: transparent ;
            position: absolute; 
            right:10px;
            top: -5px; 
            width: 20px; 
            margin: 0 0 0 0; 
            padding: 1px; 
            height: 20px; 
        }


    </style>
    <script>
        var data_list = [];
        var b = [];
        var store;
        <%
            ArrayList d = new ArrayList();
            d = (ArrayList) session.getAttribute("list_app_details");
            for (int i = 0; i < d.size(); i++) {%>
        data_list.push("<%= d.get(i)%>");
        <%}%>

        $(function() {
            add_data();
            add();
        });


        function add_data() {
            var j = 0;
            for (var i = 0; i < data_list.length / 9; i++) {

                var sy = j;
                var sk = ++j;
                var k = ++j;

                db.clients1 =
                        {
                            "App_Id": data_list[sy],
                            "App_Name": data_list[sk].link("listcategorization?app_name=" + data_list[sy] + ""),
                            "App_BgImage": ('<center><img class="circular"  height="80px" width="100px" src="data:image;base64,' + data_list[k] + '" /></center>'),
                            "Status": data_list[++j],
                            "No_Of_Categorization": data_list[++j],
                            "No_Of_Categories": data_list[++j],
                            "Total_Videos": data_list[++j],
                            "Date_Created": data_list[++j],
                            "Date_Modified": data_list[++j],
                            "All_Video": "Click Here To See All Videos".link("allvideos?app_id=" + data_list[sy] + ""),
                        };
                j++;

                b.push(db.clients1);

                //add(b);
                changeval(b);
            }

        }

        function changeval(c) {
            var k;
            var t = document.getElementById("pager").value;

            if (t == 5) {
                k = 5;
                add(c, k)
            }
            else if (t == 10) {

                k = 10;
                add(c, k)
            }
            else if (t == 15) {

                k = 15;
                add(c, k)
            }
            else if (t == 20) {

                k = 20;
                add(c, k)
            }
            else if (t == 25) {

                k = 25;
                add(c, k)
            }

        }

        function add(a, v) {
           
        $("#jsGrid").jsGrid({
                height: "100%",
                width: "100%",
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: v,
                pageButtonCount: 5,
                data: a,
                fields: [
                    {name: "App_Id", type: "text", width: 40,align: "center", title: "App ID"},
                    {name: "App_Name", type: "text", width: 80,align: "center",title: "App Name"},
                    {name: "App_BgImage", width: 130,align: "center",title: "App BGImage"},
                    {name: "Status", type: "text", width: 60,align: "center",title: "Status"},
                    {name: "No_Of_Categorization", type: "text", width: 90,align: "center",title: "No Of Categorization"},
                    {name: "No_Of_Categories", type: "text", width: 80,align: "center",title: "No Of Categories"},
                    {name: "Total_Videos", type: "text", width: 60,align: "center",title: "Total Videos"},
                    {name: "Date_Created", type: "text", width: 70,align: "center",title: "Date Created"},
                    {name: "Date_Modified", type: "text", width: 70,align: "center",title: "Date Modified"},
                    {name: "All_Video", type: "text", width: 80,align: "center",title: "All Video"},
                    {itemTemplate: function(_, item) {

                            return $("<button>").attr("type", "button").text("Active").addClass("btn btn-primary")
                                    .on("click", function() {

                                showDetailsDialog(item.App_Id, item.Status);
                            })
                        },
                        align: "center",
                        width: 50
                    },
                    {itemTemplate: function(_, item) {

                            return $("<button>").attr("type", "button").text("Edit").addClass("btn btn-primary")
                                    .on("click", function() {
                                EditDetailsApplication(item.App_Id, item.App_Name, item.Status, item.App_Bg_Image);
                            });
                        },
                        align: "center",
                        width: 50

                    },
                    {itemTemplate: function(_, item) {

                            return $("<button>").attr("type", "button").text("Delete").addClass("btn btn-primary")
                                    .on("click", function() {

                                deleted_application(item.App_Id);
                            });
                        },
                        align: "center",
                        width: 50

                    }

                ]

            });
        }

        function showDetailsDialog(apid, appsta) {


            $("#diolog_active_status").dialog({
                modal: true,
                title: "Update Application Status",
                width: 340,
                height: 240
            }).dialog("open");
            $("#update_app_id").val(apid);
            $("#hidden_update_app_id").val(apid);

            $("#update_status").val(appsta);
        }


        function EditDetailsApplication(appid, name, sta, img) {
            var appname = name.substring(name.indexOf('</') + 1, name.indexOf('">'));
            var appnamenew = appname.substring(appname.indexOf('>') + 1, appname.indexOf('<'));

            $("#dialog_edit_application").dialog({
                modal: true,
                autoOpen: false,
                title: "Edit Application",
                width: 600,
                height: 360
            }).dialog("open");
            $("#appid").val(appid);
            $("#hidden_appid").val(appid);
            $("#application_Name").val(appnamenew);
            $("#previous_application_name").val(appnamenew);
            $("#Editapplication_Status").val(sta);
            $("#hidden_Editapplication_Status").val(sta);

            $("#img1").html(img);

            document.getElementById("rst").innerHTML = "";

        }

        function deleted_application(id) {

            $("#dialog_delete_Application").dialog({
                modal: true,
                autoOpen: false,
                title: "Delete Application",
                width: 300,
                height: 150

            }).dialog("open");
            $("#hidden_app_id").val(id);
        }


        function showImage(src, target) {
            var fr = new FileReader();
            fr.onload = function() {
                target.src = fr.result;
            }
            fr.readAsDataURL(src.files[0]);
        }

        function putImage() {
            $("#img1").html('');
            var src = document.getElementById("userImage");
            var target = document.getElementById("target");
            showImage(src, target);
            checksize();

        }

    </script>

</head>
<body>

    <%
        ArrayList test = new ArrayList();
        ArrayList user_role_list = (ArrayList) session.getAttribute("list_user_role");
        request.getSession().removeAttribute("nested_list");
        session.removeAttribute("nested_list");
        session.removeAttribute("category_name");
        String User_Role_Name = (String) session.getAttribute("user_role_name");
        test = (ArrayList) session.getAttribute("nested_list");
        String other_img_limit = (String) session.getAttribute("bg_limit");
        //System.out.println("other_img_limit------------------"+other_img_limit);

    %>
    <script>
        var otherlimit =<%=other_img_limit%>;
        function checksize() {
            var pic = document.getElementById("userImage");

            if (pic.files[0].size >= (otherlimit * 1024)) {
                alert('Images Size Should Be Less Thanotherlimit  ' + otherlimit + 'KB');
                return false;
            }

        }
    </script>

    <%@include file="HeaderAndMenu.jsp"%>

    <div class="container" style="width:100%;">
        <div class="row">
            <div class="col-sm-12 col-md-12 col-lg-12 ">
                <center>
                    <table style="margin-top: -58px;">

                        <tr>

                            <td>
                                <a class = "btn btn-info" href = "homepageaction" role = "button">Create New App
                                    <span class="glyphicon glyphicon-plus-sign"></span></a>
                            </td>
                            &nbsp;  &nbsp;  &nbsp;
                            <td>
                                <a class = "btn btn-info" href = "exploreutubeaction" role = "button">Explore Youtube
                                    <span class="glyphicon glyphicon-facetime-video"> </span></a>

                            </td>
                            &nbsp;  &nbsp;  &nbsp;
                            <% if (User_Role_Name.equals("Admin")) {%> 
                            <td>
                                <a class = "btn btn-info" href = "useraction" role = "button">Create User
                                    <span class="glyphicon glyphicon-plus-sign"> </span></a>
                            </td>

                            <td>
                                <a class = "btn btn-info" href = "preadd_property" role = "button">Add Property
                                    <span class="glyphicon glyphicon-plus-sign"> </span></a>
                            </td>
                            <%                                    }%> 
                          
                                   <td>
                                <a class = "btn btn-info" href = "preauthentication_token" role = "button">Create Auth Token
                                    <span class="glyphicon glyphicon-plus-sign"> </span></a>
                            </td>
                            <td>
                                 <a class = "btn btn-info" href = "list_user_feedback" role = "button">Feedback List
                                    <span class="glyphicon glyphicon-list"> </span></a>
                            </td>
                           
                        </tr>


                    </table>
                </center>

                <br>
                <div id="diolog_active_status" style="display: none; font-size: 9pt; " align = "center">
                    <br>

                    <form action="update_app_status" method="post"> 
                        <table class="table table-striped">

                            <tr class="info">
                                <td> <label>Application ID :</label> </td>
                                <td>
                                    <input id="update_app_id" name="update_app_id" type="text" disabled="true" />
                                    <input class="form-control input-sm" type="hidden" id="hidden_update_app_id"  name="hidden_update_app_id" /> 
                                </td>
                            </tr> 
                            <tr class="info">
                                <td> <label>Status :</label> </td>
                                <td><Select class="form-control input-sm" id="update_status" name="update_status"   required="true"> 
                                        <option>Active</option>   
                                    </select>
                                </td>
                            </tr>
                            <tr class="info">
                                <td ><button class="btn btn-info" style="margin-left: 50px;" type="submit" name="submitButton" value="Submit">Update</button></td>
                                <td></td>
                            </tr>
                        </table>

                    </form>
                </div>
                <div id="dialog_edit_application" style="display: none; font-size: 9pt; " align = "center">
                    <br>

                    <form action="update_application" method="post" enctype="multipart/form-data"> 
                        <table class="table table-striped">

                            <tr class="info">
                                <td> <label>Application ID :</label> </td>
                                <td>
                                    <input id="appid" name="appid" class="form-control input-sm" type="text" disabled="true" />
                                    <input type="hidden" id="hidden_appid" class="form-control input-sm"  name="hidden_appid" /> 
                                </td>
                                <td></td>
                            </tr> 
                            <tr class="info">
                                <td> <label>Application Name :</label> </td>
                            <input type="hidden" id="previous_application_name" class="form-control input-sm"  name="previous_application_name" /> 
                            <td><input id="application_Name" name="application_Name" class="form-control input-sm" type="text"  required="true"/> </td>
                            <td></td>
                            </tr>

                            <tr class="info">
                                <td> <label>Status :</label> </td>
                                <td><input id="Editapplication_Status" class="form-control input-sm" name="Editapplication_Status" type="text" value="InActive" required="true" disabled="true" />
                                    <input type="hidden" class="form-control input-sm"  name="hidden_Editapplication_Status" value="InActive" id="hidden_Editapplication_Status" 
                                           placeholder="InActive"  class="form-control input-sm"  required="true" style="width: 100%" />
                                </td>
                                <td></td>
                            </tr>

                            <tr class="info">
                                <td> <label>Application Background Image :</label> </td>
                                <td>
                                    <input type="file" name="fileUpload" id="userImage" onchange="putImage()"  />

                                </td>
                                <td> 
                                    <div>
                                        <div id="img1" name="img1">
                                        </div>
                                        <div id="rst">
                                            <img class="circular"  height="100px" width="100px" id="target"/>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr class="info">
                                <td></td>
                                <td ><button class="btn btn-info align-center"   type="submit" name="submitButton" value="Submit"  onclick=" return checksize();" >Submit</button></td>
                                <td></td>

                            </tr>
                        </table>

                    </form>
                </div>
                <div id="dialog_delete_Application" style="display: none; font-size: 9pt;" align = "center">
                    <br>

                    <form action="deleteapplication"  method="post"> 
                        <table class="table table-striped">

                            <tr>
                                <td> <label>Are you sure to Delete ?</label></td>
                                <td>
                                    <input type="hidden" name="hidden_app_id" id="hidden_app_id"/>
                                </td>

                            </tr> 
                            <tr>
                                <td align="center"><button class="btn btn-info align-center"   type="submit" name="submitButton" value="Submit">Ok</button></td>
                            </tr>
                        </table>
                    </form>
                </div>


                <div class="panel panel-primary" style="overflow-y: auto; font-size: 9pt;height: 535px; ">
                    <div class="panel-heading" >
                        <center>  <h4 class="panel-title">Applications Details</h4></center>
                    </div>
                    <div class="panel-body">
                        <div style="height: 400px;">
                            <div class="pager-panel">

                                <label>No Of Items To Be Display:

                                    <select id="pager" name="pager" onchange="changeval();">
                                        <option value="5">5</option>
                                        <option value="10">10</option>
                                        <option value="15">15</option>
                                        <option value="20">20</option>
                                        <option value="25">25</option>
                                    </select>
                                </label>
                            </div>
                            <div id="jsGrid"  style="font-style: black; text-align: center;"  >
                            </div>
                        </div>

                    </div>
                </div>
            </div>   
        </div> 

    </div>
</body>
</html>
