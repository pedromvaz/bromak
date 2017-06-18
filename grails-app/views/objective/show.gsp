<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'objectives.label')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="col-sm-2"></div>

		<div class="col-sm-8">
			<div class="page-header">
				<h2><g:message code="default.show.label" args="[entityName]" /></h2>
			</div>

			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<g:hasErrors bean="${this.objective}">
				<div class="alert alert-danger" role="alert">
					<ul>
						<g:eachError bean="${this.objective}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
								<g:message error="${error}"/>
							</li>
						</g:eachError>
					</ul>
				</div>
			</g:hasErrors>

			<div class="panel panel-primary">
				<div class="panel-heading">General</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-2"><b><g:message code='objectives.description.label'/></b></div>
						<div class="col-sm-10">${objective.description}</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
