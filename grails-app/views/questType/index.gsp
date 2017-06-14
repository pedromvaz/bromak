<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'questType.label', default: 'Quest Type')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="page-header">
			<h2><g:message code="default.list.label" args="[entityName]" /></h2>
		</div>
		
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>
		
		<table class="table">
			<thead>
				<tr>
					<th><g:message code="questTypes.name.label" /></th>
					<th><g:message code="questTypes.description.label" /></th>
					<th><g:message code="questTypes.objectives.label" /></th>
					<th><g:message code="questTypes.groupCap.label" /></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${questTypeList}" var="questType">
					<tr>
						<td>${questType.name}</td>
						<td>${questType.description}</td>
						<td>${questType.objectives}</td>
						<td>${questType.groupCap}</td>
						<td>
							<!--<g:link action="edit" id="${questType.id}">-->
								<span class="glyphicon glyphicon-edit"></span> Edit
							<!--</g:link>-->
						</td>
						<td>
							<!--<g:link action="delete" id="${questType.id}">-->
								<span class="glyphicon glyphicon-trash"></span> Remove
							<!--</g:link>-->
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>

		<g:if test="${questTypeCount > 10}">
			<div class="pagination">
				<g:paginate total="${questTypeCount ?: 0}" />
			</div>
		</g:if>

		<g:link action="create">
			<button type="button" class="btn btn-primary">
				<g:message code="default.new.label" args="[entityName]" />
			</button>
		</g:link>
	</body>
</html>