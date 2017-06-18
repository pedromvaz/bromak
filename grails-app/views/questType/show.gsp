<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'questTypes.label')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="col-sm-2"></div>

		<div class="col-sm-8">
			<div class="page-header">
				<h2>${questType.name} <small><g:message code="questTypes.label" /></small></h2>
			</div>

			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<g:hasErrors bean="${this.questType}">
				<div class="alert alert-danger" role="alert">
					<ul>
						<g:eachError bean="${this.questType}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
								<g:message error="${error}"/>
							</li>
						</g:eachError>
					</ul>
				</div>
			</g:hasErrors>

			<div class="panel panel-primary">
				<div class="panel-heading">General</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-2"><b><g:message code='questTypes.name.label'/></b></div>
						<div class="col-sm-7">${questType.name}</div>
						
						<div class="col-sm-2"><b><g:message code='questTypes.groupCap.label'/></b></div>
						<div class="col-sm-1">${questType.groupCap}</div>
					</div>
					<div style="margin-top: 15px"></div>
					<div class="row">
						<div class="col-sm-2"><b><g:message code='questTypes.description.label'/></b></div>
						<div class="col-sm-10">${questType.description}</div>
					</div>
				</div>
			</div>
			
			<div class="panel panel-primary">
				<div class="panel-heading">
					<g:message code="questTypes.objectives.label" />
				</div>
				<div class="panel-body">
					<table class="table">
						<thead>
							<tr>
								<th><g:message code="objectives.description.label" /></th>
								<th><g:message code="objectives.primarySkills.label" /></th>
								<th><g:message code="objectives.secondarySkills.label" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${questType.objectives}" var="objective">
								<tr>
									<td>
										<g:link controller="objective" action="show" id="${objective.id}">
											${objective.description}
										</g:link>
									</td>
									<td></td>
									<td></td>
									<td>
										<g:link controller="objective" action="edit" id="${objective.id}">
											<span class="glyphicon glyphicon-edit"></span> Edit
										</g:link>
									</td>
								</tr>
							</g:each>
						</tbody>
					</table>
					
					<g:link controller="objective" action="create" params="[questTypeId:questType.id]">
						<button type="button" class="btn btn-primary">
							<g:message code="objectives.new.label" />
						</button>
					</g:link>
				</div>
			</div>
		</div>
	</body>
</html>
