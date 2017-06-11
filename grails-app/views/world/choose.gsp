<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'world.label', default: 'World')}" />
		<title><g:message code="worlds.choose.title" /></title>
	</head>
	<body>
		<div class="row">
			<div class="col-sm-2"></div>
			
			<div class="col-sm-8">
				<div class="page-header">
					<h2>
						<span class="glyphicon glyphicon-globe"></span>
						<g:message code="worlds.choose.title" />
					</h2>
				</div>
			</div>
		</div>
		
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		
		<div class="row">
			<div class="col-sm-2"></div>
			
			<div class="col-sm-8">
				<ul class="list-group">
					<li class="list-group-item active">
						<h4 class="list-group-item-heading"><g:message code="worlds.public.label" /></h4>
						<p class="list-group-item-text">A list of public worlds where multiple users play simultaneously</p>
					</li>
					<g:each in="${multiplayer}" var="world">
						<li class="list-group-item disabled">${world.name}</li>
					</g:each>
				</ul>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-2"></div>
			
			<div class="col-sm-8">
				<div class="list-group">
					<a class="list-group-item active">
						<h4 class="list-group-item-heading"><g:message code="worlds.private.label" /></h4>
						<p class="list-group-item-text">A list of private worlds where only the logged in user can play</p>
					</a>
					<g:each in="${singleplayer}" var="world">
						<g:link action="tutorials" class="list-group-item" id="${world.id}">${world.name}</g:link>
					</g:each>
					<g:unless test="${startedTutorials}">
						<g:link action="tutorials" class="list-group-item list-group-item-success">
							<h4 class="list-group-item-heading">Start Tutorials</h4>
							<p class="list-group-item-text">Learn how to play</p>
						</g:link>
					</g:unless>
				</ul>
			</div>
		</div>
	</body>
</html>
