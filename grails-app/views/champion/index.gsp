<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'champion.label', default: 'Champion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<g:if test="${!hasChampionsAlive}">
			
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
		
			<g:if test="${flash.message}">
				<div class="alert alert-info" role="alert">${flash.message}</div>
			</g:if>
			
			<g:each in="${championList}" var="champion">
				<div class="col-sm-3">
					<ul class="list-group">
						<li class="list-group-item active">
							<h4 class="list-group-item-heading">${champion.getFullName()}</h4>
							<p class="list-group-item-text">${champion.getGenderDesc() + " " + champion.race.name}</p>
						</li>
						<li class="list-group-item">
							<img data-src="holder.js/64x64" class="img-thumbnail">
						</li>
						<li class="list-group-item">
							<b>Communities:</b>
						</li>
						<g:each in="${champion.groups}" var="community">
							<li class="list-group-item">
								<g:link controller="community" action="show" id="${community.id}">
									${community.toString()}
								</g:link>
								<span class="badge">
									${community.size()}
								</span>
							</li>
						</g:each>
					</ul>
				</div>
			</g:each>
			
			<g:if test="${championCount > 10}">
				<div class="pagination">
					<g:paginate total="${championCount ?: 0}" />
				</div>
			</g:if>
			
		</g:else>
	</body>
</html>