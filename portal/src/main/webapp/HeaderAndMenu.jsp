<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Example of Bootstrap 3 Static Navbar Extended</title>

<style type="text/css">
    .bs-example{
    	margin: 20px;
    }
    .navbar-custom {
	background-color:#4897D8;
    color:#ffffff;
  	border-radius:0;
}
  
.navbar-custom .navbar-nav > li > a {
  	color:#fff;
}

.navbar-right {
    float: right!important;
    margin-right: 10px;
}
.navbar-custom .navbar-nav > .active > a, .navbar-nav > .active > a:hover, .navbar-nav > .active > a:focus {
    color: #ffffff;
	background-color:transparent;
}
      
.navbar-custom .navbar-nav > li > a:hover, .nav > li > a:focus {
    text-decoration: none;
    background-color: #33aa33;
}
      
.navbar-custom .navbar-brand {
  	color:#eeeeee;
}

.navbar-custom .navbar-toggle {
  	background-color:#eeeeee;
}
.navbar-custom .icon-bar {
  	background-color:#33aa33;
}
</style>
</head> 
<body>
<div class="bs-example">
    <nav role="navigation" class="navbar navbar-default navbar-custom navbar-fixed-top ">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a  class="navbar-brand">Appify</a>
        </div>
        <!-- Collection of nav links, forms, and other content for toggling -->
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href='login'>Home</a></li>
                
            </ul>
            
            <ul class="nav navbar-nav navbar-right">
                <li><a href='Logout.jsp'>Logout</a></li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>                                		