<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'objectives.label')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="col-sm-2"></div>

		<div class="col-sm-8">
			<div class="page-header">
				<h2><g:message code="objectives.label" /> ${objective.id} <small>${objective.description}</small></h2>
			</div>

			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<g:hasErrors bean="${this.objective}">
				<div class="alert alert-danger" role="alert">
					<ul>
						<g:eachError bean="${this.objective}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
								<g:message error="${error}"/>
							</li>
						</g:eachError>
					</ul>
				</div>
			</g:hasErrors>

			<g:each in="${epochs}" var="epoch">
				<div class="panel panel-primary">
					<div class="panel-heading">${epoch.name}</div>
					<div class="panel-body">
						<table class="table">
							<thead>
								<tr>
									<th><g:message code="skills.name.label" /></th>
									<th>Primary</th>
									<th>Secondary</th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${epoch.skills}" var="skill">
									<tr>
										<td>${skill.name}</td>
										<div class="radio">
											<td><input type="radio" name="${skill.name}" value="primary"></td>
											<td><input type="radio" name="${skill.name}" value="secondary"></td>
										</div>
									</tr>
								</g:each>
							</tbody>
						</table>
					</div>
				</div>
			</g:each>
			
			<g:link controller="objective" action="update">
				<button type="button" class="btn btn-primary">
					<g:message code="default.button.update.label" />
				</button>
			</g:link>
		</div>
	</body>
</html>
