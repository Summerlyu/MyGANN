<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Welcome to GANN system</title>
<link rel="shortcut icon"  href="images/favicon.ico">
<link rel="stylesheet" href="css/style.css" type="text/css">
<style type="text/css">
/*BACK TO TOP*/
.backToTop {
	display: none; 
	width: 38px; 
	bottom: 20px;
    height: 38px;
	background: url("images/top.png") no-repeat scroll 0 0 transparent;
	position: fixed; 
	_position: absolute; 
	right: 20px; bottom: 20px;
	 _bottom: "auto"; cursor: pointer; 
	opacity: .6; filter: Alpha(opacity=60);
}
</style>
</head>

<body>
	<div id="header" style="height: 60px;">
		<div class="clearfix" style="margin-top: -5px;">
			<div class="logo">
				<a href="index.html"><img src="images/logo.png" alt="LOGO"
					height="52" width="362">
				</a>
			</div>
			<ul class="navigation" style="margin-top: -5px; margin-right: -100px; margin-bottom: 0px;">
				<li><a href="index.jsp">Introduction</a></li>
				<li><a href="configuration.jsp">Configuration</a></li>
				<li><a href="upload.jsp">Import Data</a></li>
				<li><a href="data_distribution.jsp">Data Distribution</a></li>
				<li><a href="trainLine.jsp">Training</a></li>
				<li><a href="testLine.jsp">Testing</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
