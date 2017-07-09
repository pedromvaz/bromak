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
			<li>
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
			<li class="active">
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
					<img src="${resource(dir:'images',file:'world/regions/mountains-grasslands.jpg')}" style="width: 100%" />
				</li>
				<li class="list-group-item">
					<h5 class="list-group-item-heading"><small>FOOD & WATER</small></h5>
					<p class="list-group-item-text">
						<div class="row">
							<div class="col-sm-4">
								<span class="icon-paw"></span>
								<small>Wild Life</small>
							</div>
							<div class="col-sm-4">
								<span class="glyphicon glyphicon-grain"></span>
								<small>Plants</small>
							</div>
							<div class="col-sm-4">
								<span class="icon-water"></span>
								<small>Water</small>
							</div>
						</div>
					</p>
				</li>
				<li class="list-group-item">
					<h5 class="list-group-item-heading"><small>GATHERING</small></h5>
					<p class="list-group-item-text">
						<div class="row">
							<div class="col-sm-4">
								<span class="glyphicon glyphicon-tree-conifer"></span>
								<small>Wood</small>
							</div>
							<div class="col-sm-4">
								<span class="glyphicon glyphicon-th-large"></span>
								<small>Stone</small>
							</div>
						</div>
					</p>
				</li>
				<li class="list-group-item">
					<h5 class="list-group-item-heading"><small>MINING</small></h5>
					<p class="list-group-item-text">
						<div class="row">
							<div class="col-sm-4">
								<span class="icon-ruby"></span>
								<small>Minerals</small>
							</div>
						</div>
					</p>
				</li>
			</ul>
		</div>
	</body>
</html>
