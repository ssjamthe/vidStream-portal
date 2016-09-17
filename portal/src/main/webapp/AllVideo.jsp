<%-- 
    Document   : ListAllCategorization
    Created on : Mar 12, 2016, 12:14:33 PM
    Author     : Nileh Diore
--%>

<%@page import="javax.sound.midi.SysexMessage"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="css/login-form.css" media="screen">

        <!-- Latest compiled and minified CSS -->
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/jquery-ui.js" type="text/javascript"></script>
        <link href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/blitzer/jquery-ui.css"
              rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="css/styles.css">
        <script type="text/javascript" src="lib/js/underscore-min.js"></script>
        <script type="text/javascript" src="lib/js/backbone-min.js"></script>
        <script type="text/javascript" src="lib/js/pretty-json-min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
        <title>List All Videos </title>
        <script src="http://code.jquery.com/jquery-1.8.3.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/demos.css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,600,400' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Merriweather+Sans' rel='stylesheet' type='text/css'>
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

            body {
                text-align: center;
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

        <script>
            var tk = 1;
            var tag = document.createElement('script');

            tag.src = "https://www.youtube.com/iframe_api";
            var firstScriptTag = document.getElementsByTagName('script')[0];
            firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

            // 3. This function creates an <iframe> (and YouTube player)
            //    after the API code downloads.
            var player;

            function onYouTubeIframeAPIReady() {

                player = new YT.Player('player', {
                    height: '250',
                    width: '250',
                    videoId: 'DR31sQoruts',
                    events: {
                        'onReady': onPlayerReady,
                        'onStateChange': onPlayerStateChange
                    }
                });
            }
            // 4. The API will call this function when the video player is ready.
            function onPlayerReady(event) {
                event.target.playVideo();
            }

            var done = false;
            function onPlayerStateChange(event) {
                if (event.data == YT.PlayerState.PLAYING && !done) {
                    setTimeout(stopVideo, 6000);
                    done = true;
                }
            }
            function stopVideo() {
                player.stopVideo();
            }

        </script>
        <script type="text/javascript">

            function Map_video(categorization, catgory, vid, vname, date) {
                getListAllCategorization();

                var originalContent;
            <%-- for old dialog   
                            $("#diolog_Map_video1").dialog({
                                create: function() {
                                    $(this).closest('div.ui-dialog').find('.ui-dialog-titlebar-close')
                                            .click(function(e) {
                                        $(this).dialog('destroy').remove()
                                        // document.getElementById("fooBar").innerHTML = "";
                                        //  $('#fooBar').empty();
                                        $('#diolog_Map_video1').dialog('destroy').remove();

                            //$('#Map_videos_Categorization_Name').val('');
                            //  $('#Map_videos_Video_Category').val('');

                            e.preventDefault();
                        });
                    },
                    modal: true,
                    autoOpen: false,
                    closeOnEscape: false,
                    title: "Map Video",
                    width: 520,
                    height: 320
                }).dialog("open");
                $("#Map_video_ID").val(vid);
            --%>
                    $("#diolog_Map_video").dialog({
                        //Your Code, append following code
                        title: "Map Video",
                        width: 520,
                        height: 420,
                        modal: true,
                        open: function(event, ui) {
                            originalContent = $("#diolog_Map_video").html();

                        },
                        close: function(event, ui) {
                            $("#diolog_Map_video").html(originalContent);
                            tk = 1;

                        }
                    });
                    $("#Map_video_ID").val(vid);

                }

                function cleardata() {
                    $("#hyv-watch-related").html("");

                    document.getElementById('hyv-search').value = "";
                    document.getElementById("allbuttonsvideo").innerHTML = "";

                }

        </script>

        <style>
            .circular {

                border-radius: 150px;
                -webkit-border-radius: 150px;
                -moz-border-radius: 150px;
            }
            .ui-dialog-titlebar {
                background-color:#00bfff;
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

        </style>
        <script>
            var data_list = [];
            var b = [];


            <%
                ArrayList d = new ArrayList();
                String map_app_id = (String) session.getAttribute("map_vido_app_id");
                d = (ArrayList) session.getAttribute("all_video_list_main");

                for (int i = 0; i < d.size(); i++) {%>
            data_list.push("<%= d.get(i)%>");
            <%
                //System.out.println("inside Allvideo.jsp >" + d.get(i));%>
            <%}%>

        </script>


        <style>
            .styled-select select {
                background: transparent;
                width: 268px;
                padding: 5px;
                font-size: 16px;
                line-height: 1;
                border: 0;
                border-radius: 0;
                height: 34px;
                -webkit-appearance: none;
            }

        </style>
        <script>

            $(function() {
                add_data();
                add();
            });

            function add_data() {
                var j = 0;

                for (var i = 0; i < data_list.length / 5; i++) {
                    var sd = j;
                    var sv = ++j;
                    var sz = ++j;
                    var dt = ++j;
                    var ct = ++j;
                    db.clients1 =
                            {
                                "Categorization": data_list[sd],
                                "Category": data_list[ct],
                                "Video_ID": data_list[sv],
                                "Thumbnail": "<img src=\"http:\/\/img.youtube.com\/vi\/" + data_list[sv] + "\/default.jpg\">",
                                "Video_Title": data_list[sz],
                                "Date_Added": data_list[dt]
                            };

                    j++;
                    b.push(db.clients1);

                    //  add(b);
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
                        {name: "Categorization", type: "text", width: 30,title: "Categorization",align: "center"},
                        {name: "Category", type: "text", width: 30,title: "Category",align: "center"},
                        {name: "Video_ID", type: "text", width: 30,title: "Video ID",align: "center"},
                        {name: "Thumbnail", type: "text", width: 40,title: "Thumbnail",align: "center"},
                        {name: "Video_Title", type: "text", width: 50,title: "Video Title",align: "center"},
                        {name: "Date_Added", type: "text", width: 30,title: "Date Added",align: "center"},
                        {itemTemplate: function(_, item) {
                                return $("<button >").attr("type", "button").text("Play Video").addClass("btn btn-primary").attr("data-toggle", "modal").attr("data-target", "#myModal")
                                        .on("click", function() {

                                    set_url(item.Video_ID);
                                })
                            },
                            align: "center",
                            width: 50
                        },
                        {itemTemplate: function(_, item) {
                                return $("<button >").attr("type", "button").text("Map Video").addClass("btn btn-primary")
                                        .on("click", function() {

                                    Map_video(item.Categorization, item.Category, item.Video_ID, item.Video_Title, item.Date_Added);
                                })
                            },
                            align: "center",
                            width: 50
                        },
                    ]

                });

                $("#pager").on("change", function() {
                    var page = parseInt($(this).val(), 10);
                    $("#jsGrid").jsGrid("openPage", page);
                });

            }
            function set_url(vid) {
                var url_vid = "http://www.youtube.com/embed/" + vid + "?rel=0&wmode=transparent"
                document.getElementById('youtube-player').src = url_vid;

            }


            jQuery(".modal-backdrop, #myModal .close, #myModal .btn").live("click", function() {
                jQuery("#myModal iframe").attr("src", jQuery("#myModal iframe").attr("src"));
            });

            function getListAllCategorization() {
                var mapapid = document.getElementById("Map_video_AppId").value;
                document.getElementById("submitButton").disabled = true;
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/getAppCategorizationServlet?app_id=" + mapapid,
                    success: function(data) {
                        $("#Map_videos_Categorization_Name").html(data);
                    }
                });
            }


            function getListAllTopLevelCategories() {
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/getAppTopLevelcategoryServlet?categorization_id=" + $('select[name=Map_videos_Categorization_Name] option:selected').val(),
                    success: function(data) {

                        $("#Map_videos_Video_Category").html(data);
                    }
                });

            }
            var alldata;
            var child_flaf = true;
            function clearBox(elementID)
            {
                document.getElementById(elementID).innerHTML = "";
            }

            var t = 1;
            var list;
            function myFunction(tk) {
                list = document.getElementById("selectbox_" + tk);
                list.removeChild(list.childNodes[tk]);
            }
            function getListAllChildCategories() {
                change_button_status();
                var ik;
                var element;
                var foo;
                var item;
                var sd;

                document.getElementById("fooBar").innerHTML = "";
                if (tk >= 2) {
                    myFunction(tk);
                }
                $.ajax({
                    type: "GET",
                    dataType: 'json',
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    url: "${pageContext.request.contextPath}/getChildLevelCategoriesServlet?top_level_parent_category=" + $('select[name=Map_videos_Video_Category] option:selected').val(),
                    success: function(data) {
                        alldata = data;
                        if (alldata.length > 0) {
                            document.getElementById("submitButton").disabled = true;
                            element = document.createElement("select");
                            $(element).attr('id', 'selectbox_' + t);
                            $(element).attr('name', 'selectbox_' + t);
                            $(element).addClass("form-control input-sm");
                            foo = document.getElementById("fooBar");
                            // foo.appendChild(element);

                            $("#fooBar").append("<div id='test1'><label for='name_'" + t + ">Child Category</label></div>");
                            foo.appendChild(element);

                            tk++;
                            var option2 = document.createElement("option");
                            option2.text = 'Select';
                            option2.setAttribute("value", 'Select');
                            element.appendChild(option2);
                            for (var i = 0; i < data.length; i++) {
                                var option = document.createElement("option");
                                option.setAttribute("value", data[i].child_category_id);
                                option.text = data[i].child_category_name;
                                element.appendChild(option);

                            }

                            $('select').on('change', function() {
                                add1(this.value);
                                $('#last_child_category').val(this.value);
                            });

                        }
                        t++;
                    }

                });

            }
            function change_button_status() {
                document.getElementById("submitButton").disabled = false;
            }
            var al;
            var preval;
            var t = 1;
            function add1(value) {
                var catname = value;
                $.ajax({
                    type: "GET",
                    dataType: 'json',
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    url: "${pageContext.request.contextPath}/getChildLevelCategoriesServlet?top_level_parent_category=" + catname,
                    success: function(data) {
                        if (data.length > 0 && preval != catname) {
                            var element2 = document.createElement("select");

                            $(element2).attr('id', 'selectbox_' + t);
                            $(element2).attr('name', 'selectbox_' + t);
                            $(element2).addClass("form-control input-sm");

                            var foo = document.getElementById("fooBar");
                            //element.appendTo(label);

                            $("#fooBar").append('<div><label for="name">Child Category</label></div>');
                            foo.appendChild(element2);

                            var option2 = document.createElement("option");
                            option2.text = 'Select';
                            option2.setAttribute("value", 'Select');
                            element2.appendChild(option2);

                            for (var i = 0; i < data.length; i++) {
                                var option = document.createElement("option");
                                option.setAttribute("value", data[i].child_category_id);
                                option.text = data[i].child_category_name;
                                element2.appendChild(option);
                            }
                            //  $('#'+element.id).onchange(add1());

                            $('select').on('change', function() {
                                add1(this.value);
                                $('#last_child_category').val(this.value);
                            })

                        } else {
                            change_button_status();

                        }
                        preval = catname;

                        t++;
                    }
                });
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
            body {
                font-family: 'Merriweather Sans', arial, verdana;
            }
        </style>
    </head>
    <body>
        <%
            String map_vido_app_id = (String) session.getAttribute("map_vido_app_id");
            // System.out.println("inside precategorization jsp" + map_vido_app_id);
        %>

        <%@include file="HeaderAndMenu.jsp"%>

        <div class="container" style="width:100%;">
            <div class="breadcrumb flat" style="margin-top:25px; margin-left: -1100px;">
                <a href="login">Home</a>
                <a href="allvideos?app_id=<%=map_vido_app_id%>" class="active" >All Videos</a>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12 ">
                    <br>
                    <br>
                    <br>
                    <div class="container">
                        <!-- Modal -->
                        <div class="modal fade" id="myModal" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">YouTube Player</h4>
                                    </div>

                                    <iframe id='youtube-player' width="560" height="315" src="about:blank" frameborder="0" allowfullscreen></iframe>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                        </button>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>  

                    <div id="diolog_Map_video" style="display: none; font-size: 9pt; " align = "center">
                        <br>

                        <form  name ="Map_Category_wise_video" action="Map_Category_wise_video" method="post" >

                            <table class="table table-striped">
                                <tr class="info">
                                <input id="Map_video_AppId" name="Map_video_AppId" type="hidden" value="<%=map_app_id%>"style="width: 100%" required="true" />
                                <input id="Map_video_ID" name="Map_video_ID" type="hidden" style="width: 100%" required="true" />
                                <input id="last_child_category" name="last_child_category" type="hidden" style="width: 100%" required="true" />
                                <td> <label>Categorization :</label> </td>
                                <td>
                                    <select class="form-control input-sm"  id="Map_videos_Categorization_Name" name="Map_videos_Categorization_Name" onchange="getListAllTopLevelCategories();"> 
                                        <option>Select</option>
                                    </select>
                                </td>
                                </tr> 

                                <tr class="info">
                                    <td> <label>Category :</label> </td>
                                    <td>
                                        <select class="form-control input-sm"  id="Map_videos_Video_Category" name="Map_videos_Video_Category" onchange="getListAllChildCategories();"> 
                                            <option>Select</option>
                                        </select>
                                    </td>
                                </tr> 

                                <tr class="info">
                                    <td></td>
                                    <td>

                                        <div id="fooBar">

                                        </div>

                                    </td>

                                </tr>
                                <tr>

                                </tr>
                                <tr class="info">
                                    <td></td>
                                    <td><button class="btn btn-info"  id="submitButton" type="submit" name="submitButton" value="Submit" >Map</button></td>

                                </tr>

                            </table>



                        </form>
                    </div>

                    <div class="panel panel-primary" style="overflow-y: auto; font-size: 9pt; margin-top: -50px;height: 535px; ">
                        <div class="panel-heading" >
                            <center>  <h4 class="panel-title">All Video Details</h4></center>
                        </div>
                        <div class="panel-body">
                            <div style="height: 400px; ">
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
                                <div id="jsGrid"  style="font-style: black; text-align: center;" >
                                </div>
                            </div>

                        </div>
                    </div>


                </div>
            </div>
        </div>
    </body>
</html>
