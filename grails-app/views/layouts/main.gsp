<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="Pedro Vaz">

		<title>
			<g:layoutTitle default="bromak"/>
		</title>

		<!-- Bootstrap core CSS -->
		<!--<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">-->
		<asset:stylesheet src="application.css"/>

		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

		<!-- Custom styles for this template -->
		<!--<link href="navbar-fixed-top.css" rel="stylesheet">-->

		<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
		<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
		<!--<script src="../../assets/js/ie-emulation-modes-warning.js"></script>-->

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

		<g:layoutHead/>
	</head>
	<body>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<!--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>-->
					<a class="navbar-brand" href="/">bromak</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
						
						<sec:ifAnyGranted roles="ROLE_ADMIN">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									Admin
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li class="dropdown-header">User Accounts</li>
									<li><g:link controller="user" action="index">Users</g:link></li>
									<li><g:link controller="role" action="index">Roles</g:link></li>
									<li role="separator" class="divider"></li>
									<li class="dropdown-header">Creatures</li>
									<li><g:link controller="race" action="index">Races</g:link></li>
								</ul>
							</li>
						</sec:ifAnyGranted>
						
						<sec:ifAnyGranted roles="ROLE_PLAYER">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									Player
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><g:link controller="champion" action="index">Champions</g:link></li>
									<li><g:link controller="champion" action="index">Communities</g:link></li>
								</ul>
							</li>
						</sec:ifAnyGranted>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<sec:ifLoggedIn>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									<img src="http://icons.iconarchive.com/icons/janik-baumgartner/woocons/32/User-icon.png" style="height:16px" />
									<sec:loggedInUserInfo field='username'/>
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li class="dropdown-header">User Account</li>
									<li><g:link controller="logout">Logout</g:link></li>
								</ul>
							</li>
						</sec:ifLoggedIn>
						<sec:ifNotLoggedIn>
							<li><g:link controller="login" action="auth">Login</g:link></li>
							<li><g:link controller="user" action="create">Register</g:link></li>
						</sec:ifNotLoggedIn>
					</ul>
				</div>
			</div>
		</nav>

		<div class="container">
			<g:layoutBody/>
		</div>

		<div id="spinner" class="spinner" style="display:none;">
			<g:message code="spinner.alt" default="Loading&hellip;"/>
		</div>

		<asset:javascript src="application.js"/>
	</body>
</html>
