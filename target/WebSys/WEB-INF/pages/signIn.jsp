<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
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
    <link rel="stylesheet" href="/resources/css/signin.css">



</head>
<body>

    <div class="container" align="center">

      <form class="form-signin" method="POST" action="" style="width: 300px" command="userDetails">
        <h2>Sign In</h2>
        <p>Please enter all fields marked *.</p>
          <div class="form-group">
     	<input type="text" id="inputEmail" class="form-control" placeholder="Username *"type="text" name="username" required/>
  		</div>
          <div class="form-group">
  		<input type="password" id="inputPassword" class="form-control" placeholder="Password *" type="text" name="password" required/>
          </div>
        <div class="button-box col-lg-12">
            <p>${error}</p>
        <button class="btn btn-warning " type="submit">Sign in</button>
        <a href="/register" class="btn btn-warning " type="submit">Register</a>
        </div>
      </form>



    </div> <!-- /container -->
    <div style="width: 200px" align="center" class="container">
        <footer>
            <p>&copy; 2016 James Millner. </p>
        </footer>
    </div>
</body>
</html>