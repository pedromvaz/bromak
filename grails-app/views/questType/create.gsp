<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'questTypes.label')}" />
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

			<g:hasErrors bean="${this.questType}">
				<div class="alert alert-danger" role="alert">
					<ul>
						<g:eachError bean="${this.questType}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
								<g:message error="${error}"/>
							</li>
						</g:eachError>
					</ul>
				</div>
			</g:hasErrors>

			<g:form action="save">
				<div class="form-group">
					<label for="name"><g:message code='questTypes.name.label'/></label>
					<input type="text" name="name" id="name" class="form-control" placeholder="${message(code: 'questTypes.name.label')}" required autofocus>
				</div>

				<div class="form-group">
					<label for="description"><g:message code='questTypes.description.label'/></label>
					<textarea class="form-control" rows="4" name="description" id="description" placeholder="${message(code: 'questTypes.description.label')}" required></textarea>
				</div>

				<div class="form-group">
					<label for="groupCap"><g:message code='questTypes.groupCap.label'/></label>
					<input class="form-control" type="number" name="groupCap" id="groupCap" value="1" min="1" required />
				</div>

				<button class="btn btn-primary btn-block" type="submit" id="submit">
					<g:message code='default.button.create.label'/>
				</button>
			</g:form>
		</div>
	</body>
</html>
