<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'worlds.label')}" />
		<title><g:message code="tutorials.world.name" /></title>
	</head>
	<body>
		<div class="row">
			<div class="page-header">
				<h2>
					<g:message code="tutorials.label" />
				</h2>
			</div>
		</div>
		
		<div class="col-sm-3">
			<div class="list-group">
				<a class="list-group-item active">
					<g:message code="tutorials.tutorial.header" /> 1
				</a>
				<a class="list-group-item">
					<img src="${resource(dir:'images',file:'world/svg/hills.svg')}" style="width: 120px;height: 120px" />
				</a>
				<g:link action="tutorials" class="list-group-item list-group-item-success">
					Start Tutorial
				</g:link>
			</ul>
		</div>
	</body>
</html>
