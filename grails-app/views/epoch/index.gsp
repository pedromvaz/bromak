<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'epoch.label', default: 'Epoch')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>
		
		<g:each in="${epochList}" var="epoch">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">${epoch.name}</h3>
				</div>
				<div class="panel-body">
					<p>${epoch.description}</p>
					<ul class="nav nav-pills" role="technologies">
						<g:each in="${epoch.technologies}" var="technology">
							<li>
								<a href="#">
									${technology.name}
									<span class="badge">
										<g:message code="technologies.skillCount.label" args="[technology.size()]" />
									</span>
								</a>
							</li>
						</g:each>
						<li>
							<g:link controller="technology" action="create">
								<g:message code="technologies.new.label" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
		</g:each>

		<g:if test="${epochCount > 10}">
			<div class="pagination">
				<g:paginate total="${epochCount ?: 0}" />
			</div>
		</g:if>
		
		<g:link action="create">
			<button type="button" class="btn btn-primary">
				<g:message code="default.new.label" args="[entityName]" />
			</button>
		</g:link>
	</body>
</html>