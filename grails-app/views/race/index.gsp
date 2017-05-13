<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'race.label', default: 'Race')}" />
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
					<th><g:message code="races.name.label" /></th>
					<th><g:message code="races.description.label" /></th>
					<th><g:message code="races.startingPopulation.label" /></th>
					<th><g:message code="races.cognition.label" /></th>
					<th><g:message code="races.status.label" /></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${raceList}" var="race">
					<tr>
						<td>${race.name}</td>
						<td>${race.description}</td>
						<td>${race.startingPopulation}</td>
						<td>
							<g:if test="${race.intelligent}">
								<span class="label label-success"><g:message code="races.intelligent.label" /></span>
							</g:if>
							<g:else>
								<span class="label label-danger"><g:message code="races.primitive.label" /></span>
							</g:else>
						</td>
						<td>
							<g:if test="${race.enabled}">
								<span class="label label-success"><g:message code="races.enabled.label" /></span>
							</g:if>
							<g:else>
								<span class="label label-danger"><g:message code="races.disabled.label" /></span>
							</g:else>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>

		<g:if test="${raceCount > 10}">
			<div class="pagination">
				<g:paginate total="${raceCount ?: 0}" />
			</div>
		</g:if>

		<g:link action="create">
			<button type="button" class="btn btn-primary">
				<g:message code="default.new.label" args="[entityName]" />
			</button>
		</g:link>
	</body>
</html>