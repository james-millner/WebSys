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
              <li class="active"><a href="/homepage?name=${ecLink}">Home <span class="sr-only">(current)</span></a></li>
              <li><a href="/profile?name=${ecLink}">Profile</a></li>
              <li class="dropdown-a">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Recipes! <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="/addRecipe?name=${ecLink}">Add Recipe!</a></li>
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
    <form method="post" class="navbar-form navbar-left" role="search">
      <a href="/byviews?name=${ecLink}" type="submit" class="btn-custom  btn btn-default">Top Recipes!</a>
      <a type="button" class="btn-custom  btn btn-default" data-toggle="modal" data-target="#typeModal">By Type!</a>
      <div class="form-group">
        <input style="width: 600px" type="text" class="form-control" placeholder="Search by recipe name...">
      </div>
      <a type="submit" class="btn-custom btn btn-default disabled">Submit</a>
    </form>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="typeModal" role="dialog">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div style="background: #3b3a3c;" class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">Choose Types!</h2>
        </div>
        <div class="modal-body">
            <p>Select a type: </p>
          <a href="/byType?name=${ecLink}&type=American" type="submit" class="btn-custom  btn btn-default">American</a>
          <a href="/byType?name=${ecLink}&type=British" type="submit" class="btn-custom  btn btn-default">British</a>
          <a href="/byType?name=${ecLink}&type=Caribbean" type="submit" class="btn-custom  btn btn-default">Caribbean</a>
          <a href="/byType?name=${ecLink}&type=Chinese" type="submit" class="btn-custom  btn btn-default">Chinese</a>
          <a href="/byType?name=${ecLink}&type=Eastern European" type="submit" class="btn-custom  btn btn-default">Eastern European</a>
          <a href="/byType?name=${ecLink}&type=French" type="submit" class="btn-custom  btn btn-default">French</a>
          <a href="/byType?name=${ecLink}&type=Indian" type="submit" class="btn-custom  btn btn-default">Indian</a>
          <a href="/byType?name=${ecLink}&type=Italian" type="submit" class="btn-custom  btn btn-default">Italian</a>
          <a href="/byType?name=${ecLink}&type=Mexican" type="submit" class="btn-custom  btn btn-default">Mexican</a>
          <a href="/byType?name=${ecLink}&type=Spanish" type="submit" class="btn-custom  btn btn-default">Spanish</a>
          <a href="/byType?name=${ecLink}&type=Thai" type="submit" class="btn-custom  btn btn-default">Thai</a>
        </div>
        <div class="modal-footer">
          <a href="#" type="submit" class="btn-custom  btn btn-default">Submit</a>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>

    </div>
  </div>
  <div id="content" class="container-fluid">
       <div id="recipes" class="col-md-12">
        <c:forEach var="recipe" items="${recipes}">
          <c:url value="/viewRecipe?_id=${recipe.id}&name=${ecLink}" var="viewRecipe"></c:url>
          <div id="recipe" class="row">
            <div id="dname" class="col-xs-12">
              <p id="rname">${recipe.rname}</p>
            </div>
            <div id="ddesc" class="col-xs-6">
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
              <div class="col-xs-6">
                <a class="btn icon-btn btn-default" style="max-width: 215px" href='<c:out value="${viewRecipe}"></c:out>'><span class="glyphicon btn-glyphicon glyphicon-blackboard img-circle text-muted"></span>View Recipe</a>
              </div>
            </div>
          </div>
        </c:forEach>
    </div>
  </div>
  <div class="container">
  <footer>
    <p class="lead pull-right"><a href="#">Back to top</a></p>
    <p class="lead">&copy; 2016 James Millner. </p>
  </footer>
  </div>
  </body>
</html>
