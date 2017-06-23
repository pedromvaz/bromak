<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'epochs.label')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="col-sm-3"></div>

		<div class="col-sm-6">
			<div class="page-header">
				<h2>
					${epoch.name}
					<small><g:message code="epochs.label" /></small>
				</h2>
			</div>

			<g:if test="${flash.message}">
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

			<g:form resource="${this.epoch}" method="PUT">
				<div class="form-group">
					<label for="name"><g:message code='epochs.name.label'/></label>
					<input type="text" name="name" id="name" class="form-control" value="${epoch.name}" placeholder="${message(code: 'epochs.name.label')}" required autofocus>
				</div>

				<div class="form-group">
					<label for="description"><g:message code='epochs.description.label'/></label>
					<textarea class="form-control" rows="4" name="description" id="description" placeholder="${message(code: 'epochs.description.label')}" required>${epoch.description}</textarea>
				</div>

				<button class="btn btn-primary btn-block" type="submit" id="submit">
					<g:message code='default.button.update.label'/>
				</button>
			</g:form>
		</div>
    </body>
</html>
