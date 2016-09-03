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
        <link rel="stylesheet" href="css/styles.css">
        <script type="text/javascript" src="lib/js/underscore-min.js"></script>
        <script type="text/javascript" src="lib/js/backbone-min.js"></script>
        <script type="text/javascript" src="lib/js/pretty-json-min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
        <title>List All Categories </title>
        <script src="http://code.jquery.com/jquery-1.8.3.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/demos.css" />
        <link rel="stylesheet" href="maincss.css" type="text/css">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,600,400' rel='stylesheet' type='text/css'>

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

            $(document).ready(function() {
                var ytkey = 'AIzaSyAEBs_3Va1D1edITLw8hND9IJAcnh50Lx4';
                //youtubeApiCall();
                $("#pageTokenNext").on("click", function(event) {
                    $("#pageToken").val($("#pageTokenNext").val());

                    youtubeApiCall();

                });
                $("#pageTokenPrev").on("click", function(event) {
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
                            //videoList = videoList + '<li><input type="radio" id="radio_video" name="radio_video" value="' + e.id + '" /></li><li class="hyv-video-list-item"><div class="hyv-content-wrapper"><a href onclick="test(); class="hyv-content-link" title="' + e.snippet.title + '"><span class="title">' + e.snippet.title + '</span><span class="stat attribution">by <span>' + e.snippet.channelTitle + '</span></span></a></div><div class="hyv-thumb-wrapper"><a href="" class="hyv-thumb-link"><span class="hyv-simple-thumb-wrap"><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></span></a><span class="video-time">' + YTDurationToSeconds(e.contentDetails.duration) + '</span></div></li>';

                            //videoList = videoList + '<table><tr><td><input type="checkbox"  name="checkboxgroup"  class="case" name="case"  value="' + e.id + '" /></td> <td><li class="hyv-video-list-item"><div class="hyv-content-wrapper"><a href  class="hyv-content-link" title="' + e.snippet.title + '"><span class="title">' + e.snippet.title + '</span><span Published Date::><span>' + e.snippet.publishedAt + '</span><span class="stat attribution">by <span>' + e.snippet.channelTitle + '</span></span></a></div><div class="hyv-thumb-wrapper"><a href="" class="hyv-thumb-link"><span class="hyv-simple-thumb-wrap"><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></span></a><span class="video-time">' + YTDurationToSeconds(e.contentDetails.duration) + '</span></div></li> </td></tr></table>';

                            videoList = videoList + '<table><tr><td><input type="radio" id="video_id" name="video_id" value="' + e.id + '" /></td> <td><li class="hyv-video-list-item"><div class="hyv-content-wrapper"><a href  class="hyv-content-link" title="' + e.snippet.title + '"><span class="title">' + e.snippet.title + '</span><span class="stat attribution">Published Date:<span>' + e.snippet.publishedAt.replace('T', ' ').replace('Z', '') + '</span><span class="stat attribution">by <span>' + e.snippet.channelTitle + '</span></span></a></div><div class="hyv-thumb-wrapper"><a href="" class="hyv-thumb-link"><span class="hyv-simple-thumb-wrap"><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></span></a><span class="video-time">' + YTDurationToSeconds(e.contentDetails.duration) + '</span></div></li> </td></tr></table>';
                            //videoList = videoList + '<table><tr><td></td><td><span><div class="youtube" id="btn" value="' + e.id + '"><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></div></span></td><td><b><div id="abc">' + e.snippet.title + '</div></b> </td></tr></table>';
                            var str = e.snippet.publishedAt.replace('T', ' ').replace('Z', '');

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
                    $.ajax({
                        cache: false,
                        data: $.extend({
                            key: ytkey,
                            q: $('#hyv-search').val(),
                            part: 'snippet'
                        }, {maxResults: 4, pageToken: $("#pageToken").val()}),
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
            function getvideoinfo(video_id)
            {
                var video_id1 = video_id;
                var yt_new_key = "AIzaSyAEBs_3Va1D1edITLw8hND9IJAcnh50Lx4";
                //var url_new="https://www.googleapis.com/youtube/v3/videos?id=DR31sQoruts&key=AIzaSyAEBs_3Va1D1edITLw8hND9IJAcnh50Lx4&fields=items(id,snippet(channelId,title,categoryId),statistics)&part=snippet,statistics"
                $.ajax({
                    cache: false,
                    dataType: 'json',
                    type: 'GET',
                    timeout: 5000,
                    url: 'https://www.googleapis.com/youtube/v3/videos?id=' + video_id + '&key=' + yt_new_key + '&fields=items(id,snippet(publishedAt,channelId,title,categoryId),statistics)&part=snippet,statistics',
                    success: function(data) {
                        var items = data.items;
                        var i = 0;

                        $.each(items, function(index, e) {
                            //videoList = videoList + '<li><input type="radio" id="radio_video" name="radio_video" value="' + e.id + '" /></li><li class="hyv-video-list-item"><div class="hyv-content-wrapper"><a href onclick="test(); class="hyv-content-link" title="' + e.snippet.title + '"><span class="title">' + e.snippet.title + '</span><span class="stat attribution">by <span>' + e.snippet.channelTitle + '</span></span></a></div><div class="hyv-thumb-wrapper"><a href="" class="hyv-thumb-link"><span class="hyv-simple-thumb-wrap"><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></span></a><span class="video-time">' + YTDurationToSeconds(e.contentDetails.duration) + '</span></div></li>';
                            //var str = e.snippet.publishedAt.replace('T', ' ').replace('Z', '');
                            var count = e.statistics.viewCount;
                            var str = e.snippet.publishedAt.replace('T', ' ').replace('Z', '');


                            showDialog(video_id1, e.snippet.title)

                        });

                    }
                });
            }
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#button1").click(function() {
                    var favorite = [];
                    $.each($("input[name='video_id']:checked"), function() {
                        favorite.push($(this).val());
                        // showDialog($(this).val(),'test');
                        getvideoinfo($(this).val());
                    });
                    //alert("My favourite sports are: " + favorite.join(", "));
                });
            });

            function showDialog(vid, vname) {

                $("#diolog_add_video").dialog({
                    modal: true,
                    autoOpen: false,
                    title: "Insert Video",
                    width: 460,
                    height: 220
                }).dialog("open");
                $("#video_id_new").val(vid);
                $("#hidden_video_id_new").val(vid);
                $("#video_title").val(vname);
            }
            function cleardata() {
                $("#hyv-watch-related").html("");
            }
        </script>
        <script>

            var data_list = [];
            var b = [];
            var store;
            <%
                ArrayList d = new ArrayList();
                String cat_id = (String) session.getAttribute("cateory_id");
                d = (ArrayList) session.getAttribute("cate_video_list");
                for (int i = 0; i < d.size(); i++) {%>
            data_list.push("<%= d.get(i)%>");
            <%}%>

            $(function() {

                add_data();
                add();
            });




            function add_data() {
                var j = 0;

                for (var i = 0; i < data_list.length / 3; i++) {

                    var sy = j;
                    var sbs = ++j;
                    db.clients1 =
                            {"Video_ID": data_list[j],
                                "Thumbnail": "<img src=\"http:\/\/img.youtube.com\/vi\/" + data_list[j] + "\/default.jpg\">",
                                "Video_Title": data_list[++j],
                                "Date_Added": data_list[++j],
                            };

                    j++;

                    b.push(db.clients1);

                    add(b);
                }

            }

           function add(a) {

                                    $("#jsGrid").jsGrid({
                                        height: "120%",
                                        width: "100%",
                                        sorting: true,
                                        paging: true,
                                        autoload: true,
                                        pageSize: 4,
                                        pageButtonCount: 5,
                                        data: a,
                                        deleteConfirm: function(item) {
                                            return "The client \"" + item.Name + "\" will be removed. Are you sure?";
                                        },
                                        fields: [
                                            {name: "Video_ID", type: "text", width: 20},
                                            {name: "Thumbnail", type: "text", width: 20},
                                            {name: "Video_Title", type: "text", width: 50},
                                            {name: "Date_Added", type: "text", width: 20},
                                            {itemTemplate: function(_, item) {
                                                    return $("<button >").attr("type", "button").text("Play Video").addClass("blue").attr("data-toggle", "modal").attr("data-target", "#myModal")
                                                            .on("click", function() {

                                                        set_url(item.Video_ID);
                                                    })
                                                },
                                                align: "center",
                                                width: 50
                                            },
                                        ]

                                    });
                                }
                                function set_url(vid) {
                                    var url_vid = "http://www.youtube.com/embed/" + vid + "?rel=0&wmode=transparent"

                                    document.getElementById('youtube-player').src = url_vid;
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

        <%--  <header class="header-user-dropdown">
           <center><b><i><font size="6" color="white" face="times new roman">Appify</font></i></b></center>
       </header>
       <div id='cssmenu' style="height: 5%;">
           <ul>
               <li><a href='login'>DashBoard</a></li>
           </ul>
       </div>
        --%>
        <%@include file="HeaderAndMenu.jsp"%>
        <br>
        <br>
        
           <div style="height:66%; width: 100%; margin-top: -20pt;" class="loginform cf">

                                                <div class="row-fluid">
                                                    <main id="content" role="main" class="span12">
                                                        <!-- Begin Content -->
                                                        <div id="hyv-page-container" style="clear:both;">
                                                            <div class="hyv-content-alignment">
                                                                <div id="hyv-page-content" class="" style="overflow:hidden;">
                                                                    <div class="container-4">

                                                                        <input type="search" name="hyv-search" id="hyv-search" placeholder="Search video here..." class="ui-autocomplete-input" autocomplete="off">
                                                                            <button class="icon" id="hyv-searchBtn"></button>
                                                                            <input type="hidden" id="pageToken" value="">
                                                                                <div class="btn-group" role="group" aria-label="..." style="display:none; ">
                                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                    <button type="button" id="pageTokenPrev" value="" class="btn btn-default btn-sm">
                                                                                        <span class = "glyphicon glyphicon-chevron-left"></span> 
                                                                                        Prev</button>

                                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                    <button type="button" id="pageTokenNext" value="" class="btn btn-default btn-sm">
                                                                                        <span class = "glyphicon glyphicon-chevron-right"></span> 
                                                                                        Next</button>

                                                                                </div>
                                                                                <button class="btn btn-default btn-sm" id="button11" name="button11" type="button" onclick="cleardata();"> 
                                                                                    <span class = "glyphicon glyphicon-refresh"></span> 
                                                                                    Clear</button>


                                                                                <button class="btn btn-default btn-sm" id="button1" name="button1" type="button" > 
                                                                                    <span class = "glyphicon glyphicon-plus-sign"></span> 
                                                                                    Insert Video</button>
                                                                                </div>

                                                                                <table border="1">
                                                                                    <tr>
                                                                                        <td>
                                                                                            <div style="margin-top: -30px;margin-left: 530px;">


                                                                                            </div>

                                                                                            <div id="hyv-watch-content" class="hyv-watch-main-col hyv-card hyv-card-has-padding" style="margin-left: 30px;">

                                                                                                <ul id="hyv-watch-related" class="hyv-video-list">

                                                                                                </ul>

                                                                                            </div>
                                                                                        </td>
                                                                                        <%--
                                                                                        <td>
                                                                                            <button class="btn btn-default" id="button1" name="button1" type="button" style="margin-left: 200px; margin-top: -30px;"> Insert Video</button>
                                                                                        </td>
                                                                                        --%>
                                                                                    </tr>
                                                                                </table>
                                                                                </div>
                                                                                </div>
                                                                                </div>
                                                                                </div>
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
                                                                                <div id="diolog_add_video" style="display: none; font-size: 9pt; " align = "center">
                                                                                    <br>

                                                                                        <form  name ="add_video_link" action="add_video_link" method="post" enctype="multipart/form-data"> 
                                                                                            <table class="table table-striped">
                                                                                                <tr class="info">
                                                                                                    <input id="new_category_id" name="new_category_id" type="hidden" value="<%=cat_id%>"style="width: 100%" required="true"/>
                                                                                                    <td width="40%"> <label>VIDEO ID :</label> </td>
                                                                                                    <td width="60%">
                                                                                                        <input id="video_id_new" name="video_id_new" type="text" disabled="true" size="35" />
                                                                                                        <input type="hidden" id="hidden_video_id_new" class="form-control input-sm" name="hidden_video_id_new" size="35" /> 
                                                                                                    </td>
                                                                                                </tr> 

                                                                                                <tr class="info">
                                                                                                    <td width="40%"> <label>VIDEO TITLE :</label> </td>
                                                                                                    <td width="60%"><input id="video_title" class="form-control input-sm" name="video_title" type="text"  required="true" size="35" /> </td>

                                                                                                </tr>
                                                                                                <tr class="info">
                                                                                                    <td><button  class="btn btn-info" style="margin-left: 50px;" type="submit" name="submitButton" value="Submit" class="blue">ADD</button></td>
                                                                                                    <td></td>
                                                                                                </tr>
                                                                                            </table>
                                                                                            <br/>
                                                                                            <table  cellspacing="5" cellpadding="5" border="0" width="60%">

                                                                                            </table>
                                                                                        </form>
                                                                                </div>


                                                                                <div style="height:96%; width: 100%;">
                                                                                    <div id="jsGrid"  style="font-style: black; text-align: center;" >
                                                                                    </div>
                                                                                </div>
                                                                                </div>
                                                                                </body>
                                                                                </html>
