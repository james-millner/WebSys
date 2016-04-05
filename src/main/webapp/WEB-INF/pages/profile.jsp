<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/resources/images/favicon_main.ico">

    <title>Homepage</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
    <!-- Custom Styling -->
    <link rel="stylesheet" href="/resources/css/pf.css">
    <link rel="stylesheet" href="/resources/css/nav.css">

</head>
  <body>
  <div id="header" class="row" align="center">
      <div class="container" style="max-width: 1650px;" align="center">
      <nav class="navbar navbar-default" >
        <div class="container-fluid">
          <!-- Brand and toggle -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
          </div>

          <!-- Nav links, forms, and other content-->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li><a href="/homepage?name=${ecLink}">Home <span class="sr-only">(current)</span></a></li>
              <li class="active"><a href="/profile?name=${ecLink}">Profile</a></li>
              <li class="dropdown-a">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Recipes! <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="/addRecipe?name=${ecLink}">Add Recipe!</a></li>
                </ul>
              </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="/signin">Logout.</a></li>
            </ul>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
    </div>
    <div id="welcome" class="well">
      <p id="welcometxt">Profile Page</p>
    </div>
  </div>
  <div id="content" class="container-fluid">
    <div id="dTag" class="jumbotron">
      <h1><span class="glyphicon glyphicon-user" style="color: #333234" aria-hidden="true"></span>  &nbsp; User Details</h1>
    </div>
    <div id="details" class="container-fluid">
        <div class="col-md-3">
          <p><b>First: </b> &nbsp; ${user.fname}</p>
          <p><b>Surname: </b> &nbsp; ${user.sname}</p>
        </div>
        <div class="col-md-3">
          <p><b>Date Of Birth: </b> <br> ${user.dob}</p>
        </div>
        <div class="col-md-2">
          <p><b>Total Recipes: </b></p>
          <p>${tRecipes}</p>
        </div>
        <div class="col-md-2">
          <p><b>Total Views:</b></p>
          <p>${tViews}</p>
        </div>
      </div>
    <div id="rSeperator"class="container-fluid">
      <div id="rTag" class="jumbotron">
        <h1><span class="glyphicon glyphicon-pencil" style="color: #333234" aria-hidden="true"></span>  &nbsp; Created Recipes:</h1>
      </div>
      <div id="scrollBar" class="container-fluid">
        <div id="items">
        <c:forEach var="recipe" items="${rList}">
          <div class="item col-xs-6 col-md-3">
            <a href="/viewRecipe?_id=${recipe.id}&name=${ecLink}" class="thumbnail">
              <b>${recipe.rname}</b>
              <br>
              <small>${recipe.ftype}</small>
            </a>
          </div>
        </c:forEach>
        </div>
      </div>
    </div>
    <div id="lSeperator"class="container-fluid">
      <div id="lTag" class="jumbotron">
        <h1><span class="glyphicon glyphicon-heart" style="color: #333234" aria-hidden="true"></span>  &nbsp; Liked Recipes:</h1>
      </div>
      <div id="lscrollBar" class="container-fluid">
        <div id="litems">
          <c:forEach var="recipe" items="${likesList}">
            <div class="item col-xs-6 col-md-3">
              <a href="/viewRecipe?_id=${recipe.id}&name=${ecLink}" class="thumbnail">
                <b>${recipe.rname}</b>
                <br>
                <small>${recipe.ftype}</small>
              </a>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <footer>
      <p class="lead">&copy; 2016 James Millner. </p>
    </footer>
  </div>
  </body>
</html>
