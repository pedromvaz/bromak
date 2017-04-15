<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'champion.label', default: 'Champion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:if test="${flash.message}">
			<div class="alert alert-info" role="alert">${flash.message}</div>
		</g:if>
		
		<g:if test="${championCount == 0}">
			
			<!-- Carousel -->
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img class="shrink-img" src="http://img11.deviantart.net/db50/i/2013/265/4/1/standard_rpg_races__male_by_chief_orc-d6nffjp.jpg" alt="Male Champions">
					</div>
					<div class="item">
						<img class="shrink-img" src="http://img10.deviantart.net/88e5/i/2013/269/1/7/standard_rpg_races__female_by_chief_orc-d6nxjsi.jpg" alt="Female Champions">
					</div>
				</div>
				<!--
				<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
				-->
			</div>
			<!-- /.carousel -->
			
			<p/>
			<p class="text-center">
				<g:link action="create">
					<button type="button" class="btn btn-lg btn-primary">
						<g:message code="default.new.label" args="[entityName]" />
					</button>
				</g:link>
			</p>
			
		</g:if>
		<g:else>
		
			<!--<div class="col-sm-6">-->
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><g:message code="default.list.label" args="[entityName]" /></h3>
					</div>
					<div class="panel-body">
						<table class="table">
							<thead>
								<tr>
									<th><g:message code="creatures.firstName.label" /></th>
									<th><g:message code="creatures.father.label" /></th>
									<th><g:message code="creatures.mother.label" /></th>
									<th><g:message code="creatures.gender.label" /></th>
									<th><g:message code="creatures.race.label" /></th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${championList}" var="champion">
									<tr>
										<td>${champion.getFullName()}</td>
										<td>${champion.father?.getFullName()}</td>
										<td>${champion.mother?.getFullName()}</td>
										<td>
											<g:if test="${champion.gender == 'm'}">
												<g:message code="creatures.gender.male.label" />
											</g:if>
											<g:else>
												<g:message code="creatures.gender.female.label" />
											</g:else>
										</td>
										<td>${champion.race.name}</td>
									</tr>
								</g:each>
							</tbody>
						</table>

						<g:if test="${championCount > 10}">
							<div class="pagination">
								<g:paginate total="${championCount ?: 0}" />
							</div>
						</g:if>

						<g:link action="create">
							<button type="button" class="btn btn-primary">
								<g:message code="default.new.label" args="[entityName]" />
							</button>
						</g:link>
					</div>
				</div>
			<!--</div>-->
			
		</g:else>
	</body>
</html>