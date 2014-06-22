<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Body</title>

	<!-- Bootstrap core CSS -->
	<link href="assets/CSS/bootstrap.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="assets/CSS/bootstrap-theme.min.css" rel="stylesheet">
	
	<script src="assets/Javascript/jquery-2.0.3.js"></script>
	<script src="assets/Javascript/bootstrap.min.js"></script>
	
</head>
<body>
	<center>
		<div id="myCarousel" class="carousel slide">
	      <!-- Indicators -->
	      <ol class="carousel-indicators">
	        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	        <li data-target="#myCarousel" data-slide-to="1"></li>
	        <li data-target="#myCarousel" data-slide-to="2"></li>
	      </ol>
	      <div class="carousel-inner">
	        <div class="item active">
	          <img src="assets/Images/train2.jpg" alt="First slide" style="width:100%">
	        </div>
	        <div class="item">
	          <img src="assets/Images/train1.jpg" alt="Second slide" style="width:100%" >
	        </div>
	        <div class="item">
	          <img src="assets/Images/train2.jpg" alt="Third slide" style="width:100%">
	        </div>
	      </div>
	      <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
	      <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
	    </div>	
	</center>
</body>
</html>