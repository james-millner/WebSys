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
    <link rel="icon" href="../../favicon.ico">

    <title>Homepage</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
    <!-- Custom Styling -->
    <link rel="stylesheet" href="/resources/css/main.css">
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
              <li class="active"><a href="/homepage?name=${name}">Home <span class="sr-only">(current)</span></a></li>
              <li><a href="/profile?name=${name}">Profile</a></li>
              <li class="dropdown-a">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Recipes! <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="/addRecipe?name=${name}">Add Recipe!</a></li>
                </ul>
              </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <a class="navbar-brand">User: ${name}</a>
              <li><a href="/signin">Logout.</a></li>
            </ul>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
    </div>
    <div id="welcome" class="well">
      <p id="welcometxt">The Recipe Book!</p>
    </div>
  </div>
  <div id="reports" class="container-fluid" align="center">
    <ol class="breadcrumb">
      <li class="active"><a href="#">Recent Recipes</a></li>
      <li><a href="#">Top Recipes.</a></li>
      <li><a href="#">Search.</a></li>
    </ol>
  </div>
  <div id="content" class="container-fluid">
       <div id="recipes" class="col-md-12">
        <c:forEach var="recipe" items="${recipes}">
          <c:url value="/viewRecipe?_id=${recipe.id}&name=${name}" var="viewRecipe"></c:url>
          <div id="recipe" class="row">
            <div id="dname" class="col-xs-12">
              <p id="rname">${recipe.rname}</p>
            </div>
            <div id="dcreator" class="col-xs-1">
              <p id="rcreator"><b>Created By:</b><br>${recipe.creator}</p>
            </div>
            <div id="ddesc" class="col-xs-5">
              <p><b>Description: </b><br>${recipe.rdesc}</p>
            </div>
            <div id="dtime" class="col-xs-6">
              <div id="dtarea" class="col-xs-2" align="center">
                <span class="glyphicon glyphicon-time" style="color: azure" aria-hidden="true"></span> <p>${recipe.rhours} H<br>${recipe.rmins} M</p>
              </div>
              <div id="dviews" class="col-xs-2">
                <span class="glyphicon glyphicon-user" style="color: azure" aria-hidden="true"></span> <p>${recipe.views}</p>
              </div>
              <div id="dtype" class="col-xs-2" align="center">
                <span class="glyphicon glyphicon-menu-down" style="color: azure" aria-hidden="true"> <p>${recipe.ftype}</p></span>
              </div>
              <div class="col-xs-4">
                <a style="width: 215px" href='<c:out value="${viewRecipe}"></c:out>'> View</a>
              </div>
            </div>
          </div>
        </c:forEach>
    </div>
  </div>

  </body>
</html>
