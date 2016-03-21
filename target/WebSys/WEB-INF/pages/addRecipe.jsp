
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

    <title>Add Recipe!</title>

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

    <link rel="stylesheet" href="/resources/css/addRecipes.css">
</head>
  <body>
  <div class="row" align="center">
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
              <li><a href="/homepage?name=${name}">Home <span class="sr-only">(current)</span></a></li>
              <li><a href="#">Profile</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Recipes! <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li class="active"><a href="#">Add Recipe!</a></li>
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
              <a class="navbar-brand" href="#">User: ${name}</a>
              <li><a href="/signin">Logout.</a></li>
            </ul>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
    </div>
    <div id="welcome" class="row">
      <p id="welcometxt">Add New Recipe!</p>
    </div>
  </div>
  <div class="container">
    <p id="intro">Please use the following form to add a new recipe!. All fields marked * are required. </p>
  </div>
  <form method="POST" action="" command="recipe">
    <div id="content" class="container-fluid">
      <div class="row-fluid">
        <div class="col-md-6">
          <div class="input-group">
            <label id="namelbl">Recipe Name: </label>
            <input type="text" id="recipeName" class="form-control" name="rname" required/>
          </div>
          <div class="input-group">
            <label id="desclbl">Description: </label>
            <textarea type="text" id="description" class="form-control" name="rdesc" required style="width: 400px; height: 75px"></textarea>
          </div>
          <div class="input-group">
            <label id="itemslbl">Ingredients: </label>
            <textarea type="text" id="ingredients" class="form-control" name="ringredients" placeholder="Please enter one ingredient per line." required style="width: 400px; height: 150px"></textarea>
          </div>
        </div>
        <div class="col-md-6">
          <label>Time to Prep: </label>
          <p>Hours: &nbsp <input type="number" name="rhours" min="0" max="12" style="color: black"/>  <p> Minutes: &nbsp <input type="number" name="rmins" min="0" max="60" style="color: black"/> </p> </p>
          <div class="input-group">
            <label id="extralbl">Additional Information: </label>
            <textarea type="text" id="additionalInfo" class="form-control" name="additional" placeholder="Please enter any additional information." style="width: 400px; height: 150px"></textarea>
          </div>
          <div class="input-group" style="padding-top: 5px">
            <button class="btn btn-warning " type="submit">Add Recipe!</button>
          </div>
        </div>

      </div>
    </div>
    <div class="container" align="center">
      <p>${success}</p>
    </div>
  </form>

  </body>
</html>
