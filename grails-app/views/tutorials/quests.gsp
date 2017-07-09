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
			<li>
				<g:link action="areas">
					<span class="glyphicon glyphicon-map-marker"></span>
					Areas
				</g:link>
			</li>
			<li class="active">
				<g:link action="quests">
					<span class="glyphicon glyphicon-exclamation-sign"></span>
					Quests
				</g:link>
			</li>
		</ul>
		
		<div class="row">
			<div class="col-sm-4">
				<ul class="list-group">
					<li class="list-group-item" style="padding: 0px">
						<img src="${resource(dir:'images',file:'quests/hunting-deer.jpg')}" style="width: 100%" />
					</li>
					<li class="list-group-item">
						<h5 class="list-group-item-heading"><small>ANIMAL HUNTING</small></h5>
						<p class="list-group-item-text">
							Your first quest will be to hunt a deer with some members from your community.
						</p>
					</li>
					<g:link controller="quest" action="prepare" class="list-group-item list-group-item-success">
						Prepare for the hunt
					</g:link>
				</ul>
			</div>
			
			<div class="col-sm-4">
				<ul class="list-group">
					<li class="list-group-item" style="padding: 0px">
						<img src="${resource(dir:'images',file:'quests/skinning.jpg')}" style="width: 100%" />
					</li>
					<li class="list-group-item">
						<h5 class="list-group-item-heading"><small>ANIMAL SKINNING</small></h5>
						<p class="list-group-item-text">
							Your community quickly felt the need to find something sharp with which to cut through animal skin.
						</p>
					</li>
					<g:link controller="quest" action="prepare" class="list-group-item list-group-item-success">
						Craft stone tools
					</g:link>
				</ul>
			</div>
		</div>
		
		<div class="col-sm-3">
			<h4>Winter is coming</h4>
			
			<img src="${resource(dir:'images',file:'stone-age/pelt-bw.svg')}" style="width: 50%;margin: auto;display: block" />
			
			<p>Your community must learn to skin the animals they hunt, for clothing, otherwise they won't last the winter.</p>
			
			<g:link class="btn btn-success btn-block disabled">
				Learn to skin
			</g:link>
		</div>
		
		<div class="col-sm-3">
			<h4>To arms!</h4>
			
			<img src="${resource(dir:'images',file:'stone-age/spear-bw.svg')}" style="width: 50%;margin: auto;display: block" />
			
			<p>Having suffered an attack from a sabertooth, the community feels threatened.</p>
			
			<g:link class="btn btn-success btn-block disabled">
				Craft the first spear
			</g:link>
		</div>
		
		<div class="col-sm-3">
			<h4>Fire!</h4>
			
			<img src="${resource(dir:'images',file:'stone-age/bonfire-bw.svg')}" style="width: 50%;margin: auto;display: block" />
			
			<p>The thunderstorm left behind a threat, one that could be used for the community's benefit.</p>
			
			<g:link class="btn btn-success btn-block disabled">
				Gather fire
			</g:link>
		</div>
	</body>
</html>
