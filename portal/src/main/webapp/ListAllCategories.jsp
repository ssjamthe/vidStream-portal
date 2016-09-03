<%-- 
    Document   : ListAllCategorization
    Created on : Mar 12, 2016, 12:14:33 PM
    Author     : Nileh Diore
--%>

<%@page import="org.w3c.dom.Document"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/jquery-ui.js" type="text/javascript"></script>
        <link href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/blitzer/jquery-ui.css"
              rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="css/styles.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
        <title>List All Categories </title>
        <script src="http://code.jquery.com/jquery-1.8.3.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/demos.css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,600,400' rel='stylesheet' type='text/css'>
        <script src="http://thecodeplayer.com/uploads/js/prefixfree-1.0.7.js" type="text/javascript" type="text/javascript"></script>
        <script src="js/db.js"></script>
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
            @import url(http://fonts.googleapis.com/css?family=Merriweather+Sans);

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



            /* old IE */
            .jsgrid-load-panel {
                padding-top: 1.5em\9;
            }
            .jsgrid-load-panel:before {
                display: none\9;
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

        </style>
        <style>
            .button1 {
                background-color: #E3E1B8; 
                padding: 2px 4px;
                font: 13px sans-serif;
                text-decoration: none;
                border: 1px solid #000;
                border-color: #aaa #444 #444 #aaa;
                color: #000
            }
            .blue {

                border-radius: 250px;
                -webkit-border-radius: 250px;
                -moz-border-radius: 250px;
                width: 60px;

                color: #ffffff;
                font-family: 'Open Sans', Helvetica, sans-serif;
                background: #141517;
                background: -o-linear-gradient(top,  #141517,#141517);
                background: -ms-linear-gradient(top,  #141517,#141517);
                background: -webkit-linear-gradient(top, #141517,#141517);

                background: -moz-linear-gradient(top,  #141517,#141517);
                background: linear-gradient(to bottom, #141517,#141517);
                box-shadow: inset 0 -3px 0 #1f97c7, inset 0 -3px 3px #1f9acc, inset 0 2px 2px #9ad7ef, inset 1px 0 2px #22a4d9, inset -1px 0 2px #22a4d9, 0 1px 1px rgba(0, 0, 0, 0.1), 0 2px 2px rgba(0, 0, 0, 0.06), 0 3px 3px rgba(0, 0, 0, 0.17), 2px 1px 2px rgba(0, 0, 0, 0.05), -2px 1px 2px rgba(0, 0, 0, 0.05);
            }
            .blue1 {

                border-radius: 250px;
                -webkit-border-radius: 250px;
                -moz-border-radius: 250px;
                width: 80px;

                color: #ffffff;
                font-family: 'Open Sans', Helvetica, sans-serif;
                background: #141517;
                background: -o-linear-gradient(top,  #141517,#141517);
                background: -ms-linear-gradient(top,  #141517,#141517);
                background: -webkit-linear-gradient(top, #141517,#141517);

                background: -moz-linear-gradient(top,  #141517,#141517);
                background: linear-gradient(to bottom, #141517,#141517);
                box-shadow: inset 0 -3px 0 #1f97c7, inset 0 -3px 3px #1f9acc, inset 0 2px 2px #9ad7ef, inset 1px 0 2px #22a4d9, inset -1px 0 2px #22a4d9, 0 1px 1px rgba(0, 0, 0, 0.1), 0 2px 2px rgba(0, 0, 0, 0.06), 0 3px 3px rgba(0, 0, 0, 0.17), 2px 1px 2px rgba(0, 0, 0, 0.05), -2px 1px 2px rgba(0, 0, 0, 0.05);
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
        <script>

            var data_list = [];
            var b = [];
            var store;
            <%
                ArrayList d = new ArrayList();
                ArrayList category_list = new ArrayList();
                String app_name_new = (String) session.getAttribute("App_Name");
                  String app = (String) session.getAttribute("app_id");
                String Categorization_Name = (String) session.getAttribute("categorization_name");
                String cat_id = (String) session.getAttribute("categorization_ID");
                category_list = (ArrayList) session.getAttribute("list_category");
                d = (ArrayList) session.getAttribute("categories_list");
                ArrayList appid_list = (ArrayList) session.getAttribute("list_ap_id");
                for (int i = 0; i < d.size(); i++) {%>
            data_list.push("<%= d.get(i)%>");
            <%}%>

            $(function() {

                add_data();
                add();
            });




            function add_data() {
                var j = 0;

                for (var i = 0; i < data_list.length / 6; i++) {

                    var sy = j;
                    var sbs = ++j;
                    var catid=++j;;
                    db.clients1 =
                            {"App_id": data_list[sy],
                                "Categories": data_list[sbs].link("listvideos?categoies_Name=" + data_list[catid] + ""),
                                "Image": ('<center><img class="circular"  height="70px" width="100px" src="data:image;base64,' + data_list[++j] + '" /></center>'),
                                "No_Of_Videos": data_list[++j],
                                "Date_Created": data_list[++j],
                                "Date_Modified": data_list[++j]
                            };
                    j++;

                    b.push(db.clients1);

                    add(b);
                }

            }

            function add(a) {

                $("#jsGrid").jsGrid({
                    height: "100%",
                    width: "100%",
                    sorting: true,
                    paging: true,
                    autoload: true,
                    pageSize: 10,
                    pageButtonCount: 5,
                    data: a,
                    deleteConfirm: function(item) {
                        return "The client \"" + item.Name + "\" will be removed. Are you sure?";
                    },
                    fields: [
                        {name: "App_id", type: "text", width: 40, align: "center", title: "App id"},
                        {name: "Categories", type: "text", width: 90, align: "center", title: "Categories"},
                        {name: "Image", type: "text", width: 100, align: "center", title: "Image"},
                        {name: "No_Of_Videos", type: "text", width: 90, align: "center", title: "No Of Videos"},
                        {name: "Date_Created", type: "text", width: 80, align: "center", title: "Date Created"},
                        {name: "Date_Modified", type: "text", width: 80, align: "center", title: "Date Modified"},
                        {itemTemplate: function(_, item) {

                                return $("<button>").attr("type", "button").text("Edit").addClass("btn btn-primary")
                                        .on("click", function() {
                                    EditDetailsCategory(item.App_ID, item.Categories, item.Image);
                                });
                            },
                            align: "center",
                            width: 50

                        },
                        {itemTemplate: function(_, item) {

                                return $("<button>").attr("type", "button").text("Delete").addClass("btn btn-primary")
                                        .on("click", function() {
                                    deleted_Category(item.App_ID, item.Categories);
                                });
                            },
                            align: "center",
                            width: 50

                        }
                    ]

                });
            }

            function add_tab_Categories() {

                $("p2").click(function() {
                    $("#dialog_categories").dialog({
                        modal: true,
                        autoOpen: false,
                        title: "Add Categories",
                        width: 560,
                        height: 250

                    }).dialog("open");

                });

            }
            function getselected_value() {
                var sel_value = document.getElementById("choices_pc").value;
                if (sel_value == "Child Category")
                {
                    $("#mult").show();
                    getAlllCategory();
                }
                else {
                    $("#mult").hide();
                }

            }

            function EditDetailsCategory(appid, name, img) {

                var catgoryname_edit = name.substring(name.indexOf('</') + 1, name.indexOf('">'));
                var catgoryname_edit_new = catgoryname_edit.substring(catgoryname_edit.indexOf('>') + 1, catgoryname_edit.indexOf('<'));



                $("#dialog_edit_category").dialog({
                    modal: true,
                    autoOpen: false,
                    title: "Edit Categorization",
                    width: 600,
                    height: 360
                }).dialog("open");

                $("#Edit_category_Name").val(catgoryname_edit_new);
                $("#img1").html(img);
                $.ajax({
                    url: "${pageContext.request.contextPath}/getCategoryIdServlet?category_name=" + catgoryname_edit_new,
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    success: function(data) {
                        category_id = data[0].category_id.toString();
                        $("#Edit_category_ID_NEW").val(category_id);
                        $("#hidden_Edit_category_ID_NEW").val(category_id);
                    },
                    error: function(data, status, er) {

                    }
                });

                document.getElementById("rst").innerHTML = "";

            }

            function deleted_Category(id, name) {
                var catgoryname_edit = name.substring(name.indexOf('</') + 1, name.indexOf('">'));
                var catgoryname_edit_new = catgoryname_edit.substring(catgoryname_edit.indexOf('>') + 1, catgoryname_edit.indexOf('<'));


                $("#dialog_delete_Category").dialog({
                    modal: true,
                    autoOpen: false,
                    title: "Delete Category",
                    width: 300,
                    height: 150

                }).dialog("open");
                $.ajax({
                    url: "${pageContext.request.contextPath}/getCategoryIdServlet?category_name=" + catgoryname_edit_new,
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    success: function(data) {
                        categoryID = data[0].category_id.toString();
                        $("#hidden_category_id").val(categoryID);
                    },
                    error: function(data, status, er) {

                    }
                });

                $("#hidden_category_name").val(catgoryname_edit_new);
            }


        </script>
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


            function putImage() {
                checkEditsize();
                $("#img1").html('');
                var src = document.getElementById("userImage");
                var target = document.getElementById("target");
                showImage(src, target);


            }

            function getAlllCategory() {

                var CATID = document.getElementById("categorization_id").value;
                $.ajax({
                    url: "${pageContext.request.contextPath}/getAppCategoriesServlet?categorization_id=" + CATID,
                    type: 'POST',
                    success: function(data) {

                        $("#parent_cat_name").html(data);
                    },
                    error: function(data, status, er) {

                    }
                });
            }
        </script>

    </head>
    <body>
        <%
            String other_img_limit = (String) session.getAttribute("other_limit");
        %>
        <script>
     var otherlimit =<%=other_img_limit%>;
     function checkEditsize() {


         var pic2 = document.getElementById("userImage");

         if (pic2.files[0].size >= (otherlimit * 1024)) {
             alert('Images Size Should Be Less Than  ' + otherlimit + 'KB');
             return false;
         }
         else {

         }
     }



     function checksize() {
         var pic = document.getElementById("fileUpload");

         if (pic.files[0].size >= 1000020) {
             alert('Images Size Should Be Less Than ' + otherlimit + 'KB');
             return false;
         }
         else {

         }
     }
        </script>
        <%@include file="HeaderAndMenu.jsp"%>
        <br>
        <div class="breadcrumb flat">
            <a href="login">Home</a>
            <a href="listcategorization?app_name=<%=app%>"><%=app_name_new%></a>
            <a href="listcategories?cat_name=<%=cat_id%>" class="active"><%=Categorization_Name%></a>

        </div>

        <div class="container" style="width:100%;">
            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12 ">
                    <center>
                        <table style="margin-right:120pt; margin-top: -22px;" >
                            <tr>

                                <td>
                            <p2>
                                <button type = "button" name="button1" id="button1"  class = "btn btn-info" onclick="add_tab_Categories();" >
                                    Create Category&nbsp;<span class="glyphicon glyphicon-plus-sign"></span>
                                </button>
                            </p2>
                            </td>


                            </tr>
                        </table>

                    </center>
                    <br>


                    <div id="dialog_categories" style="display: none" align = "center">
                        <br>
                        <form action="add_categories"  method="post" enctype="multipart/form-data">
                            <table class="table table-striped">
                                <tr class="info">
                                <input id="categorization_id" name="categorization_id" type="hidden" value="<%=cat_id%>"style="width: 100%" required="true"/>
                                <input id="parent_cat_name" name="parent_cat_name" type="hidden" value="Parent Category"style="width: 100%" required="true"/>
                                <td> <label> Name:</label> </td>
                                <td>
                                    <input id="cat_goriesnew" class="form-control input-sm" name="cat_goriesnew" type="text"  required="true" style="width: 100%" />
                                </td>
                                </tr>
                                <tr class="info">
                                    <td> <label> Image:</label> </td>
                                    <td>
                                        <input type="file" class="form-control input-sm" name="fileUpload" id="fileUpload" onchange="checksize();"  required="true"/>
                                    </td>
                                </tr>

                                <tr class="info">
                                    <td ><button  type="submit" class="btn btn-info" style="margin-left: 50px;" name="submitButton" value="Submit" onclick="return checksize();">Submit</button></td>
                                    <td ><button  type="reset" class="btn btn-info" >Reset</button></td>
                                </tr>
                            </table>

                        </form>
                    </div>
                    <div id="dialog_edit_category" style="display: none; font-size: 9pt; " align = "center">
                        <br>

                        <form action="update_categories" method="post" enctype="multipart/form-data"> 
                            <table class="table table-striped">

                                <tr class="info">
                                    <td> <label>Category ID :</label> </td>
                                    <td>
                                        <input id="Edit_category_ID_NEW" name="Edit_category_ID_NEW" class="form-control input-sm" type="text" disabled="true" />
                                        <input type="hidden" id="hidden_Edit_category_ID_NEW" class="form-control input-sm"  name="hidden_Edit_category_ID_NEW" /> 
                                    </td>
                                    <td></td>
                                </tr> 

                                <tr class="info">
                                    <td> <label>Category Name :</label> </td>
                                    <td>
                                        <input id="Edit_category_Name" name="Edit_category_Name" class="form-control input-sm" type="text" required="true"  />
                                    </td>
                                    <td></td>
                                </tr> 

                                <tr class="info">
                                    <td> <label>Category Image :</label> </td>
                                    <td>
                                        <input type="file" name="fileUpload" id="userImage" onchange="putImage()" />

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
                                    <td ><button class="btn btn-info align-center"   type="submit" name="submitButton" value="Submit" onclick=" return checkEditsize();" >Submit</button></td>
                                    <td></td>

                                </tr>
                            </table>

                        </form>
                    </div>
                    <div id="dialog_delete_Category" style="display: none; font-size: 9pt;" align = "center">
                        <br>

                        <form action="delete_categories"  method="post"> 
                            <table class="table table-striped">

                                <tr>
                                    <td> <label>Are you sure to Delete ?</label></td>
                                    <td>
                                        <input type="hidden" name="hidden_category_id" id="hidden_category_id"/>
                                        <input type="hidden" name="hidden_category_name" id="hidden_category_name"/>
                                    </td>


                                </tr> 
                                <tr>
                                    <td align="center"><button  type="submit" name="submitButton" value="Submit" class="blue">Ok</button></td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div class="panel panel-primary" style="overflow-y: auto; font-size: 9pt;">
                        <div class="panel-heading" >
                            <center>  <h4 class="panel-title">Application Category Details</h4></center>
                        </div>

                        <div class="panel-body jsgrid-table" >
                            <div style="height: 350px;">
                                <div id="jsGrid"  style="font-style: black; text-align: center;" >
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <%
                String app_name = (String) session.getAttribute("App_Name");%>
            <ul class="pager">
                <li><a href="listcategorization?app_name=<%=app%>">Previous Page</a></li>

            </ul>                
        </div>
    </body>
</html>
