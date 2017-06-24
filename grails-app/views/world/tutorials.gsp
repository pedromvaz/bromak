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
			<h4>Summon your first Champion</h4>
			
			<img src="${resource(dir:'images',file:'stone-age/male-troglodyte-bw.svg')}" style="width: 50%;margin: auto;display: block" />
			
			<p>This is the Champion you will use throughout the tutorials. You can choose its name, race and gender.</p>
			
			<g:link class="btn btn-success btn-block" controller="champion" action="create" params="['world.id':world.id]">
				Summon Champion
			</g:link>
		</div>
		
		<div class="col-sm-3">
			<h4>Animal hunting</h4>
			
			<img src="${resource(dir:'images',file:'stone-age/deer-bw.svg')}" style="width: 50%;margin: auto;display: block" />
			
			<p>Your first quest will be to hunt a deer with some members from your community.</p>
			
			<g:link class="btn btn-success btn-block disabled" controller="champion" action="create">
				Prepare for the hunt
			</g:link>
		</div>
		
		<div class="col-sm-3">
			<h4>Winter is coming</h4>
			
			<img src="${resource(dir:'images',file:'stone-age/leathers-bw.svg')}" style="width: 50%;margin: auto;display: block" />
			
			<p>Your community must learn to skin the animals they hunt, for clothing, otherwise they won't last the winter.</p>
			
			<g:link class="btn btn-success btn-block disabled" controller="champion" action="create">
				Learn to skin
			</g:link>
		</div>
	</body>
</html>
