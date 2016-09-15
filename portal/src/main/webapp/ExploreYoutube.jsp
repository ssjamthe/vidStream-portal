<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.appify.vidstream.portal.util.PortalConstants" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-gb" lang="en-gb" dir="ltr">

    <head>

        <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
                <script src="//code.jquery.com/jquery-1.12.0.min.js" type="text/javascript"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
                <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
                <meta http-equiv="content-type" content="text/html; charset=utf-8" />
                <link rel="stylesheet" href="css/login-form.css" media="screen">
                    <title>Explore Youtube </title>
                    <link rel="stylesheet" href="maincss.css" type="text/css">

                        <link rel="stylesheet" type="text/css" href="lib/pretty-json.css" />
                        <!-- Latest compiled and minified CSS -->
                        <link rel="stylesheet" href="css/login-form.css" media="screen">
                            <script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/jquery-ui.js" type="text/javascript"></script>
                            <link href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/blitzer/jquery-ui.css"
                                  rel="stylesheet" type="text/css" />
                            <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
                            <link rel="stylesheet" href=?http://w3tweaks.com/demo/youtube_search_videos/maincss.css? type="text/css" />
                            <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" type="text/css" />
                            <script src="//code.jquery.com/jquery-2.1.4.js" type="text/javascript"></script>
                            <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js" type="text/javascript"></script>
                            <!-- Underscore, backbone and pretty are used for displaying the json responce in user redable format -->
                            <script type="text/javascript" src="lib/js/underscore-min.js"></script>
                            <script type="text/javascript" src="lib/js/backbone-min.js"></script>
                            <script type="text/javascript" src="lib/js/pretty-json-min.js"></script>
                            <link rel="stylesheet" href="css/styles.css">
                                <script type="text/javascript" language="javascript"
                                src="resources/js/jquery.js"></script>
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
                                        <script type="text/javascript">
                                            var VideoId_List = [];
                                            var tk = 1;
                                           var ytkey = '<%= PortalConstants.YOUTUBE_API_KEY %>';
                                           
                                           
                                            
                                            <%
                                                ArrayList videoid_list = new ArrayList();


                                                videoid_list = (ArrayList) session.getAttribute("video_id_list");
                                                for (int i = 0; i < videoid_list.size(); i++) {%>
                                            VideoId_List.push("<%= videoid_list.get(i)%>");
                                            <%}%>



                                            $(document).ready(function() {

                                                //youtubeApiCall();
                                                $("#pageTokenNext").on("click", function(event) {
                                                    $("#pageToken").val($("#pageTokenNext").val());
                                                    youtubeApiCall();

                                                });

                                                $("#video_pager").on("click", function(event) {
                                                    $("#pageToken").val($("#video_pager").val());
                                                    youtubeApiCall();

                                                });


                                                $("#pageTokenPrev").on("click", function(event) {
                                                    $("#pageToken").val($("#pageTokenPrev").val());
                                                    youtubeApiCall();
                                                });


                                                $("#video_pager").on("click", function(event) {
                                                    $("#pageToken").val($("#pageTokenPrev").val());
                                                    youtubeApiCall();

                                                });
                                                $("#hyv-searchBtn").on("click", function(event) {
                                                    youtubeApiCall();
                                                    return false;
                                                });
                                                jQuery("#hyv-search").autocomplete({
                                                    source: function(request, response) {
                                                        //console.log(request.term);
                                                        var sqValue = [];
                                                        jQuery.ajax({
                                                            type: "POST",
                                                            url: "http://suggestqueries.google.com/complete/search?hl=en&ds=yt&client=youtube&hjson=t&cp=1",
                                                            dataType: 'jsonp',
                                                            data: jQuery.extend({
                                                                q: request.term
                                                            }, {}),
                                                            success: function(data) {
                                                                console.log(data[1]);
                                                                obj = data[1];
                                                                jQuery.each(obj, function(key, value) {
                                                                    sqValue.push(value[0]);

                                                                });
                                                                response(sqValue);

                                                            }
                                                        });
                                                    },
                                                    select: function(event, ui) {
                                                        setTimeout(function() {
                                                            youtubeApiCall();

                                                        }, 300);
                                                    }
                                                });
                                                function getVideoDetails(ids) {

                                                    $.ajax({
                                                        cache: false,
                                                        data: $.extend({
                                                            key: ytkey,
                                                            part: 'snippet,contentDetails,statistics'
                                                        }, {id: ids}),
                                                        dataType: 'json',
                                                        type: 'GET',
                                                        timeout: 5000,
                                                        fields: "items(id,contentDetails,statistics,snippet(publishedAt,channelTitle,channelId,title,description,thumbnails(medium)))",
                                                        url: 'https://www.googleapis.com/youtube/v3/videos'
                                                    })
                                                            .done(function(data) {

                                                        var items = data.items, videoList = "", newvideolist = "";
                                                        var i = 0;

                                                        $.each(items, function(index, e) {
                                                            var added;
                                                            for (var i = 0; i < VideoId_List.length; i++) {
                                                                if (VideoId_List[i].toString() == e.id.toString()) {
                                                                    added = e.id;
                                                                    $.ajax({
                                                                        url: "${pageContext.request.contextPath}/getVideCategoryServlet?videoid=" + e.id,
                                                                        type: 'POST',
                                                                        dataType: 'json',
                                                                        contentType: 'application/json',
                                                                        mimeType: 'application/json',
                                                                        success: function(data) {
                                                                            data_result_cat = data[0].Category_Name.toString();
                                                                            data_result_appname = data[0].APP_Name.toString();

                                                                        },
                                                                        error: function(data, status, er) {

                                                                        }
                                                                    });
                                                                    videoList = videoList + '<table><tr bgcolor=red;><td><input type="checkbox" id="video_id" name="video_id" value="' + e.id + '" /></td> <td><li class="hyv-video-list-item"><div class="hyv-content-wrapper"><a href  class="hyv-content-link" title="' + e.snippet.title + '"><span class="title">' + e.snippet.title + '</span><span class="stat attribution">Published Date:<span>' + e.snippet.publishedAt.replace('T', ' ').replace('Z', '') + '</span><span class="stat attribution">by <span>' + e.snippet.channelTitle + '</span></a><span class="stat attribution">Category Name: <span>' + data_result_cat + '</span><span class="stat attribution">App Name: <span>' + data_result_appname + '</span></div><div class="hyv-thumb-wrapper"><a href="" class="hyv-thumb-link"><span class="hyv-simple-thumb-wrap"><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></span></a><span class="video-time">' + YTDurationToSeconds(e.contentDetails.duration) + '</span></div></li> </td></tr></table>';

                                                                    //videoList = videoList + '<table><tr bgcolor=white;><td><input type="radio" id="video_id" name="video_id" value="' + e.id + '" /></td> <td><li class="hyv-video-list-item"><div class="hyv-content-wrapper"><a href  class="hyv-content-link" title="' + e.snippet.title + '"><span class="title">' + e.snippet.title + '</span><span Published Date::><span>' + e.snippet.publishedAt + '</span><span class="stat attribution">by <span>' + e.snippet.channelTitle + '</span></span></a></div><div class="hyv-thumb-wrapper"><a href="" class="hyv-thumb-link"><span class="hyv-simple-thumb-wrap"><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></span></a><span class="stat attribution>Category Name<span>' + result + '</span><span class="video-time">' + YTDurationToSeconds(e.contentDetails.duration) + '</span></div></li> </td></tr></table>';
                                                                } else {

                                                                }
                                                            }
                                                            if (e.id !== added) {
                                                                //videoList = videoList + '<table><tr><td><input type="radio" id="video_id" name="video_id" value="' + e.id + '" /></td> <td><li class="hyv-video-list-item"><div class="hyv-content-wrapper"><a href  class="hyv-content-link" title="' + e.snippet.title + '"><span class="title">' + e.snippet.title + '</span><span Published Date::><span>' + e.snippet.publishedAt.replace('T', ' ').replace('Z', '') + '</span><span class="stat attribution">by <span>' + e.snippet.channelTitle + '</span></span></a></div><div class="hyv-thumb-wrapper"><a href="" class="hyv-thumb-link"><span class="hyv-simple-thumb-wrap"><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></span></a><span class="video-time">' + YTDurationToSeconds(e.contentDetails.duration) + '</span></div></li> </td></tr></table>';
                                                                videoList = videoList + '<table><tr><td><input type="checkbox" id="video_id" name="video_id" value="' + e.id + '" /></td> <td><li class="hyv-video-list-item"><div class="hyv-content-wrapper"><a href  class="hyv-content-link" title="' + e.snippet.title + '"><span class="title">' + e.snippet.title + '</span><span class="stat attribution">Published Date:<span>' + e.snippet.publishedAt.replace('T', ' ').replace('Z', '') + '</span><span class="stat attribution">by <span>' + e.snippet.channelTitle + '</span></span></a></div><div class="hyv-thumb-wrapper"><a href="" class="hyv-thumb-link"><span class="hyv-simple-thumb-wrap"><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></span></a><span class="video-time">' + YTDurationToSeconds(e.contentDetails.duration) + '</span></div></li> </td></tr></table>';
                                                            }
                                                        });

                                                        $("#hyv-watch-related").html(videoList).l;
                                                    });
                                                }



                                                function YTDurationToSeconds(duration) {
                                                    var match = duration.match(/PT(\d+H)?(\d+M)?(\d+S)?/)

                                                    var hours = ((parseInt(match[1]) || 0) !== 0) ? parseInt(match[1]) + ":" : "";
                                                    var minutes = ((parseInt(match[2]) || 0) !== 0) ? parseInt(match[2]) + ":" : "";
                                                    var seconds = ((parseInt(match[3]) || 0) !== 0) ? parseInt(match[3]) : "00";
                                                    var total = hours + minutes + seconds;
                                                    return total;
                                                }

                                                function youtubeApiCall() {
                                                    var vn = document.getElementById("video_pager").value


                                                    $.ajax({
                                                        cache: false,
                                                        data: $.extend({
                                                            key: ytkey,
                                                            q: $('#hyv-search').val(),
                                                            part: 'snippet'
                                                        }, {maxResults: vn, pageToken: $("#pageToken").val()}),
                                                        dataType: 'json',
                                                        type: 'GET',
                                                        timeout: 5000,
                                                        fields: "pageInfo,items(id(videoId)),nextPageToken,prevPageToken",
                                                        url: 'https://www.googleapis.com/youtube/v3/search'
                                                    })
                                                            .done(function(data) {
                                                        $('.btn-group').show();
                                                        if (typeof data.prevPageToken === "undefined") {
                                                            $("#pageTokenPrev").hide();
                                                        } else {
                                                            $("#pageTokenPrev").show();
                                                        }
                                                        if (typeof data.nextPageToken === "undefined") {
                                                            $("#pageTokenNext").hide();
                                                        } else {
                                                            $("#pageTokenNext").show();
                                                        }
                                                        var items = data.items, videoids = [];
                                                        $("#pageTokenNext").val(data.nextPageToken);
                                                        $("#pageTokenPrev").val(data.prevPageToken);

                                                        $.each(items, function(index, e) {
                                                            videoids.push(e.id.videoId);
                                                            // alert(e.id.videoId);
                                                        });
                                                        getVideoDetails(videoids.join());

                                                    });
                                                }


                                            });

                                            var tag = document.createElement('script');

                                            tag.src = "https://www.youtube.com/iframe_api";
                                            var firstScriptTag = document.getElementsByTagName('script')[0];
                                            firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

                                            // 3. This function creates an <iframe> (and YouTube player)
                                            //    after the API code downloads.
                                            var player;

                                            function onYouTubeIframeAPIReady(vid) {
                                                $("#player").html("");
                                                //$('#player').empty();

                                                var newvid = vid.toString();

                                                player = new YT.Player('player', {
                                                    height: '250',
                                                    width: '250',
                                                    videoId: newvid,
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
                                                    setTimeout(stopVideo, 60000);
                                                    done = true;
                                                }
                                            }
                                            function stopVideo() {
                                                player.stopVideo();
                                            }



                                            var vididlist = [];
                                            function getvideoinfo(vididlist)
                                            {

                                                var array = vididlist.split(',');

                                                getvideodata(vididlist);

                                            }
                                            var vidcnt = [];
                                            var vidname = [];
                                            var vidpd = [];
                                            function getvideodata(video_id) {

                                                var video_id1 = video_id;
                                                
                                                $.ajax({
                                                    cache: false,
                                                    dataType: 'json',
                                                    type: 'GET',
                                                    timeout: 5000,
                                                    url: 'https://www.googleapis.com/youtube/v3/videos?id=' + video_id1 + '&key=' + ytkey + '&fields=items(id,snippet(publishedAt,channelId,title,categoryId),statistics)&part=snippet,statistics',
                                                    success: function(data) {
                                                        var items = data.items;
                                                        var i = 0;

                                                        $.each(items, function(index, e) {
                                                            var vcount = e.statistics.viewCount;
                                                            vidcnt.push(vcount);

                                                            var pdate = e.snippet.publishedAt.replace('T', ' ').replace('Z', '');
                                                            vidpd.push(pdate);
                                                            var str = e.snippet.title;
                                                            var regex = new RegExp(',', 'g');
                                                            str = str.replace(regex, '');
                                                            vidname.push(str);
                                                            var video_id_list = [];
                                                            video_id_list.push(video_id1, e.snippet.title, vcount, pdate);

                                                        });

                                                        showDialog(video_id1, vidname, vidcnt, vidpd);

                                                        vidcnt = [];
                                                        vidname = [];
                                                        vidpd = [];
                                                    }
                                                });


                                            }


                                            function getExplorevideoinfo(video_idNEW, category_name, application_name)
                                            {
                                                var video_id1 = video_idNEW;
                                                var catName = category_name;
                                                var Appname = application_name;
                                               
                                                $.ajax({
                                                    cache: false,
                                                    dataType: 'json',
                                                    type: 'GET',
                                                    timeout: 5000,
                                                    url: 'https://www.googleapis.com/youtube/v3/videos?id=' + video_idNEW + '&key=' + ytkey + '&fields=items(id,snippet(publishedAt,channelId,title,categoryId),statistics)&part=snippet,statistics',
                                                    success: function(data) {
                                                        var items = data.items;
                                                        var i = 0;

                                                        $.each(items, function(index, e) {
                                                            var vcount = e.statistics.viewCount;
                                                            var pdate = e.snippet.publishedAt.replace('T', ' ').replace('Z', '');

                                                            $.ajax({
                                                                type: "GET",
                                                                url: "${pageContext.request.contextPath}/getCategorizationFromCategoryServlet?category_name=" + catName,
                                                                dataType: 'json',
                                                                contentType: 'application/json',
                                                                mimeType: 'application/json',
                                                                success: function(data) {

                                                                    categorization_Name = data[0].Categorization_name.toString();
                                                                }
                                                            });
                                                            EditExploreYoutubeDeatils(video_id1, e.snippet.title, vcount, pdate, catName, Appname, categorization_Name);
                                                        });

                                                    }
                                                });
                                            }




                                        </script>
                                        <script type="text/javascript">
                                            $(document).ready(function() {
                                                $("#Exp_button_insert").click(function() {
                                                    var favorite = [];
                                                    $.each($("input[name='video_id']:checked"), function() {
                                                        favorite.push($(this).val());
                                                    });

                                                    getvideoinfo(favorite.join(", "));


                                                });
                                            });


                                            $(document).ready(function() {
                                                $("#button12").click(function() {
                                                    var favorite = [];

                                                    $.each($("input[name='video_id']:checked"), function() {
                                                        favorite.push($(this).val());
                                                        // showDialog($(this).val(),'test');
                                                        //getvideoinfo($(this).val());
                                                        onYouTubeIframeAPIReady($(this).val());
                                                        set_url($(this).val());
                                                        $("#player").html("");

                                                    });
                                                    //alert("My favourite sports are: " + favorite.join(", "));
                                                });
                                            });

                                            function set_url(vid) {

                                                var url_vid = "http://www.youtube.com/embed/" + vid + "?rel=0&wmode=transparent"

                                                document.getElementById('youtube-player').src = url_vid;
                                            }


                                            function showDialog(vid, vname, vcount, vpdate) {
                                                var originalContent;
                                                $("#diolog_add_video").dialog({
                                                    modal: true,
                                                    title: "Insert Video",
                                                    width: 460,
                                                    height: 340,
                                                    open: function(event, ui) {
                                                        originalContent = $("#diolog_add_video").html();

                                                    },
                                                    close: function(event, ui) {
                                                        $("#diolog_add_video").html(originalContent);
                                                        tk = 1;
                                                    }
                                                });
                                                $("#Explore_video_id").val(vid);
                                                $("#hidden_Explore_video_id").val(vid);
                                                $("#explore_video_title").val(vname);
                                                $("#explore_video_view_count").val(vcount);
                                                $("#explore_video_published_date").val(vpdate);
                                            }

                                            function send_data_server(vid, vname, vcount, vpdate) {

                                                $.ajax({
                                                    url: "${pageContext.request.contextPath}/AddMultipleVideoActionServlet?Explore_video_id=" + vid + "&explore_video_title=" + vname + "&explore_video_view_count=" + vcount + "explore_video_published_date" + vpdate + "&ExpYTupdate_Application_Name=" + $('select[name=ExpYTupdate_Application_Name] option:selected').val() + "&ExpYTube_Categorization=" + $('select[name=ExpYTube_Categorization] option:selected').val(),
                                                    type: 'POST',
                                                    success: function(data) {
                                                        alert("Successfully Insert Data ..");

                                                    },
                                                    error: function(data, status, er) {
                                                        alert("error while processing data..");

                                                    }
                                                });
                                            }

                                            function cleardata() {
                                                $("#hyv-watch-related").html("");

                                                document.getElementById('hyv-search').value = "";
                                                document.getElementById("allbuttonsvideo").innerHTML = "";


                                            }



                                        </script>

                                        <script type="text/javascript">
                                            var cat_video_data_list = [];
                                            var bb = [];
                                            var store;
                                            <%
                                                ArrayList dnew = new ArrayList();
                                                dnew = (ArrayList) session.getAttribute("Category_Wise_Video_List");


                                                for (int i = 0; i < dnew.size(); i++) {%>
                                            cat_video_data_list.push("<%= dnew.get(i)%>");
                                            <%}%>

                                            $(function() {
                                                add_data();
                                                add();
                                            });




                                            function add_data() {
                                                var j = 0;

                                                var sd = 0;
                                                for (var i = 0; i < cat_video_data_list.length / 5; i++) {



                                                    var sd = j;
                                                    var sv = ++j;
                                                    var sz = ++j;
                                                    var dt = ++j;
                                                    var ct = ++j;
                                                    db.clients1 =
                                                            {
                                                                "Application_Name": cat_video_data_list[sd],
                                                                "Category_Name": cat_video_data_list[ct],
                                                                "Video_ID": cat_video_data_list[sv],
                                                                "Thumbnail": "<img src=\"http:\/\/img.youtube.com\/vi\/" + cat_video_data_list[sv] + "\/default.jpg\">",
                                                                "Video_Title": cat_video_data_list[sz],
                                                                "Date_Added": cat_video_data_list[dt],
                                                            };

                                                    j++;

                                                    bb.push(db.clients1);

                                                    //add(bb);

                                                    changeval(bb);
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
                                                    deleteConfirm: function(item) {
                                                        return "The client \"" + item.Name + "\" will be removed. Are you sure?";
                                                    },
                                                    fields: [
                                                        {name: "Application_Name", type: "text", width: 40, title: "Application Name", align: "center"},
                                                        {name: "Category_Name", type: "text", width: 40, title: "Category Name", align: "center"},
                                                        {name: "Video_ID", type: "text", width: 30, title: "Video ID", align: "center"},
                                                        {name: "Thumbnail", type: "text", width: 50, title: "Thumbnail", align: "center"},
                                                        {name: "Video_Title", type: "text", width: 60, title: "Video Title", align: "center"},
                                                        {name: "Date_Added", type: "text", width: 40, title: "Date Added", align: "center"},
                                                        {itemTemplate: function(_, item) {
                                                                return $("<button >").attr("type", "button").text("Play Video").addClass("btn btn-primary").attr("data-toggle", "modal").attr("data-target", "#myModal")
                                                                        .on("click", function() {

                                                                    set_url(item.Video_ID);
                                                                })
                                                            },
                                                            align: "center",
                                                            width: 50
                                                        },<%--   {itemTemplate: function(_, item) {

                                                            return $("<button>").attr("type", "button").text("Ediit").addClass("btn btn-primary")
                                                                    .on("click", function() {
                                                                // EditExploreYoutubeDeatils(item.Category_Name, item.Video_ID, item.Video_Title);
                                                                getExplorevideoinfo(item.Video_ID, item.Category_Name, item.Application_Name);
                                                            });
                                                        },
                                                        align: "center",
                                                        width: 50

                                                    },--%>
                                                        {itemTemplate: function(_, item) {

                                                                return $("<button>").attr("type", "button").text("Delete").addClass("btn btn-primary")
                                                                        .on("click", function() {

                                                                    deleted_ExploreYoutubeDeatils(item.Video_ID,item.Application_Name,item.Category_Name);
                                                                });
                                                            },
                                                            align: "center",
                                                            width: 50

                                                        }
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



                                            function EditExploreYoutubeDeatils(vid, vname, vcount, pdate, Category_Name, AppName, Categorization_Name) {


                                                $("#dialog_edit_ExploreYoutubeDeatils").dialog({
                                                    modal: true,
                                                    autoOpen: false,
                                                    title: "Edit Explore YouTube Video",
                                                    width: 600,
                                                    height: 400
                                                }).dialog("open");

                                                $("#hidden_ExpYTupdate_video_Id").val(vid);
                                                $("#ExpYTupdate_video_Id").val(vid);
                                                $("#ExpYTupdate_Video_Name").val(vname);
                                                $("#ExpYTupdate_Video_published_date").val(pdate);
                                                $("#ExpYTupdate_Video_view_count").val(vcount);


                                                $("#Edit_ExpYTupdate_Application_Name").html("<option value='" + AppName + "'>" + AppName + "</option>");


                                                $("#ExpYTubeupdate_Categorization_Name").html("<option value='" + Categorization_Name + "'>" + Categorization_Name + "</option>");

                                                $("#ExpYTupdate_Video_Category").html("<option value='" + Category_Name + "'>" + Category_Name + "</option>");

                                            }

                                            function deleted_ExploreYoutubeDeatils(id,appname,categoryname) {


                                                $("#dialog_delete_ExploreYoutubeDeatils").dialog({
                                                    modal: true,
                                                    autoOpen: false,
                                                    title: "Delete Explore YouTube Video",
                                                    width: 300,
                                                    height: 150

                                                }).dialog("open");
                                                $("#hidden_explorevideoID").val(id);
                                                 $("#hidden_explorevideoCategoryID").val(categoryname);
                                                  $("#hidden_explorevideoAppID").val(appname);

                                            }

                                        </script>
                                        <script>
                                            $(document).ready(function() {
                                                $('.modal').each(function() {
                                                    var src = $(this).find('iframe').attr('src');

                                                    $(this).on('click', function() {

                                                        $(this).find('iframe').attr('src', '');
                                                        $(this).find('iframe').attr('src', src);

                                                    });
                                                });
                                            });

                                        </script>

                                        <style type="text/css">
                                            body{
                                                background-color: #ffffff;
                                            }
                                            .container-4 input#hyv-search {
                                                width: 500px;
                                                height: 30px;
                                                border: 1px solid #c6c6c6;
                                                font-size: 10pt;
                                                float: left;
                                                padding-left: 195px;
                                                -webkit-border-top-left-radius: 5px;
                                                -webkit-border-bottom-left-radius: 5px;
                                                -moz-border-top-left-radius: 5px;
                                                -moz-border-bottom-left-radius: 5px;
                                                border-top-left-radius: 5px;
                                                border-bottom-left-radius: 5px;
                                            }
                                            .container-4 button.icon {
                                                height: 30px;
                                                background: #f0f0f0 url('images/searchicon.png') 10px 1px no-repeat;
                                                background-size: 24px;
                                                -webkit-border-top-right-radius: 5px;
                                                -webkit-border-bottom-right-radius: 5px;
                                                -moz-border-radius-topright: 5px;
                                                -moz-border-radius-bottomright: 5px;
                                                border-top-right-radius: 5px;
                                                border-bottom-right-radius: 5px;
                                                border: 1px solid #c6c6c6;
                                                width: 50px;
                                                margin-left: -44px;
                                                color: #4f5b66;
                                                font-size: 10pt;
                                            }

                                            .button1 {
                                                background-color: #E3E1B8; 
                                                padding: 2px 4px;
                                                font: 13px sans-serif;
                                                text-decoration: none;
                                                border: 1px solid #000;
                                                border-color: #aaa #444 #444 #aaa;
                                                color: #ffff
                                            }


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

                                        </style>
                                        <script>
                                            function getAppCategorization() {
                                                document.getElementById("submitButton").disabled = true;
                                                $.ajax({
                                                    type: "GET",
                                                    url: "${pageContext.request.contextPath}/getAppCategorizationServlet?app_id=" + $('select[name=ExpYTupdate_Application_Name] option:selected').val(),
                                                    success: function(data) {

                                                        $("#ExpYTube_Categorization").html(data);
                                                    }
                                                });
                                            }

                                            function getEditAppCategorization() {

                                                $.ajax({
                                                    type: "GET",
                                                    url: "${pageContext.request.contextPath}/getAppCategorizationServlet?app_id=" + $('select[name=Edit_ExpYTupdate_Application_Name] option:selected').val(),
                                                    success: function(data) {

                                                        $("#ExpYTubeupdate_Categorization_Name").html(data);
                                                    }
                                                });
                                            }

                                            function getAppCategories() {

                                                $.ajax({
                                                    type: "GET",
                                                    url: "${pageContext.request.contextPath}/getAppCategoriesServlet?categorization_id=" + $('select[name=ExpYTube_Categorization] option:selected').val(),
                                                    success: function(data) {

                                                        $("#explore_video_category_wise").html(data);
                                                    }
                                                });
                                            }


                                            function getEditAppCategories() {

                                                $.ajax({
                                                    type: "GET",
                                                    url: "${pageContext.request.contextPath}/getAppCategoriesServlet?categorization_id=" + $('select[name=ExpYTubeupdate_Categorization_Name] option:selected').val(),
                                                    success: function(data) {

                                                        $("#ExpYTupdate_Video_Category").html(data);
                                                    }
                                                });
                                            }




                                            function getListAllTopLevelCategories() {

                                                $.ajax({
                                                    type: "GET",
                                                    url: "${pageContext.request.contextPath}/getAppTopLevelcategoryServlet?categorization_id=" + $('select[name=ExpYTube_Categorization] option:selected').val(),
                                                    success: function(data) {
                                                        $("#explore_video_category_wise").html(data);
                                                        

                                                    }
                                                });
                                            }

                                            var t = 1;

                                            var list;
                                            var alldata;
                                            function myFunction(tk) {

                                                //  t = 1;

                                                list = document.getElementById("selectbox_" + tk);

                                                // list.removeChild(list);
                                                list.removeChild(list.childNodes[tk]);
                                            }
                                            function getListAllChildCategories() {
                                                change_button_status();
                                                var ik;
                                                var element;
                                                var foo;
                                                var item;
                                                var sd;
                                                // removeElement('parent_div','fooBar');



                                                document.getElementById("fooBar").innerHTML = "";
                                                if (tk >= 2) {
                                                    myFunction(tk);
                                                }
                                                $.ajax({
                                                    type: "GET",
                                                    dataType: 'json',
                                                    contentType: 'application/json',
                                                    mimeType: 'application/json',
                                                    url: "${pageContext.request.contextPath}/getChildLevelCategoriesServlet?top_level_parent_category=" + $('select[name=explore_video_category_wise] option:selected').val(),
                                                    success: function(data) {
                                                        // document.getElementById("fooBar").remove();
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
                                                                option.setAttribute("value", data[i].child_category_name);
                                                                option.text = data[i].child_category_name;
                                                                element.appendChild(option);

                                                            }
                                                            //  $('#'+element.id).onchange(add1());


                                                            $('select').on('change', function() {
                                                                add1(this.value);
                                                                
                                                                $('#last_child_category').val(this.value);
                                                            });

                                                        }
                                                        t++;




                                                    }

                                                });

                                            }
                                            var al;
                                            var preval;
                                            var t = 1;
                                            function change_button_status() {
                                                document.getElementById("submitButton").disabled = false;
                                            }
                                            function add1(value) {
                                                var catname = value;

                                                $.ajax({
                                                    type: "GET",
                                                    dataType: 'json',
                                                    contentType: 'application/json',
                                                    mimeType: 'application/json',
                                                    url: "${pageContext.request.contextPath}/getChildLevelCategoriesServlet?top_level_parent_category=" + catname,
                                                    success: function(data) {
                                                        if (data.length > 0 && preval !== catname) {
                                                            var element = document.createElement("select");

                                                            $(element).attr('id', 'selectbox_' + t);
                                                            $(element).attr('name', 'selectbox_' + t);
                                                            $(element).addClass("form-control input-sm");
                                                            var foo = document.getElementById("fooBar");
                                                            $("#fooBar").append('<div><label for="name">Child Category</label></div>');
                                                            foo.appendChild(element);
                                                            var option2 = document.createElement("option");
                                                            option2.text = 'Select';
                                                            option2.setAttribute("value", 'Select');
                                                            element.appendChild(option2);
                                                            for (var i = 0; i < data.length; i++) {
                                                                var option = document.createElement("option");
                                                                option.setAttribute("value", data[i].child_category_name);
                                                                option.text = data[i].child_category_name;
                                                                element.appendChild(option);
                                                            }
                                                            $('select').on('change', function() {

                                                                add1(this.value);
                                                              
                                                                $('#Exp_last_child_category').val(this.value);
                                                            })

                                                        } else {

                                                            change_button_status();

                                                        }

                                                        t++;
                                                        preval = catname;

                                                    }
                                                });
                                            }


                                        </script>
                                        </head>

                                        <body>
                                            <%
                                                ArrayList cl = (ArrayList) session.getAttribute("category_idandname_list");
                                                ArrayList appnameid = (ArrayList) session.getAttribute("list_app_name");
                                                session.removeAttribute("nested_list");
                                                session.removeAttribute("category_name");
                                            %>

                                            <%@include file="HeaderAndMenu.jsp"%>
                                            <br>

                                                <div class="container" style="width:100%">
                                                    <br>
                                                        <div class="breadcrumb flat">
                                                            <a href="login">Home</a>
                                                            <a href="exploreutubeaction" class="active" >Explore Youtube</a>
                                                            
                                                        </div>
                                                        <br>
                                                            <br>
                                                                <div id="diolog_add_video" style="display: none; font-size: 9pt; " align = "center">
                                                                    <br>

                                                                        <form action="addvideocatwise" method="post"> 
                                                                            <table class="table table-striped">
                                                                                <tr class="info">
                                                                                    <td width="40%"> <label>VIDEO ID :</label> </td>
                                                                                    <td width="60%">
                                                                                        <input id="Explore_video_id" name="Explore_video_id" type="text" disabled="true" size="35" />
                                                                                        <input id="Exp_last_child_category" name="Exp_last_child_category" type="hidden" style="width: 100%" required="true" />
                                                                                        <input type="hidden" id="hidden_Explore_video_id" class="form-control input-sm"  name="hidden_Explore_video_id" size="35" /> 
                                                                                    </td>
                                                                                </tr> 

                                                                                <tr class="info">
                                                                                    <td width="40%"> <label>VIDEO TITLE :</label> </td>
                                                                                    <td width="60%"><input id="explore_video_title" name="explore_video_title" type="text" class="form-control input-sm" required="true" size="35" /> </td>

                                                                                </tr>
                                                                                <tr class="info">
                                                                                    <td width="40%"> <label>VIDEO View Count :</label> </td>
                                                                                    <td width="60%"><input id="explore_video_view_count" name="explore_video_view_count" type="text" class="form-control input-sm" required="true" size="35" /> </td>

                                                                                </tr>
                                                                                <tr class="info">
                                                                                    <td width="40%"> <label>VIDEO Published Date :</label> </td>
                                                                                    <td width="60%"><input id="explore_video_published_date" name="explore_video_published_date" type="text" class="form-control input-sm" required="true" size="35" /> </td>

                                                                                </tr>
                                                                                <tr class="info">
                                                                                    <td> <label>Application :</label> </td>
                                                                                    <td>
                                                                                        <select class="form-control input-sm"  id="ExpYTupdate_Application_Name" name="ExpYTupdate_Application_Name"  required="true" onchange="getAppCategorization();"> 
                                                                                            <option value="Select"> Select</option>
                                                                                            <%
                                                                                                int counter = 0;
                                                                                                for (int j = 0; j < (appnameid.size()) / 2; j++) {
                                                                                            %>
                                                                                            <option value="<%=appnameid.get(counter)%>">
                                                                                                <%
                                                                                                    out.print(appnameid.get(++counter));
                                                                                                    counter++;
                                                                                                %>
                                                                                            </option>
                                                                                            <%}%>
                                                                                        </select>
                                                                                    </td>
                                                                                </tr> 

                                                                                <tr class="info">
                                                                                    <td> <label>Categorization :</label> </td>
                                                                                    <td>
                                                                                        <select class="form-control input-sm"  id="ExpYTube_Categorization" name="ExpYTube_Categorization"  onchange="getListAllTopLevelCategories();"> 
                                                                                            <option>Select</option>


                                                                                        </select>
                                                                                    </td>
                                                                                </tr> 
                                                                                <tr class="info">
                                                                                    <td width="40%"> <label>Category :</label> </td>
                                                                                    <td width="60%">
                                                                                        <select id="explore_video_category_wise" class="form-control input-sm" name="explore_video_category_wise" onchange="getListAllChildCategories();" > 
                                                                                            <option> Select</option>
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


                                                                                <tr class="info">
                                                                                    <td ><button class="btn btn-info" type="submit" name="submitButton" id="submitButton"  style="margin-left: 50px;" >ADD</button></td>
                                                                                    <td></td>
                                                                                </tr>
                                                                            </table>

                                                                        </form>
                                                                </div>
                                                                <div class="row-fluid">
                                                                    <main id="content" role="main" class="span12">
                                                                        <!-- Begin Content -->
                                                                        <div id="hyv-page-container" style="clear:both;">
                                                                            <div class="hyv-content-alignment">
                                                                                <div id="hyv-page-content" class="" style="overflow:hidden;">
                                                                                    <div class="container-4" style="margin-top: 2px;">

                                                                                        <input type="search" name="hyv-search" id="hyv-search" placeholder="Search video here..." class="ui-autocomplete-input" autocomplete="off">
                                                                                            <button class="icon" id="hyv-searchBtn"></button>

                                                                                    </div>

                                                                                    <table>
                                                                                        <tr>
                                                                                            <td>
                                                                                                <div id ="allbuttonsvideo" style="margin-top: -20px;margin-left: 530px;">

                                                                                                    <input type="hidden" id="pageToken" value="">
                                                                                                        <div class="btn-group" role="group" aria-label="..." style="display:none;margin-top:-10px; margin-left:10px; ">
                                                                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                            <button type="button" id="pageTokenPrev" value="" class="btn btn-default btn-sm">
                                                                                                                <span class = "glyphicon glyphicon-chevron-left"></span> 
                                                                                                                Prev</button>

                                                                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                            <button type="button" id="pageTokenNext" value="" class="btn btn-default btn-sm">
                                                                                                                <span class = "glyphicon glyphicon-chevron-right"></span> 
                                                                                                                Next</button>

                                                                                                            <button class="btn btn-default btn-sm" id="button11" name="button11" type="button" onclick="cleardata();"> 
                                                                                                                <span class = "glyphicon glyphicon-refresh"></span> 
                                                                                                                Clear</button>
                                                                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                            <button type="button" id="button12" name="button12" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">
                                                                                                                <span class = "glyphicon glyphicon-play-circle"></span> 
                                                                                                                Play Video</button>
                                                                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                            <button class="btn btn-default btn-sm" id="Exp_button_insert" name="Exp_button_insert" type="button" > 
                                                                                                                <span class = "glyphicon glyphicon-plus-sign"></span> 
                                                                                                                Insert Video</button>
                                                                                                            <div style="margin-left: -395px;margin-top: 45px;">
                                                                                                                <label>No Of Videos To Be Display:
                                                                                                                    <select id="video_pager" name="video_pager" >
                                                                                                                        <option value="5">5</option>
                                                                                                                        <option value="10">10</option>
                                                                                                                        <option value="15">15</option>
                                                                                                                        <option value="20">20</option>
                                                                                                                        <option value="25">25</option>
                                                                                                                    </select>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                </div>

                                                                                                <div id="hyv-watch-content" class="hyv-watch-main-col hyv-card hyv-card-has-padding" style="margin-left: 30px;">

                                                                                                    <ul id="hyv-watch-related" class="hyv-video-list">

                                                                                                    </ul>

                                                                                                </div>


                                                                                                <td>



                                                                                                </td>
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
                                                                                                                    <button type="button" class="btn btn-default" data-dismiss="modal" >Close
                                                                                                                    </button>
                                                                                                                </div>
                                                                                                            </div>

                                                                                                        </div>
                                                                                                    </div>

                                                                                                </div>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>

                                                                                            </td>
                                                                                            </td>


                                                                                        </tr>



                                                                                    </table>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                </div>
                                                                <div id="dialog_edit_ExploreYoutubeDeatils" style="display: none;z-index:100; position: relative;font-size: 9pt;" align = "center" >
                                                                    <form action="updateExploreYTVideo" method="post" > 
                                                                        <table  class="table table-striped">
                                                                            <tr class="info">
                                                                                <td> <label>Video ID :</label> </td>
                                                                                <td><input id="ExpYTupdate_video_Id" name="ExpYTupdate_video_Id" class="form-control input-sm" type="text" disabled="true" />
                                                                                    <input type="hidden" id="hidden_ExpYTupdate_video_Id" class="form-control input-sm"  name="hidden_ExpYTupdate_video_Id" /> 

                                                                                </td>
                                                                            </tr>
                                                                            <tr class="info">
                                                                                <td> <label>Video Title :</label> </td>
                                                                                <td><input id="ExpYTupdate_Video_Name" name="ExpYTupdate_Video_Name" class="form-control input-sm" type="text" required="true"/> </td>
                                                                            </tr>
                                                                            <tr class="info">
                                                                                <td width="40%"> <label>VIDEO View Count :</label> </td>
                                                                                <td width="60%"><input id="ExpYTupdate_Video_view_count" name="ExpYTupdate_Video_view_count" type="text" class="form-control input-sm" required="true" size="35" /> </td>

                                                                            </tr>
                                                                            <tr class="info">
                                                                                <td width="40%"> <label>VIDEO Published Date :</label> </td>
                                                                                <td width="60%"><input id="ExpYTupdate_Video_published_date" name="ExpYTupdate_Video_published_date" type="text" class="form-control input-sm" required="true" size="35" /> </td>

                                                                            </tr>
                                                                            <tr class="info">
                                                                                <td> <label>Application :</label> </td>
                                                                                <td>
                                                                                    <select class="form-control input-sm"  id="Edit_ExpYTupdate_Application_Name" name="Edit_ExpYTupdate_Application_Name"  required="true" onchange="getEditAppCategorization();"> 
                                                                                        <option value="Select"> Select</option>
                                                                                        <%
                                                                                            int countered = 0;
                                                                                            for (int j = 0; j < (appnameid.size()) / 2; j++) {
                                                                                        %>
                                                                                        <option value="<%=appnameid.get(countered)%>">
                                                                                            <%
                                                                                                out.print(appnameid.get(++countered));
                                                                                                countered++;
                                                                                            %>
                                                                                        </option>
                                                                                        <%}%>
                                                                                    </select>
                                                                                </td>
                                                                            </tr> 

                                                                            <tr class="info">
                                                                                <td> <label>Categorization :</label> </td>
                                                                                <td>
                                                                                    <select class="form-control input-sm"  id="ExpYTubeupdate_Categorization_Name" name="ExpYTubeupdate_Categorization_Name" onchange="getEditAppCategories();"> 
                                                                                        <option>Select</option>


                                                                                    </select>
                                                                                </td>
                                                                            </tr> 

                                                                            <tr class="info">
                                                                                <td> <label>Category :</label> </td>
                                                                                <td>
                                                                                    <select class="form-control input-sm"  id="ExpYTupdate_Video_Category" name="ExpYTupdate_Video_Category"  onchange="getListAllChildCategories();"> 
                                                                                        <option>Select</option>


                                                                                    </select>
                                                                                </td>
                                                                            </tr> 
                                                                            <tr class="info">
                                                                                <td><button  type="submit" name="submitButton" value="Submit" class="btn btn-info align-center">Update</button></td>
                                                                                <td></td>
                                                                            </tr>

                                                                        </table>

                                                                    </form>


                                                                </div>                  
                                                                <div id="dialog_delete_ExploreYoutubeDeatils" style="display: none; font-size: 9pt;" align = "center">
                                                                    <br>

                                                                        <form action="deleteexplorevideo"  method="post"> 
                                                                            <table class="table table-striped">

                                                                                <tr>
                                                                                    <td> <label>Are you sure to Delete ?</label></td>
                                                                                    <td>
                                                                                        <input type="hidden" name="hidden_explorevideoID" id="hidden_explorevideoID"/>
                                                                                        <input type="hidden" name="hidden_explorevideoAppID" id="hidden_explorevideoAppID"/>
                                                                                        <input type="hidden" name="hidden_explorevideoCategoryID" id="hidden_explorevideoCategoryID"/>
                                                                                    </td>

                                                                                </tr> 
                                                                                <tr>
                                                                                    <td align="center"><button  type="submit" name="submitButton" value="Submit" class="blue">Ok</button></td>
                                                                                </tr>
                                                                            </table>
                                                                        </form>
                                                                </div>
                                                                <div class="panel panel-primary" style="overflow-y: auto; font-size: 9pt; margin-top: -10px; height: 480px;">
                                                                    <div class="panel-heading" >
                                                                        <center>  <h4 class="panel-title">Explore YouTube Video Details</h4></center>
                                                                    </div>
                                                                    <div class="panel-body">
                                                                        <div style="height: 400px;">
                                                                            <div class="pager-panel" style="margin-left:  640px;">
                                                                                <label>No Of Items To Be Display:
                                                                                    <select id="pager" name="pager" onchange="changeval();">
                                                                                        <%--     <%
                                                                                            int arrsize = d.size();
                                                                                        
                                                                                            double cal = arrsize / 5;
                                                                                        
                                                                                            double res = cal / 10;
                                                                                         
                                                                                          
                                                                                                        for (int j = 1; j <= Math.ceil(res); j++) {
                                                                                                    %><option value="<%=j%>"><%out.print(j);%></option><%
                                                                                                        }

                                                    %>--%>
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
                                                                </body>

                                                                </html>