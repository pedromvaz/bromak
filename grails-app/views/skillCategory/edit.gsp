<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'skillCategories.label')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="col-sm-4"></div>

		<div class="col-sm-4">
			<div class="page-header">
				<h2><g:message code="default.edit.label" args="[entityName]" /></h2>
			</div>

			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<g:hasErrors bean="${this.skillCategory}">
				<div class="alert alert-danger" role="alert">
					<ul>
						<g:eachError bean="${this.skillCategory}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
								<g:message error="${error}"/>
							</li>
						</g:eachError>
					</ul>
				</div>
			</g:hasErrors>

			<g:form resource="${this.skillCategory}" method="PUT">
				<div class="form-group">
					<label for="name"><g:message code='skillCategories.name.label'/></label>
					<input type="text" name="name" id="name" class="form-control" value="${skillCategory.name}" placeholder="${message(code: 'skillCategories.name.label')}" required autofocus>
				</div>

				<div class="form-group">
					<label for="description"><g:message code='skillCategories.description.label'/></label>
					<textarea class="form-control" rows="4" name="description" id="description" placeholder="${message(code: 'skillCategories.description.label')}" required>${skillCategory.description}</textarea>
				</div>

				<button class="btn btn-primary btn-block" type="submit" id="submit">
					<g:message code='default.button.update.label'/>
				</button>
			</g:form>
		</div>
	</body>
</html>
