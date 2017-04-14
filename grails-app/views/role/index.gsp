<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'role.label', default: 'Role')}" />
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
					<!--<f:table collection="${userList}" class="table" />-->
					<table class="table">
						<thead>
							<tr>
								<th><g:message code="roles.authority.label" /></th>
								<th><g:message code="roles.description.label" /></th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${roleList}" var="role">
								<tr>
									<td>${role.authority}</td>
									<td>${role.description}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
					
					<g:if test="${roleCount > 10}">
						<div class="pagination">
							<g:paginate total="${roleCount ?: 0}" />
						</div>
					</g:if>
					
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