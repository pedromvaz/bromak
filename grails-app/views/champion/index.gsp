<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'champion.label', default: 'Champion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>

		<!--<div class="col-sm-6">-->
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title"><g:message code="default.list.label" args="[entityName]" /></h3>
				</div>
				<div class="panel-body">
					<table class="table">
						<thead>
							<tr>
								<th><g:message code="creatures.firstName.label" /></th>
								<th><g:message code="creatures.lastName.label" /></th>
								<th><g:message code="creatures.title.label" /></th>
								<th><g:message code="creatures.race.label" /></th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${championList}" var="champion">
								<tr>
									<td>${champion.firstName}</td>
									<td>${champion.lastName}</td>
									<td>${champion.title}</td>
									<td>${champion.race.name}</td>
								</tr>
							</g:each>
						</tbody>
					</table>

					<div class="pagination">
						<g:paginate total="${championCount ?: 0}" />
					</div>

					<g:link action="create">
						<button type="button" class="btn btn-primary">
							<g:message code="default.new.label" args="[entityName]" />
						</button>
					</g:link>
				</div>
			</div>
		<!--</div>-->
	</body>
</html>