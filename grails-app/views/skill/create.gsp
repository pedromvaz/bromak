<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'skill.label', default: 'Skill')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<g:if test='${flash.message}'>
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>

		<g:hasErrors bean="${this.skill}">
			<div class="alert alert-danger" role="alert">
				<ul>
					<g:eachError bean="${this.skill}" var="error">
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
						<form action="/skill/save" method="POST" id="createSkillForm" class="form-signin" >
							<label for="name" class="sr-only"><g:message code='skills.name.label'/></label>
							<input type="text" name="name" id="name" class="form-control" placeholder="${message(code: 'skills.name.label')}" required autofocus>
							
							<div class="form-group">
								<label for="description" class="sr-only"><g:message code='skills.description.label'/></label>
								<textarea class="form-control" rows="4" name="description" id="description" placeholder="${message(code: 'skills.description.label')}" required></textarea>
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
