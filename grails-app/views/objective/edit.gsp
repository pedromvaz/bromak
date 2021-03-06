<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'objectives.label')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="col-sm-4"></div>

		<div class="col-sm-4">
			<div class="page-header">
				<h2><g:message code="default.edit.label" args="[entityName]" /></h2>
			</div>

			<g:if test="${flash.message}">
				<div class="alert alert-info" role="alert">${flash.message}</div>
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

			<g:form resource="${this.objective}" action="update">
				<div class="form-group">
					<label for="description"><g:message code='objectives.description.label'/></label>
					<textarea class="form-control" rows="4" name="description" id="description" placeholder="${message(code: 'objectives.description.label')}" required autofocus>${objective.description}</textarea>
				</div>

				<div class="form-group">
					<label for="skillCategory.id"><g:message code='skillCategories.label'/></label>
					<g:select class="form-control" name="skillCategory.id" from="${skillCategories}" value="${objective.skillCategory.id}" optionKey="id" optionValue="name" />
				</div>

				<button class="btn btn-primary btn-block" type="submit" id="submit">
					<g:message code='default.button.update.label'/>
				</button>
			</g:form>
		</div>
	</body>
</html>
