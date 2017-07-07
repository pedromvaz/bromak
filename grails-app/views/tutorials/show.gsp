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
					<g:message code="tutorials.world.name" />
					<small><g:message code="worlds.label" /></small>
				</h2>
			</div>
		</div>
		
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
					<h5 class="list-group-item-heading"><small>RESOURCES</small></h5>
					<p class="list-group-item-text">
						<div class="row">
							<div class="col-sm-4">
								<span class="glyphicon glyphicon-tree-conifer"></span>
								<small>Trees</small>
							</div>
							<div class="col-sm-4">
								<span class="glyphicon glyphicon-th-large"></span>
								<small>Stones</small>
							</div>
							<div class="col-sm-4">
								<span class="icon-ruby"></span>
								<small>Minerals</small>
							</div>
						</div>
					</p>
				</li>
			</ul>
		</div>
		
		<div class="col-sm-3">
			<h4>Animal hunting</h4>
			
			<img src="${resource(dir:'images',file:'stone-age/deer-bw.svg')}" style="width: 50%;margin: auto;display: block" />
			
			<p>Your first quest will be to hunt a deer with some members from your community.</p>
			
			<g:link class="btn btn-success btn-block disabled">
				Prepare for the hunt
			</g:link>
		</div>
		
		<div class="col-sm-3">
			<h4>Animal skinning</h4>
			
			<img src="${resource(dir:'images',file:'stone-age/knife-bw.svg')}" style="width: 50%;margin: auto;display: block" />
			
			<p>Your community quickly felt the need to find something sharp with which to cut through animal skin.</p>
			
			<g:link class="btn btn-success btn-block disabled">
				Craft stone tools
			</g:link>
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
