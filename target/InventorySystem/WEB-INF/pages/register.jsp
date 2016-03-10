<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>

<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

</head>

<body>
    <div class="container" align="center">

      <form class="form-signin" method="POST" action="" style="width: 500px" command="userDetails" modelAttribute="userModel">
        <div class="jumbotron">
  			<h1>Register</h1>
  			</div>
        <p class="lead"  align="left">Please enter all fields marked *.</p>
        <div class="form-group">
     		<input type="text" id="inputFName" class="form-control" placeholder="First Name *"  name="fName" required/>
  			</div>
  		<div class="form-group">
  			<input type="text" id="inputSName" class="form-control" placeholder="Surname *"  name="sName" required/>
  			</div>
  		<div class="form-group">
 			<input type="text" id="inputUsername" class="form-control" placeholder="Username *" name="username" required/>
		</div>
		<div class="form-group">
  			<input type="text" id="inputPassword" class="form-control" placeholder="Password *" name="password" required/>
  		</div>
		<div class="form-group">
			<input type="text" id="inputType" class="form-control" placeholder="Type *" name="type"/>
		</div>
		<div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        </div>
      </form>

      
    </div> <!-- /container -->
</body>
</html>