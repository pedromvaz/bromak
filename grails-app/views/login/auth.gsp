<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code='springSecurity.login.title'/></title>
	</head>
	<body>
		<div class="col-sm-4"></div>
		
		<div class="col-sm-4">
			<div class="page-header">
				<h2><g:message code="login.header" /></h2>
			</div>
			
			<g:if test='${flash.message}'>
				<div class="alert alert-danger" role="alert">${flash.message}</div>
			</g:if>
			
			<form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm">
				<div class="form-group">
					<label for="username" class="sr-only"><g:message code='springSecurity.login.username.label'/></label>
					<input type="text" name="${usernameParameter ?: 'username'}" id="username" class="form-control" placeholder="<g:message code='springSecurity.login.username.label'/>" required autofocus>
				</div>

				<div class="form-group">
					<label for="password" class="sr-only"><g:message code='springSecurity.login.password.label'/></label>
					<input type="password" name="${passwordParameter ?: 'password'}" id="password" class="form-control" placeholder="<g:message code='springSecurity.login.password.label'/>" required>
				</div>

				<!--<div class="checkbox">
					<label>
						<input type="checkbox" value="remember-me"> Remember me
					</label>
				</div>-->

				<div class="form-group">
					<button class="btn btn-primary btn-block" type="submit" id="submit">
						<g:message code='springSecurity.login.button'/>
					</button>
				</div>

				<p class="text-center">
					New to bromak? <g:link controller="user" action="create">Create an account</g:link>
				</p>
			</form>
		</div>
	</body>
</html>
