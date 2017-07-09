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
		
		<g:if test="${champions.isEmpty()}">
			<div class="row">
				<div class="col-sm-4"></div>
				
				<div class="col-sm-4">
					<ul class="list-group">
						<li class="list-group-item" style="padding: 0px">
							<img src="${resource(dir:'images',file:'stone-age/cave-men.jpg')}" style="width: 100%" />
						</li>
						<li class="list-group-item">
							<h5 class="list-group-item-heading">
								<small><g:message code="worlds.show.summonChampion.title" /></small>
							</h5>
							<p class="list-group-item-text">
								<g:message code="worlds.show.summonChampion.description" />
							</p>
						</li>
						<g:link controller="champion" action="create" class="list-group-item list-group-item-success">
							<g:message code="worlds.show.summonChampion.button" />
						</g:link>
					</ul>
				</div>
			</div>
		</g:if>
		
		<g:if test="${champions.size > 0}">
			<div class="row">
				<div class="col-sm-6">
					<ul class="list-group">
						<li class="list-group-item active">
							<g:message code='champions.header' />
						</li>
						<g:each in="${champions}" var="champion">
							<li class="list-group-item">
								${champion.firstName}
							</li>
						</g:each>
					</ul>
				</div>
				
				<div class="col-sm-6">
					<ul class="list-group">
						<li class="list-group-item active">
							<g:message code='communities.show.header' />
						</li>
						<li class="list-group-item">
							Community
						</li>
					</ul>
				</div>
			</div>
				
			<div class="row">
				<div class="col-sm-6">
					<ul class="list-group">
						<li class="list-group-item active">
							Areas
						</li>
						<li class="list-group-item">
							Areas
						</li>
					</ul>
				</div>
				
				<div class="col-sm-6">
					<ul class="list-group">
						<li class="list-group-item active">
							<g:message code="quests.header" />
						</li>
						<li class="list-group-item">
							Quests
						</li>
					</ul>
				</div>
			</div>
		</g:if>
	</body>
</html>
