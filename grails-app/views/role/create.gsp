<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'role.label', default: 'Role')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>
		
		<g:hasErrors bean="${this.role}">
			<div class="alert alert-danger" role="alert">
				<ul class="errors" role="alert">
					<g:eachError bean="${this.role}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
							<g:message error="${error}"/>
						</li>
					</g:eachError>
				</ul>
			</div>
		</g:hasErrors>
		
		<div class="row">
			<div class="col-sm-4">
			</div>
			<div class="col-sm-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<g:message code="default.create.label" args="[entityName]" />
					</div>
					<div class="panel-body">
						<form action="/role/save" method="POST" id="createRoleForm">
							<div class="form-group">
								<label for="authority" class="sr-only"><g:message code='roles.authority.label'/></label>
								<input type="text" name="authority" id="authority" class="form-control" placeholder="${message(code: 'roles.authority.label')}" required autofocus>
							</div>

							<div class="form-group">
								<label for="description" class="sr-only"><g:message code='roles.description.label'/></label>
								<textarea class="form-control" rows="4" name="description" id="description" placeholder="${message(code: 'roles.description.label')}" required></textarea>
							</div>
							
							<button class="btn btn-primary btn-block" type="submit" id="submit">
								<g:message code='default.button.create.label'/>
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
