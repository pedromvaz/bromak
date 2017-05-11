<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code='springSecurity.login.title'/></title>
	</head>
	<body>
		<g:if test='${flash.message}'>
			<div class="alert alert-danger" role="alert">${flash.message}</div>
		</g:if>
		
		<div class="row">
			<div class="col-sm-4">
			</div>
			<div class="col-sm-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<g:message code='springSecurity.login.header'/>
					</div>
					<div class="panel-body">
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
							
							<button class="btn btn-primary btn-block" type="submit" id="submit">
								<g:message code='springSecurity.login.button'/>
							</button>
						</form>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-body">
						New to bromak? <g:link controller="user" action="create">Create an account</g:link>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
