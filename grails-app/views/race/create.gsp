<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'race.label', default: 'Race')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		
		<g:hasErrors bean="${this.race}">
			<div class="alert alert-danger" role="alert">
				<ul>
					<g:eachError bean="${this.race}" var="error">
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
						<form action="/race/save" method="POST" id="createRaceForm" class="form-signin" >
							<label for="name" class="sr-only"><g:message code='races.name.label'/></label>
							<input type="text" name="name" id="name" class="form-control" placeholder="${message(code: 'races.name.label')}" required autofocus>
							
							<div class="form-group">
								<label for="description" class="sr-only"><g:message code='races.description.label'/></label>
								<textarea class="form-control" rows="4" name="description" id="description" placeholder="${message(code: 'races.description.label')}" required></textarea>
							</div>
							
							<label for="startingPopulation" class="sr-only"><g:message code='races.startingPopulation.label'/></label>
							<input type="text" name="startingPopulation" id="startingPopulation" class="form-control" placeholder="${message(code: 'races.startingPopulation.label')}" required>
							
							<div class="checkbox">
								<label>
									<input type="checkbox" name="intelligent" id="intelligent">
									<g:message code='races.intelligent.label'/>
								</label>
							</div>
							
							<div class="checkbox">
								<label>
									<input type="checkbox" name="enabled" id="enabled" checked="checked">
									<g:message code='races.enabled.label'/>
								</label>
							</div>
							
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
