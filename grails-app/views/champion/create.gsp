<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'champion.label', default: 'Champion')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>

		<g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
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
		
		<div class="row">
			<div class="col-sm-4">
			</div>
			<div class="col-sm-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><g:message code="default.create.label" args="[entityName]" /></h3>
					</div>
					<div class="panel-body">
						<form action="/champion/save" method="POST" id="createChampionForm" class="form-signin" >
							<label for="firstName" class="sr-only"><g:message code='creatures.firstName.label'/></label>
							<input type="text" name="firstName" id="firstName" class="form-control" placeholder="<g:message code='creatures.firstName.label'/>" required autofocus>
							
							<label for="lastName" class="sr-only"><g:message code='creatures.lastName.label'/></label>
							<input type="text" name="lastName" id="lastName" class="form-control" placeholder="<g:message code='creatures.lastName.label'/>">
							
							<label for="title" class="sr-only"><g:message code='creatures.title.label'/></label>
							<input type="text" name="title" id="title" class="form-control" placeholder="<g:message code='creatures.title.label'/>">
							
							<div class="form-group">
								<label class="radio-inline">
									<input type="radio" name="gender" value="m">
									<g:message code="creatures.gender.male.label" />
								</label>
								<label class="radio-inline">
									<input type="radio" name="gender" value="f">
									<g:message code="creatures.gender.female.label" />
								</label>
							</div>
							
							<div class="form-group">
								<label for="raceId" class="sr-only"><g:message code='creatures.race.label'/></label>
								<g:select class="form-control" name="race.id" from="${com.bromakgame.Race.findAllByIntelligentAndEnabled(true, true)}" optionKey="id" optionValue="name" />
							</div>
							
							<button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">
								<g:message code='default.button.create.label'/>
							</button>
						</form>
					</div>
				</div>
			</div>
        </div>
	</body>
</html>
