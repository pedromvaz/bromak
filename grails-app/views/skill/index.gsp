<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'skills.label')}" />
		<title><g:message code="skills.header" /></title>
	</head>
	<body>
		<div class="page-header">
			<h2><g:message code="skills.header" /></h2>
		</div>
		
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>
		
		<table class="table">
			<thead>
				<tr>
					<th><g:message code="skills.name.label" /></th>
					<th><g:message code="skills.description.label" /></th>
					<th><g:message code="skills.category.label" /></th>
					<th><g:message code="skills.epoch.label" /></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${skillList}" var="skill">
					<tr>
						<td>${skill.name}</td>
						<td>${skill.description}</td>
						<td>${skill.category?.name}</td>
						<td>${skill.epoch?.name}</td>
						<td>
							<g:link action="edit" id="${skill.id}">
								<span class="glyphicon glyphicon-edit"></span> Edit
							</g:link>
						</td>
						<td>
							<!--<g:link action="delete" id="${skill.id}">-->
								<span class="glyphicon glyphicon-trash"></span> Remove
							<!--</g:link>-->
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>

		<g:if test="${skillCount > 10}">
			<div class="pagination">
				<g:paginate total="${skillCount ?: 0}" />
			</div>
		</g:if>

		<g:link class="btn btn-primary" action="create">
			<g:message code="default.new.label" args="[entityName]" />
		</g:link>
	</body>
</html>