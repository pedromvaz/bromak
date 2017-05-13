<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'epoch.label', default: 'Epoch')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="page-header">
			<h2><g:message code="epochs.index.header" /></h2>
		</div>
		
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>
		
		<g:each in="${epochList}" var="epoch">
			<div class="list-group">
				<a class="list-group-item active">
					<h4 class="list-group-item-heading">${epoch.name}</h4>
					<p class="list-group-item-text">${epoch.description}</p>
				</a>
				<g:each in="${epoch.skills}" var="skill">
					<a class="list-group-item">
						<h4 class="list-group-item-heading">${skill.name}</h4>
						<p class="list-group-item-text">${skill.description}</p>
						<h4>
							<g:each in="${com.bromakgame.creatures.Race.getAllThatCanLearn(skill.id)}" var="race">
								<span class="label label-success">${race.name}</span>
							</g:each>
						</h4>
					</a>
				</g:each>
				<g:link controller="skill" action="create" params="[epochId:epoch.id]" class="list-group-item">
					<g:message code="skills.new.label" />
				</g:link>
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