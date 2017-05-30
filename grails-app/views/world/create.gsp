<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'world.label', default: 'World')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="col-sm-4"></div>
		
		<div class="col-sm-4">
			<div class="page-header">
				<h2><g:message code="default.create.label" args="[entityName]" /></h2>
			</div>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<g:hasErrors bean="${this.world}">
				<div class="alert alert-danger" role="alert">
					<ul>
						<g:eachError bean="${this.world}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
								<g:message error="${error}"/>
							</li>
						</g:eachError>
					</ul>
				</div>
			</g:hasErrors>
			
			<form action="/world/save" method="POST" id="createWorldForm">
				<div class="form-group">
					<label for="name"><g:message code='worlds.name.label'/></label>
					<input type="text" name="name" id="name" class="form-control" placeholder="${message(code: 'worlds.name.label')}" required autofocus>
				</div>
				
				<div class="form-group">
					<label for="radius"><g:message code='worlds.radius.label'/></label>
					<input class="form-control" type="number" name="radius" id="radius" value="0" min="0" required />
				</div>
				
				<button class="btn btn-primary btn-block" type="submit" id="submit">
					<g:message code='default.button.create.label'/>
				</button>
			</form>
		</div>
	</body>
</html>
