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
		
		<table class="table">
			<thead>
				<tr>
					<th><g:message code="epochs.name.label" /></th>
					<th><g:message code="skillCategories.description.label" /></th>
					<th><g:message code="skillCategories.skills.label" /></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${epochList}" var="epoch">
					<tr>
						<td>${epoch.name}</td>
						<td>${epoch.description}</td>
						<td>
							<div class="dropdown">
								<button class="btn btn-default dropdown-toggle btn-xs" type="button" data-toggle="dropdown">
									<span class="glyphicon glyphicon-education"></span>
									${epoch.getSkillCount()}
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<g:each in="${epoch.skills}" var="skill">
										<li><a>${skill.name}</a></li>
									</g:each>
								</ul>
							</div>
						</td>
						<td>
							<g:link action="edit" id="${epoch.id}">
								<span class="glyphicon glyphicon-edit"></span> Edit
							</g:link>
						</td>
						<td>
							<!--<g:link action="delete" id="${epoch.id}">-->
								<span class="glyphicon glyphicon-trash"></span> Remove
							<!--</g:link>-->
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>

		<g:if test="${epochCount > 10}">
			<div class="pagination">
				<g:paginate total="${epochCount ?: 0}" />
			</div>
		</g:if>
		
		<g:link action="create" class="btn btn-primary">
			<g:message code="default.new.label" args="[entityName]" />
		</g:link>
	</body>
</html>