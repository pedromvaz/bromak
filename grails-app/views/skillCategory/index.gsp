<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'skillCategories.label')}" />
		<title><g:message code="skillCategories.header" /></title>
	</head>
	<body>
		<div class="page-header">
			<h2><g:message code="skillCategories.header" /></h2>
		</div>
		
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>
		
		<table class="table">
			<thead>
				<tr>
					<th><g:message code="skillCategories.name.label" /></th>
					<th><g:message code="skillCategories.description.label" /></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${skillCategoryList}" var="category">
					<tr>
						<td>${category.name}</td>
						<td>${category.description}</td>
						<td>
							<g:link action="edit" id="${category.id}">
								<span class="glyphicon glyphicon-edit"></span> Edit
							</g:link>
						</td>
						<td>
							<!--<g:link action="delete" id="${category.id}">-->
								<span class="glyphicon glyphicon-trash"></span> Remove
							<!--</g:link>-->
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>

		<g:if test="${skillCategoryCount > 10}">
			<div class="pagination">
				<g:paginate total="${skillCategoryCount ?: 0}" />
			</div>
		</g:if>

		<g:link action="create">
			<button type="button" class="btn btn-primary">
				<g:message code="default.new.label" args="[entityName]" />
			</button>
		</g:link>
	</body>
</html>