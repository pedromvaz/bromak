<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code='login.auth.title'/></title>
	</head>
	<body>
		<div class="col-sm-4"></div>
		
		<div class="col-sm-4">
			<div class="page-header">
				<h2><g:message code="login.auth.header" /></h2>
			</div>
			
			<g:if test='${flash.message}'>
				<div class="alert alert-info" role="alert">${flash.message}</div>
			</g:if>
			
			<g:if test='${flash.success}'>
				<div class="alert alert-success" role="alert">${flash.success}</div>
			</g:if>
			
			<g:if test='${flash.failure}'>
				<div class="alert alert-danger" role="alert">${flash.failure}</div>
			</g:if>
			
			<form controller="login" action="authenticate" method="POST">
				<div class="form-group">
					<label for="username" class="sr-only"><g:message code='login.auth.username.label'/></label>
					<input type="text" name="username" id="username" class="form-control" placeholder="<g:message code='login.auth.username.label'/>" required autofocus>
				</div>

				<div class="form-group">
					<label for="password" class="sr-only"><g:message code='login.auth.password.label'/></label>
					<input type="password" name="password" id="password" class="form-control" placeholder="<g:message code='login.auth.password.label'/>" required>
				</div>

				<!--<div class="checkbox">
					<label>
						<input type="checkbox" value="remember-me"> Remember me
					</label>
				</div>-->

				<div class="form-group">
					<button class="btn btn-primary btn-block" type="submit" id="submit">
						<g:message code='login.auth.button' />
					</button>
				</div>

				<p class="text-center">
					<g:message code='login.auth.newToBromak.label' />
					<g:link controller="user" action="create">
						<g:message code='login.auth.createAccount.link' />
					</g:link>
				</p>
			</form>
		</div>
	</body>
</html>
