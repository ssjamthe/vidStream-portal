<!--
Author: DesignMaz
Author URL: https://www.designmaz.net
License URL: https://www.designmaz.net/licence/
-->
<!DOCTYPE HTML>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Appify Login Page</title>
        <!-- Custom Theme files -->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
        <!-- Custom Theme files -->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
        <meta name="keywords" content="Static Login Form Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
        <!--script-->
        <script src="js/jquery.min.js"></script>
        <script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#horizontalTab').easyResponsiveTabs({
                    type: 'default', //Types: default, vertical, accordion           
                    width: 'auto', //auto or any width like 600px
                    fit: true   // 100% fit in a container
                });
            });

        </script>
        <style type="text/css">
            .errors {
                background-color:#FFCCCC;
                padding-left: 20px;
                border:1px solid #CC0000;
                width:215px;
                font-size: -0.2em;
                margin-bottom:-20px;

            }
            .errors li{
                list-style: none;
            }
            .login_submit{

                margin-top: 34%;
                font-size: 13px;
                color: #44c7f4;
                background: #fff;
                border: 2px solid #44c7f4;
                outline: none;
                cursor: pointer;
                border-radius: 20px;
                -webkit-border-radius: 20px;
                -moz-border-radius: 20px;
                -o-border-radius: 20px;
                -ms-border-radius: 20px;
                padding: 6px 13px;
                font-family: 'SourceSansPro-Regular';
                background:#44c7f4;
                color:#fff;
                transition: 0.5s all ease;
                -webkit-transition: 0.5s all ease;
                -moz-transition: 0.5s all ease;
                -o-transition: 0.5s all ease;
                -ms-transition: 0.5s all ease;

            }
        </style>
        <!--script-->
        <!---Google Analytics Designmaz.net-->
        <script>(function(i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function() {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
            ga('create', 'UA-35751449-15', 'auto');
            ga('send', 'pageview');</script>
        <script>
            function clear_Div() {

                document.getElementById(error1).innerHTML = "";
            }
        </script>
    </head>
    <body style="background-color: #007fff">
        <div class="head"  >
            <div class="logo"style="background-color: #00bfff;height:74px;" >
                <div class="logo-top" >
                    <h1>Appify Login</h1>
                </div>
                <div class="logo-bottom" style="margin-left: 50px;">
                    <s:if test="hasActionErrors()"  >
                        <div class="errors" id="error1"> 
                            <s:actionerror/>
                        </div>
                    </s:if>
                </div>
            </div>		
            <div class="login">
                <div class="sap_tabs">
                    <div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
                        <ul class="resp-tabs-list">

                            <li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>Login Here</span></li>

                            <div class="clearfix"></div>
                        </ul>				  	 
                        <div class="resp-tabs-container">
                            <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
                                <div class="login-top">
                                    <s:form action="login" method="POST">
                                        <s:textfield name="username" class="username" placeholder="Username" id="username"  required=""/>
                                        <s:password name="password" id="password" class="password" placeholder="Password"  required=""/>		

                                        <div class="login-bottom login-bottom1" style="margin-top: 10pt;">
                                            <div class="submit" >
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <s:submit cssClass="login_submit" key="Submit" name="submit" value="Submit" theme="simple" />
                                                        </td>

                                                        <td>
                                                            <s:reset cssClass="login_submit" key="Reset" name="Reset" value="Reset" theme="simple"  />   
                                                        </td>
                                                    </tr>
                                                </table>




                                            </div>
                                        </div>	

                                    </s:form>

                                </div>
                            </div>

                        </div>	
                    </div>
                </div>	
            </div>	
            <div class="clear"></div>
        </div>	

        <div style="text-align:center; margin-top:10px;">
            <ins class="adsbygoogle"
                 style="display:block"
                 data-ad-client="ca-pub-8011246932591811"
                 data-ad-slot="9844648019"
            data-ad-format="auto"></ins> <script>(adsbygoogle = window.adsbygoogle || []).push({});</script>
            <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        </div>
        <div class="footer">
            <p>Powered by <a href="http://amsworld.net/" target="_blank">AMS PVT LTD NAGPUR</a> </p>
        </div>
    </body>
</html>