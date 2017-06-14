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
					<a class="navbar-brand" href="/">
						<span class="glyphicon glyphicon-tint"></span>
						bromak
					</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<sec:ifAnyGranted roles="ROLE_ADMIN">
							<li><g:link controller="user" action="index">
								<span class="glyphicon glyphicon-user"></span>
								Users
							</g:link></li>
							<li><g:link controller="role" action="index">
								<span class="glyphicon glyphicon-knight"></span>
								Roles
							</g:link></li>
							<li><g:link controller="race" action="index">
								<span class="icon-paw"></span>
								Races
							</g:link></li>
							<li><g:link controller="epoch" action="index">
								<span class="glyphicon glyphicon-hourglass"></span>
								Epochs
							</g:link></li>
							<li><g:link controller="world" action="index">
								<span class="glyphicon glyphicon-globe"></span>
								Worlds
							</g:link></li>
							<li><g:link controller="questType" action="index">
								<span class="glyphicon glyphicon-exclamation-sign"></span>
								Quest Types
							</g:link></li>
						</sec:ifAnyGranted>
						<sec:ifAnyGranted roles="ROLE_PLAYER">
							<li><g:link controller="world" action="choose">
								<span class="glyphicon glyphicon-globe"></span>
								Worlds
							</g:link></li>
							<li><g:link controller="champion" action="index">
								<span class="icon-street-view"></span>
								Champions
							</g:link></li>
							<li><g:link controller="champion" action="index">
								<span class="glyphicon glyphicon-tent"></span>
								Communities
							</g:link></li>
						</sec:ifAnyGranted>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<sec:ifLoggedIn>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									<span class="glyphicon glyphicon-user"></span>
									<sec:loggedInUserInfo field='username'/>
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><g:link controller="user" action="settings">
										<span class="glyphicon glyphicon-cog"></span>
										Settings
									</g:link></li>
									<li><g:link controller="logout">
										<span class="glyphicon glyphicon-log-out"></span>
										Logout
									</g:link></li>
								</ul>
							</li>
						</sec:ifLoggedIn>
						<sec:ifNotLoggedIn>
							<li><g:link controller="login" action="auth">
								<span class="glyphicon glyphicon-log-in"></span>
								Login
							</g:link></li>
							<li><g:link controller="user" action="create">
								<span class="glyphicon glyphicon-pencil"></span>
								Register
							</g:link></li>
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
