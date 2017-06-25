<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'champion.label', default: 'Champion')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
		<div class="col-sm-4"></div>
		
		<div class="col-sm-4">
			<div class="page-header">
				<h2><g:message code="default.create.label" args="[entityName]" /></h2>
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
			
			<g:form action="save">
				<div class="form-group">
					<label for="firstName" class="sr-only"><g:message code='creatures.firstName.label'/></label>
					<div class="input-group">
						<input type="text" name="firstName" id="firstName" class="form-control" placeholder="${message(code: 'creatures.firstName.label')}" required readonly>

						<span class="input-group-btn">
							<button class="btn btn-primary" type="button" id="setName" onclick="generateRandomName()">
								<g:message code='creatures.name.generate'/>
							</button>
						</span>

						<!--
						<label for="lastName" class="sr-only"><g:message code='creatures.lastName.label'/></label>
						<input type="text" name="lastName" id="lastName" class="form-control" placeholder="<g:message code='creatures.lastName.label'/>">
						-->
					</div>
				</div>

				<div class="form-group">
					<label for="raceId" class="sr-only"><g:message code='creatures.race.label'/></label>
					<g:select class="form-control" name="race.id" from="${com.bromakgame.creatures.Race.getPlayableRaces()}" optionKey="id" optionValue="name" />
				</div>

				<div class="form-group">
					<label class="radio-inline">
						<input type="radio" name="gender" value="${com.bromakgame.creatures.Champion.MALE}">
						<g:message code="creatures.gender.male.label" />
					</label>
					<label class="radio-inline">
						<input type="radio" name="gender" value="${com.bromakgame.creatures.Champion.FEMALE}">
						<g:message code="creatures.gender.female.label" />
					</label>
				</div>

				<button class="btn btn-primary btn-block" type="submit" id="submit">
					<g:message code='default.button.create.label'/>
				</button>
			</g:form>

			<g:javascript>
				function generateRandomName() {
					document.getElementById('firstName').value = createRandomWord();
				}
			</g:javascript>

		</div>
	</body>
</html>
