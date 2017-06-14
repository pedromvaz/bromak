<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'questType.label', default: 'Quest Type')}" />
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

			<form action="/questType/save" method="POST" id="createQuestTypeForm">
				<div class="form-group">
					<label for="name" class="sr-only"><g:message code='questTypes.name.label'/></label>
					<input type="text" name="name" id="name" class="form-control" placeholder="${message(code: 'questTypes.name.label')}" required autofocus>
				</div>

				<div class="form-group">
					<label for="description" class="sr-only"><g:message code='questTypes.description.label'/></label>
					<textarea class="form-control" rows="4" name="description" id="description" placeholder="${message(code: 'questTypes.description.label')}" required></textarea>
				</div>

				<div class="form-group">
					<label for="groupCap" class="sr-only"><g:message code='questTypes.groupCap.label'/></label>
					<input class="form-control" type="number" name="groupCap" id="groupCap" value="1" min="1" required />
				</div>

				<div class="form-group">
					<table class="table">
						<thead>
							<tr>
								<th><g:message code='questTypes.objectives.label'/></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="">
											Kill a wild animal
										</label>
									</div>
								</td>
						</tbody>
					</table>
				</div>

				<button class="btn btn-primary btn-block" type="submit" id="submit">
					<g:message code='default.button.create.label'/>
				</button>
			</form>
		</div>
	</body>
</html>
