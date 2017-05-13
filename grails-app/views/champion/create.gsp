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
			
			<form action="/champion/save" method="POST" id="createChampionForm">
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
			</form>

			<g:javascript>
				var RandExp = require('randexp');

				function generateRandomName() {
					document.getElementById('firstName').value =
						createRandomWord(4);
						//new RandExp(/[aeiouy][^aeiouy][aeiouy]/).gen();
				}

				function createRandomWord(length) {
					var consonants = 'bcdfghjklmnpqrstvwxyz',
						vowels = 'aeiou',
						rand = function(limit) {
							return Math.floor(Math.random()*limit);
						},
						i, word='', length = parseInt(length,10),
						consonants = consonants.split(''),
						vowels = vowels.split('');
					for (i=0;i<length/2;i++) {
						var randConsonant = consonants[rand(consonants.length)],
							randVowel = vowels[rand(vowels.length)];
						word += (i===0) ? randConsonant.toUpperCase() : randConsonant;
						word += i*2<length-1 ? randVowel : '';
					}
					return word;
				}
			</g:javascript>

		</div>
	</body>
</html>
