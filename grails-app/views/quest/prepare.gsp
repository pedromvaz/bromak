<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'quests.label')}" />
		<title><g:message code="quests.prepare.title" /></title>
	</head>
	<body>
		<div class="row">
			<div class="col-sm-12">
				<div class="page-header">
					<h2>
						<g:message code="quests.prepare.header" />
					</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<ul class="list-group">
					<li class="list-group-item active">
						<h4 class="list-group-item-heading">
							<g:message code="quests.prepare.quest.header" />
						</h4>
						<p class="list-group-item-text">
							<g:message code="quests.prepare.quest.description" />
						</p>
					</li>
					<li class="list-group-item">
						<h5 class="list-group-item-heading">
							<small><g:message code="quests.prepare.quest.title.label" /></small>
						</h5>
						<p>${quest.title}</p>
					</li>
					<li class="list-group-item">
						<h5 class="list-group-item-heading">
							<small><g:message code="quests.prepare.quest.description.label" /></small>
						</h5>
						<p>${quest.description}</p>
					</li>
					<li class="list-group-item">
						<h5 class="list-group-item-heading">
							<small><g:message code="quests.prepare.quest.objectives.label" /></small>
						</h5>
						<g:each in="${objectives}" var="objective">
							<p class="list-group-item-text">
								- ${objective.description}
							</p>
						</g:each>
					</li>
				</ul>
			</div>
			<g:form resource="${quest}" action="run" id="${quest.id}">
			<div class="col-sm-4">
				<ul class="list-group">
					<li class="list-group-item active">
						<h4 class="list-group-item-heading">
							<g:message code="quests.prepare.target.header" />
						</h4>
						<p class="list-group-item-text">
							<g:message code="quests.prepare.target.description" />
						</p>
					</li>
					<li class="list-group-item">
						<input type="radio" name="target-23" id="target-23">
						Deer
					</li>
					<li class="list-group-item">
						<input type="radio" name="target-27" id="target-27">
						Rabbit
					</li>
					
					<li class="list-group-item">
						<input type="radio" name="target-34" id="target-34">
						Wolf
					</li>
				</ul>
			</div>
			<div class="col-sm-4">
				<ul class="list-group">
					<li class="list-group-item active">
						<h4 class="list-group-item-heading">
							<g:message code="quests.prepare.members.header" />
						</h4>
						<p class="list-group-item-text">
							<g:message code="quests.prepare.members.description" />
						</p>
					</li>
					<div style="overflow: auto; max-height: 200px">
						<g:each in="${members}" var="member">
							<li class="list-group-item">
								<input type="checkbox" name="member-${member.id}" id="member-${member.id}">
								${member.firstName}
							</li>
						</g:each>
					</div>
					<g:link controller="quest" action="run" id="${quest.id}" class="list-group-item list-group-item-success disabled">
						Run Quest
					</g:link>
				</ul>
			</div>
			</g:form>
		</div>
	</body>
</html>
