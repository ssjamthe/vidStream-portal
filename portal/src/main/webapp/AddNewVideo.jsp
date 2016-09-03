<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-gb" lang="en-gb" dir="ltr">

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="css/login-form.css" media="screen">
            <title>Add New Video  </title>
            <link rel="stylesheet" href="maincss.css" type="text/css">

                <link rel="stylesheet" type="text/css" href="lib/pretty-json.css" />
                <!-- Latest compiled and minified CSS -->
                <link rel="stylesheet" href="css/login-form.css" media="screen">
                    <script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/jquery-ui.js" type="text/javascript"></script>
                    <link href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/blitzer/jquery-ui.css"
                          rel="stylesheet" type="text/css" />
                    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
                    <link rel="stylesheet" href=?http://w3tweaks.com/demo/youtube_search_videos/maincss.css? type="text/css" />
                    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" type="text/css" />
                    <script src="//code.jquery.com/jquery-2.1.4.js" type="text/javascript"></script>
                    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js" type="text/javascript"></script>
                    <!-- Underscore, backbone and pretty are used for displaying the json responce in user redable format -->
                    <script type="text/javascript" src="lib/js/underscore-min.js"></script>
                    <script type="text/javascript" src="lib/js/backbone-min.js"></script>
                    <script type="text/javascript" src="lib/js/pretty-json-min.js"></script>
                    <link rel="stylesheet" href="css/styles.css">
                        <link rel="stylesheet" type="text/css" href="css/demos.css" />
                        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,600,400' rel='stylesheet' type='text/css'>
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

                                                videoList = videoList + '<table><tr><td><div class="selector"><input type="radio" id="video_id" name="video_id" value="' + e.id + '" /></div><td></td><td><span><img alt="' + e.snippet.title + '" src="' + e.snippet.thumbnails.default.url + '" width="120" height="90"></span></td><td><b><div id="abc">' + e.snippet.title + '</div></b> </td></tr></table>';

                                                var str = e.snippet.publishedAt.replace('T', ' ').replace('Z', '');

                                            });
                                            $("#hyv-watch-related").html(videoList).l;
                                <%--        document.getElementsByClassName("case").onclick = function() {
            myFunction();
        };
                                --%>
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
                                                }, {maxResults: 5, pageToken: $("#pageToken").val()}),
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
                                                    alert('viewCount::::::::' + e.statistics.viewCount);
                                                    var str = e.snippet.publishedAt.replace('T', ' ').replace('Z', '');
                                                    alert('date::::::::' + str);

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
                                        height: 180
                                    }).dialog("open");
                                    $("#video_id_new").val(vid);
                                    $("#hidden_video_id_new").val(vid);
                                    $("#video_title").val(vname);



                                }
                                function cleardata(){
                                    $("#hyv-watch-related").html("");
                                }
                            </script>


                            <style type="text/css">
                                body{
                                    background-color: #efefef;
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
                            </head>

                            <body>
                                <header class="header-user-dropdown">
                                    <center><b><i><font size="6" color="white" face="times new roman">Appify</font></i></b></center>
                                </header>
                                <div id='cssmenu' style="height: 5%;">
                                    <ul>
                                        <li><a href='login'>DashBoard</a></li>
                                    </ul>
                                </div>
                                <br>
                                    <br>
                                        <div id="diolog_add_video" style="display: none; font-size: 9pt; " align = "center">
                                            <br>

                                                <form action="add_video_link" method="post"> 
                                                    <table>
                                                        <tr>
                                                            <td width="40%"> <label>VIDEO ID :</label> </td>
                                                            <td width="60%">
                                                                <input id="video_id_new" name="video_id_new" type="text" disabled="true" size="35" />
                                                                <input type="hidden" id="hidden_video_id_new"  name="hidden_video_id_new" size="35" /> 
                                                            </td>
                                                        </tr> 

                                                        <tr>
                                                            <td width="40%"> <label>VIDEO TITLE :</label> </td>
                                                            <td width="60%"><input id="video_title" name="video_title" type="text"  required="true" size="35" /> </td>

                                                        </tr>
                                                    </table>
                                                    <br/>
                                                    <table  cellspacing="5" cellpadding="5" border="0" width="60%">
                                                        <tr>
                                                            <td align="center"><button  type="submit" name="submitButton" value="Submit" class="blue">ADD</button></td>

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
                                                            <div class="container-4">

                                                                <input type="search" name="hyv-search" id="hyv-search" placeholder="Search video here..." class="ui-autocomplete-input" autocomplete="off">
                                                                    <button class="icon" id="hyv-searchBtn"></button>

                                                            </div>

                                                            <table border="1">
                                                                <tr>
                                                                    <td>
                                                                        <div style="margin-top: -30px;margin-left: 530px;">

                                                                            <input type="hidden" id="pageToken" value="">
                                                                                <div class="btn-group" role="group" aria-label="..." style="display:none; ">
                                                                                    <button type="button" id="pageTokenPrev" value="" class="btn btn-default">Prev</button>

                                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                    <button type="button" id="pageTokenNext" value="" class="btn btn-default">Next</button>

                                                                                    <button class="btn btn-default" id="button11" name="button11" type="button" onclick="cleardata();"> Clear</button>

                                                                                </div>
                                                                        </div>

                                                                        <div id="hyv-watch-content" class="hyv-watch-main-col hyv-card hyv-card-has-padding" style="margin-left: 30px;">

                                                                            <ul id="hyv-watch-related" class="hyv-video-list">

                                                                            </ul>

                                                                        </div>
                                                                    </td>



                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <button class="btn btn-default" id="button1" name="button1" type="button" style="margin-left: 200px;"> Insert Video</button>
                                                                    </td>
                                                                </tr>



                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                        </div>


                                        </body>

                                        </html>