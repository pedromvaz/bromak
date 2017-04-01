<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
		<g:if test='${flash.message}'>
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>

		<g:hasErrors bean="${this.user}">
			<ul class="errors" role="alert">
				<g:eachError bean="${this.user}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</g:hasErrors>

		<div class="row">
			<div class="col-sm-4">
			</div>
			<div class="col-sm-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><g:message code="default.create.label" args="[entityName]" /></h3>
					</div>
					<div class="panel-body">
						<form action="/user/save" method="POST" id="createUserForm" class="form-signin" >
							<label for="username" class="sr-only"><g:message code='users.username.label'/></label>
							<input type="text" name="username" id="username" class="form-control" placeholder="<g:message code='users.username.label'/>" required autofocus>
							
							<label for="email" class="sr-only"><g:message code='users.email.label'/></label>
							<input type="email" name="email" id="email" class="form-control" placeholder="<g:message code='users.email.label'/>" required autofocus>
							
							<label for="password" class="sr-only"><g:message code='users.password.label'/></label>
							<input type="password" name="password" id="password" class="form-control" placeholder="<g:message code='users.password.label'/>" required autofocus>
							
							<sec:ifAnyGranted roles="ROLE_ADMIN">
								<div class="checkbox">
									<label>
										<input type="checkbox" name="enabled" id="enabled" checked="checked">
										<g:message code='users.enabled.label'/>
									</label>
								</div>
								
								<div class="checkbox">
									<label>
										<input type="checkbox" name="accountExpired" id="accountExpired">
										<g:message code='users.accountExpired.label'/>
									</label>
								</div>
								
								<div class="checkbox">
									<label>
										<input type="checkbox" name="accountLocked" id="accountLocked">
										<g:message code='users.accountLocked.label'/>
									</label>
								</div>
								
								<div class="checkbox">
									<label>
										<input type="checkbox" name="passwordExpired" id="passwordExpired">
										<g:message code='users.passwordExpired.label'/>
									</label>
								</div>
							</sec:ifAnyGranted>
							
							<button class="btn btn-lg btn-primary btn-block" type="submit" id="submit"><g:message code='default.button.create.label'/></button>
						</form>
					</div>
				</div>
			</div>
        </div>
    </body>
</html>
