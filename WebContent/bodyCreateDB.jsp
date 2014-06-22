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
	<br><br><br>
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<form action="Create_Train_Station">
			<table class="table">
				<tr>
					<td>Enter the number of Station you want to Enter ? </td> 
					<td> <input type="text" id="station" name="station" class="form-control" placeholder="Enter the number of Station"> </td>
				</tr>
				<tr>
					<td></td> 
					<td></td>
				</tr>
				<tr>
					<td>Enter the number of Train you want to Enter ? </td> 
					<td> <input type="text" id="train" name="train" class="form-control" placeholder="Enter the number of Trains"> </td>
				</tr>
				<tr>
					<td></td> 
					<td></td>
				</tr>
				<tr><td><button type="submit" class="btn btn-warning" style="width:140px;">Submit</button></td></tr>
			</table>
		</form>
	</div>
	<div class="col-md-3"></div>
</body>
</html>