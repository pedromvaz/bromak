<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'world.label', default: 'World')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="page-header">
			<h2><g:message code="worlds.index.header" /></h2>
		</div>
		
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>
		
		<table class="table">
			<thead>
				<tr>
					<th><g:message code="worlds.name.label" /></th>
					<th><g:message code="worlds.radius.label" /></th>
					<th><g:message code="worlds.numRegions.label" /></th>
					<th><g:message code="worlds.numChampions.label" /></th>
					<th><g:message code="worlds.numSettlements.label" /></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${worldList}" var="world">
					<tr>
						<td>
							<g:link controller="world" action="show" id="${world.id}">
								${world.name}
							</g:link>
						</td>
						<td>${world.radius}</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</g:each>
			</tbody>
		</table>
		
		<g:if test="${worldCount > 10}">
			<div class="pagination">
				<g:paginate total="${worldCount ?: 0}" />
			</div>
		</g:if>
		
		<g:link action="create">
		<button type="button" class="btn btn-primary">
			<g:message code="default.new.label" args="[entityName]" />
		</button>
		</g:link>
	</body>
</html>