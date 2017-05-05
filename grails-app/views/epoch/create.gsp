<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'epoch.label', default: 'Epoch')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<g:if test='${flash.message}'>
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>

		<g:hasErrors bean="${this.epoch}">
			<div class="alert alert-danger" role="alert">
				<ul>
					<g:eachError bean="${this.epoch}" var="error">
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
						<h3 class="panel-title"><g:message code="default.create.label" args="[entityName]" /></h3>
					</div>
					<div class="panel-body">
						<form action="/epoch/save" method="POST" id="createEpochForm" class="form-signin" >
							<label for="name" class="sr-only"><g:message code='epochs.name.label'/></label>
							<input type="text" name="name" id="name" class="form-control" placeholder="<g:message code='epochs.name.label'/>" required autofocus>
							
							<label for="description" class="sr-only"><g:message code='epochs.description.label'/></label>
							<input type="text" name="description" id="description" class="form-control" placeholder="<g:message code='epochs.description.label'/>" required>

							<button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">
								<g:message code='default.button.create.label'/>
							</button>
						</form>
					</div>
				</div>
			</div>
        </div>
		
	</body>
</html>
