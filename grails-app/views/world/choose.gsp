<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'worlds.label')}" />
		<title><g:message code="worlds.choose.title" /></title>
	</head>
	<body>
		<div class="row">
			<div class="col-sm-2"></div>
			
			<div class="col-sm-8">
				<div class="page-header">
					<h2>
						<span class="glyphicon glyphicon-globe"></span>
						<g:message code="worlds.choose.header" />
					</h2>
				</div>
			</div>
		</div>
		
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>
		
		<g:hasErrors bean="${this.world}">
			<div class="alert alert-danger" role="alert">
				<ul>
					<g:eachError bean="${this.world}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
							<g:message error="${error}"/>
						</li>
					</g:eachError>
				</ul>
			</div>
		</g:hasErrors>
		
		<g:hasErrors bean="${this.tutorials}">
			<div class="alert alert-danger" role="alert">
				<ul>
					<g:eachError bean="${this.tutorials}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
							<g:message error="${error}"/>
						</li>
					</g:eachError>
				</ul>
			</div>
		</g:hasErrors>
		
		<div class="row">
			<div class="col-sm-2"></div>
			
			<div class="col-sm-4">
				<ul class="list-group">
					<li class="list-group-item active">
						<h4 class="list-group-item-heading">
							<span class="icon-organization"></span>
							<g:message code="worlds.choose.public.header" />
						</h4>
						<p class="list-group-item-text">
							<g:message code='worlds.choose.public.description' />
						</p>
					</li>
					<g:each in="${multiplayer}" var="world">
						<li class="list-group-item disabled">${world.name}</li>
					</g:each>
				</ul>
			</div>
			
			<div class="col-sm-4">
				<div class="list-group">
					<a class="list-group-item active">
						<h4 class="list-group-item-heading">
							<span class="icon-person"></span>
							<g:message code="worlds.choose.private.header" />
						</h4>
						<p class="list-group-item-text">
							<g:message code='worlds.choose.private.description' />
						</p>
					</a>
					<g:each in="${singleplayer}" var="world">
						<g:link controller="tutorials" action="show" class="list-group-item">${world.name}</g:link>
					</g:each>
					<g:unless test="${startedTutorials}">
						<g:link controller="tutorials" action="show" class="list-group-item list-group-item-success">
							<h4 class="list-group-item-heading">
								<g:message code='worlds.choose.startTutorial.header' />
							</h4>
							<p class="list-group-item-text">
								<g:message code='worlds.choose.startTutorial.description' />
							</p>
						</g:link>
					</g:unless>
				</div>
			</div>
		</div>
	</body>
</html>
