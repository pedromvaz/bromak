<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'questTypes.label')}" />
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
					<th><g:message code="questTypes.groupCap.label" /></th>
					<th><g:message code="questTypes.objectives.label" /></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${questTypeList}" var="questType">
					<tr>
						<td>
							<g:link action="show" id="${questType.id}">
								${questType.name}
							</g:link>
						</td>
						<td>${questType.description}</td>
						<td>${questType.groupCap}</td>
						<td>
							<g:if test="${questType.objectives.size() == 0}">
								<span class="glyphicon glyphicon glyphicon-remove-sign" style="color:red"></span>
								None
							</g:if>
							<g:else>
								${questType.objectives.size()}
							</g:else>
						</td>
						<td>
							<g:link action="edit" id="${questType.id}">
								<span class="glyphicon glyphicon-edit"></span> Edit
							</g:link>
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