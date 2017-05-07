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
						<div class="col-sm-6">
							<span class="progress-label"><g:message code="creatures.gender.male.label" /></span>
							<div class="progress">
								<div class="progress-bar" style="width:${maleChampions}%"></div>
								<div class="progress-bar progress-bar-info" style="width:${males}%"></div>
							</div>
						</div>
						<div class="col-sm-6">
							<span class="progress-label"><g:message code="creatures.gender.female.label" /></span>
							<div class="progress">
								<div class="progress-bar" style="width:${femaleChampions}%"></div>
								<div class="progress-bar progress-bar-info" style="width:${females}%"></div>
							</div>
						</div>
						<div class="col-sm-12">
							<span class="progress-label">Happiness</span>
							<div class="progress">
								<div class="progress-bar" style="width:70%"></div>
							</div>
						</div>
						<div class="col-sm-12">
							<span class="progress-label">Food</span>
							<div class="progress">
								<div class="progress-bar" style="width:60%"></div>
							</div>
						</div>
						<div class="col-sm-12">
							<span class="progress-label">Water</span>
							<div class="progress">
								<div class="progress-bar" style="width:90%"></div>
							</div>
						</div>
						<div class="col-sm-12">
							<span class="progress-label">Safety</span>
							<div class="progress">
								<div class="progress-bar" style="width:50%"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<ul class="list-group">
					<li class="list-group-item active">
						<h4 class="list-group-item-heading"><g:message code="communities.skills.label" /></h4>
					</li>
					<g:each in="${com.bromakgame.learning.Epoch.list()}" var="epoch">
						<li class="list-group-item">
							<h5 class="list-group-item-heading">${epoch.name}</h5>
							<ul class="nav nav-pills">
								<g:each in="${epoch.skills}" var="skill">
									<li role="presentation">
										<a>
											${skill.name}
											<span class="badge">
												${this.community.countCreaturesThatKnowSkill(skill.id)}
											</span>
										</a>
									</li>
								</g:each>
							</ul>
						</li>
					</g:each>
				</ul>
			</div>
		</div>
	</body>
</html>
