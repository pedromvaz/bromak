<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'worlds.label')}" />
		<title><g:message code="tutorials.world.name" /></title>
	</head>
	<body>
		<div class="row">
			<div class="col-sm-12">
				<div class="page-header-no-border">
					<h2>
						<g:message code="tutorials.world.name" />
						<small><g:message code="worlds.label" /></small>
					</h2>
				</div>
			</div>
		</div>
		
		<ul class="nav nav-tabs">
			<li class="active">
				<g:link action="show">
					<span class="glyphicon glyphicon-th-list"></span>
					Overview
				</g:link>
			</li>
			<li>
				<g:link action="champions">
					<span class="icon-street-view"></span>
					Champions
				</g:link>
			</li>
			<li>
				<g:link action="community">
					<span class="glyphicon glyphicon-tent"></span>
					Community
				</g:link>
			</li>
			<li>
				<g:link action="areas">
					<span class="glyphicon glyphicon-map-marker"></span>
					Areas
				</g:link>
			</li>
			<li>
				<g:link action="quests">
					<span class="glyphicon glyphicon-exclamation-sign"></span>
					Quests
				</g:link>
			</li>
		</ul>
		
		<div class="col-sm-4">
			<ul class="list-group">
				<li class="list-group-item" style="padding: 0px">
					<img src="${resource(dir:'images',file:'stone-age/cave-men.jpg')}" style="width: 100%" />
				</li>
				<li class="list-group-item">
					<h5 class="list-group-item-heading"><small>SUMMON YOUR FIRST CHAMPION</small></h5>
					<p class="list-group-item-text">
						This is the Champion you will use throughout the tutorials. You can choose its name, race and gender.
					</p>
				</li>
				<g:if test="${hasChampion}">
					<li class="list-group-item">
						<span class="glyphicon glyphicon-ok" style="color:green"></span>
						Champion Summoned
					</li>
				</g:if>
				<g:else>
					<g:link controller="champion" action="create" class="list-group-item list-group-item-success">
						Summon Champion
					</g:link>
				</g:else>
			</ul>
		</div>
	</body>
</html>
