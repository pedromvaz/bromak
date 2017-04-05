<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<!-- Temporary tabs, will create an admin-specific layout -->
		<!--
		<div class="navbar">
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#">Users</a></li>
				<li role="presentation"><a href="#">Roles</a></li>
				<li role="presentation"><a href="#">Races</a></li>
			</ul>
		</div>
		-->
		
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
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
								<th><g:message code="users.username.label" /></th>
								<th><g:message code="users.email.label" /></th>
								<th><g:message code="users.status.label" /></th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${userList}" var="user">
								<tr>
									<td>${user.username}</td>
									<td>${user.email}</td>
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
								</tr>
							</g:each>
						</tbody>
					</table>

					<div class="pagination">
						<g:paginate total="${userCount ?: 0}" />
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
