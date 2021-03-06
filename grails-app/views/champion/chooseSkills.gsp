<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'champions.label')}" />
        <title><g:message code="champions.chooseSkills.title" /></title>
    </head>
    <body>
		<div class="col-sm-4"></div>
		
		<div class="col-sm-4">
			<div class="page-header">
				<h3>
					<img data-src="holder.js/64x64" class="img-thumbnail">
					${champion.firstName}
				</h3>
			</div>
			
			<g:if test="${flash.message}">
				<div class="alert alert-info" role="alert">${flash.message}</div>
			</g:if>

			<g:hasErrors bean="${this.champion}">
				<div class="alert alert-danger" role="alert">
					<ul>
						<g:eachError bean="${this.champion}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
								<g:message error="${error}"/>
							</li>
						</g:eachError>
					</ul>
				</div>
			</g:hasErrors>
			
			<p><g:message code="champions.chooseSkills.firstLabel" /></p>
			
			<g:form resource="${this.champion}" action="saveSkills">
				<div class="panel panel-default">
					<div class="panel-body">
						<g:each in="${skills}" var="skill">
							<div class="checkbox">
								<label>
									<input type="checkbox" name="${skill.id}" id="${skill.id}">
									${skill.name}
								</label>
							</div>
						</g:each>
					</div>
				</div>
				
				<div class="col-sm-6">
					<button class="btn btn-default btn-block" type="submit" id="submit" disabled>
						<g:message code='default.button.cancel.label'/>
					</button>
				</div>
				<div class="col-sm-6">
					<button class="btn btn-primary btn-block" type="submit" id="submit">
						<g:message code='skills.button.save.label'/>
					</button>
				</div>
			</g:form>
		</div>
	</body>
</html>
