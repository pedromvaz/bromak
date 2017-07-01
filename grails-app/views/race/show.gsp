<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'races.label')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="page-header">
			<h2>
				${race.name}
				<small><g:message code="races.label" /></small>
			</h2>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="panel panel-primary">
					<div class="panel-heading">General</div>
					<div class="panel-body">
						<table class="table">
							<tbody>
								<tr>
									<td><b><g:message code='races.name.label'/></b></td>
									<td>${race.name}</td>
								</tr>
								<tr>
									<td><b><g:message code='races.description.label'/></b></td>
									<td>${race.description}</td>
								</tr>
								<tr>
									<td><b><g:message code='races.startingPopulation.label'/></b></td>
									<td>${race.startingPopulation}</td>
								</tr>
								<tr>
									<td><b><g:message code='races.cognition.label'/></b></td>
									<td>
										<g:if test="${race.intelligent}">
											<span class="label label-success"><g:message code="races.intelligent.label" /></span>
										</g:if>
										<g:else>
											<span class="label label-danger"><g:message code="races.primitive.label" /></span>
										</g:else>
									</td>
								</tr>
								<tr>
									<td><b><g:message code='races.status.label'/></b></td>
									<td>
										<g:if test="${race.enabled}">
											<span class="label label-success"><g:message code="races.enabled.label" /></span>
										</g:if>
										<g:else>
											<span class="label label-danger"><g:message code="races.disabled.label" /></span>
										</g:else>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<div class="col-sm-6">
				<div class="panel panel-primary">
					<div class="panel-heading">Skill Tree</div>
					<div class="panel-body">
						<table class="table">
							<tbody>
								<g:each in="${race.skillTree.skills}" var="skill">
									<tr>
										<td>${skill.name}</td>
									</tr>
								</g:each>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
    </body>
</html>
