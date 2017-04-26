<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'community.label', default: 'Community')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row">
			<div class="col-sm-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><g:message code="communities.population.label" /></h3>
					</div>
					<div class="panel-body">
						<p />
						<p>
							<span class="progress-label">Champions</span>
							<div class="progress">
								<div class="progress-bar" role="progressbar" style="width:5%"></div>
							</div>
						</p>
						<p>
							<span class="progress-label"><g:message code="creatures.gender.male.label" /></span>
							<div class="progress">
								<div class="progress-bar" role="progressbar" style="width:40%"></div>
							</div>
						</p>
						<p>
							<span class="progress-label"><g:message code="creatures.gender.female.label" /></span>
							<div class="progress">
								<div class="progress-bar" role="progressbar" style="width:55%"></div>
							</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
