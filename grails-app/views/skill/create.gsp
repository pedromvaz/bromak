<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'skills.label')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="col-sm-4"></div>
		
		<div class="col-sm-4">
			<div class="page-header">
				<h2><g:message code="default.create.label" args="[entityName]" /></h2>
			</div>
			
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
			
			<form action="save" method="POST">
				<div class="form-group">
					<label for="name" class="sr-only"><g:message code='skills.name.label'/></label>
					<input type="text" name="name" id="name" class="form-control" placeholder="${message(code: 'skills.name.label')}" required autofocus>
				</div>

				<div class="form-group">
					<label for="description" class="sr-only"><g:message code='skills.description.label'/></label>
					<textarea class="form-control" rows="4" name="description" id="description" placeholder="${message(code: 'skills.description.label')}" required></textarea>
				</div>
				
				<div class="form-group">
					<label for="category.id"><g:message code='skillCategories.label'/></label>
					<g:select class="form-control" name="category.id" from="${categories}" optionKey="id" optionValue="name" noSelection="${['null':'Select One...']}" />
				</div>
				
				<div class="form-group">
					<label for="epoch.id"><g:message code='epochs.label'/></label>
					<g:select class="form-control" name="epoch.id" from="${epochs}" optionKey="id" optionValue="name" noSelection="${['null':'Select One...']}" />
				</div>

				<button class="btn btn-primary btn-block" type="submit" id="submit">
					<g:message code='default.button.create.label'/>
				</button>
			</form>
		</div>
	</body>
</html>
