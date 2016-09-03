<%-- 
    Document   : index
    Created on : Mar 10, 2016, 6:02:10 PM
    Author     : Nileh Diore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../pplayer/assets/pplayer.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script>
    $("#youtube-video").pPlayer({
    youtubeVideoId: "YE7VzlLtp-4",
    autoplay: 0,
    origin: "http://yoursite.com"
});
      
      $("#html5-video").pPlayer({
    youtubeVideoId: "YE7VzlLtp-4"
});
    </script>
    </head>
    <body>
       <div id="youtube-video"><!-- --></div>
                    
    </body>
</html>
