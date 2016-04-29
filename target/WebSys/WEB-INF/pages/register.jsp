<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>

<head>
	<link rel="icon" href="/resources/images/favicon_main.ico">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
	<!-- Custom Styling -->
	<link rel="stylesheet" href="/resources/css/main.css">


</head>

<body>
<div id="main" class="container">
	<div class="container" align="left">
		<h1>Register</h1>
	</div>
    <div id="fcontainer" class="container" align="left">
      <form id="regForm" class="form-signin" method="POST" action="" command="userDetails" modelAttribute="userModel">
		<p class="lead"  align="left">Please enter all fields marked *.</p>
		  <div class="form-group input-group-sm">
			  <p>First Name: </p>
     		<input type="text" style="width: 300px" id="inputfName" class="form-control"  name="fname" required/>
  			</div>
		  <div class="form-group input-group-sm">
			  <p>Surname: </p>
			  <input type="text" style="width: 400px" id="inputsName" class="form-control"  name="sname" required/>
		  </div>
		  <div class="form-group input-group-sm">
			  <p>Date Of Birth: </p>
			  <input type="date" style="width: 200px" id="inputDOB" class="form-control" name="dob" required/>
		  </div>
  		<div class="form-group input-group-sm">
			<p>Username: </p>
 			<input type="text" style="width: 200px" id="inputUsername" class="form-control" placeholder="5 to 20 Characters." name="username" required/>
		</div>
		<div class="form-group input-group-sm">
			<p>Password: </p>
  			<input type="password" style="width: 300px" id="inputPassword" class="form-control" placeholder="8 to 16 Characters." name="password" required/>
  		</div>
		<div class="form-group">
        <button class="btn btn-warning btn-block" style="width: 100px" type="submit">Register</button>
        </div>
      </form>
	</div>
      
    </div> <!-- /container -->
	<div class="container">
	<footer>
		<p class="lead">&copy; 2016 James Millner. </p>
	</footer>
</div>
</body>
</html>