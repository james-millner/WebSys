<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

    <title>View!</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
    <!-- Custom Styling -->
    <link rel="stylesheet" href="/resources/css/view.css">
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
              <li><a href="#">Profile</a></li>
              <li class="dropdown-a">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Recipes! <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="/addRecipe?name=${name}">Add Recipe!</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li role="separator" class="divider"></li>
                  <li><a href="#">Separated link</a></li>
                  <li role="separator" class="divider"></li>
                  <li><a href="#">One more separated link</a></li>
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
  </div>
  <div id="content" class="container-fluid">
    <div class="col-lg-12" align="center">
      <p id="title">${recipeModel.rname}</p>
    </div>
    <div class="col-lg-12" align="center">
      <p style="font-size: 24px; text-shadow: 2px 8px 10px #000;"><b>Recipe Details:</b></p>
    </div>
    <div class="col-md-12" align="center">
      <div class="col-sm-4"></div>
      <div class="col-sm-4">
        <div class="col-sm-4">
        <span class="glyphicon glyphicon-time" style="color: azure" aria-hidden="true"></span> <p>${recipeModel.rhours} H - ${recipeModel.rmins} M</p>
        </div>
        <div class="col-sm-4">
        <span class="glyphicon glyphicon-user" style="color: azure" aria-hidden="true"></span> <p>${recipeModel.views}</p>
        </div>
        <div class="col-sm-4">
        <span class="glyphicon glyphicon-menu-down" style="color: azure" aria-hidden="true"></span> <p>${recipeModel.ftype}</p>
        </div>
      </div>
      <div class="col-sm-4"></div>
    </div>
    <div class="col-lg-12" align="left">
      <div id="ing" class="col-md-3">
        <p style="font-size: 24px; text-shadow: 2px 8px 10px #000;"><b>Ingredients:</b></p>
        <pre>${recipeModel.ringredients}</pre>
      </div>
      <div class="col-md-7">
        <p style="font-size: 24px; text-shadow: 2px 8px 10px #000;"><b>Method:</b></p>
        <pre>${recipeModel.rmethod}
        </pre>
      </div>
      <div class="col-md-2" align="right">
        <p style="font-size: 24px; text-shadow: 2px 8px 10px #000;"><b>Added On: </b></p>
        <p>
        ${recipeModel.timecreated}
        </p>
        <p style="font-size: 24px; text-shadow: 2px 8px 10px #000";><b>Created By:</b></p>
        <p>
          ${recipeModel.creator}
        </p>
      </div>
      <div class="col-md-9">
        COMMENTS
      </div>
    </div>
  </div>

  </body>
</html>
