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
		
			<div class="list-group">
				<g:each in="${championList}" var="champion">
					<a href="#" class="list-group-item">
						<table>
							<tbody>
								<tr>
									<td>
										<g:if test="${champion.gender == 'm'}">
											<img class="img-thumbnail-50pc" src="http://www.iconninja.com/files/40/368/11/man-user-male-avatar-young-person-icon.svg">
										</g:if>
										<g:else>
											<img class="img-thumbnail-50pc" src="http://www.iconninja.com/files/311/30/378/female-young-woman-user-avatar-person-icon.svg">
										</g:else>
									</td>
									<td>
										<h4 class="list-group-item-heading">${champion.getFullName()}</h4>
										<p class="list-group-item-text">${champion.getGenderDesc() + " " + champion.race.name}</p>
									</td>
								</tr>
							</tbody>
						</table>
					</a>
				</g:each>
			</div>
			
			<g:if test="${championCount > 10}">
				<div class="pagination">
					<g:paginate total="${championCount ?: 0}" />
				</div>
			</g:if>
			
		</g:else>
	</body>
</html>