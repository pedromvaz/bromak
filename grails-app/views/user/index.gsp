<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
					<th><g:message code="users.username.label" /></th>
					<th><g:message code="users.email.label" /></th>
					<th><g:message code="users.created.label" /></th>
					<th><g:message code="users.status.label" /></th>
					<th><g:message code="roles.label" /></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${userList}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td><g:formatDate format="yyyy-MM-dd" date="${user.dateCreated}"/></td>
						<td>
							<g:if test="${user.enabled}">
								<span class="label label-success"><g:message code="users.enabled.label" /></span>
							</g:if>
							<g:else>
								<span class="label label-danger"><g:message code="users.disabled.label" /></span>
							</g:else>

							<g:if test="${user.accountExpired}">
								<span class="label label-danger"><g:message code="users.accountExpired.label" /></span>
							</g:if>

							<g:if test="${user.accountLocked}">
								<span class="label label-danger"><g:message code="users.accountLocked.label" /></span>
							</g:if>

							<g:if test="${user.passwordExpired}">
								<span class="label label-danger"><g:message code="users.passwordExpired.label" /></span>
							</g:if>
						</td>
						<td>
							<g:each in="${user.getAuthorities()}" var="role">
								<span class="label label-default">${role.authority}</span>
							</g:each>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>

		<g:if test="${userCount > 10}">
				<div class="pagination">
					<g:paginate total="${userCount ?: 0}" />
				</div>
			</g:if>

		<g:link action="create">
			<button type="button" class="btn btn-primary">
				<g:message code="default.new.label" args="[entityName]" />
			</button>
		</g:link>
	</body>
</html>
