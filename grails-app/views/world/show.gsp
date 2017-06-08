<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'world.label', default: 'World')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<script src="/assets/jquery-ui-1.12.1/jquery.js" ></script>
		<script src="/assets/jquery-ui-1.12.1/jquery-ui.min.js" ></script>
		<script>
			$(document).ready(function() {
				$("#hexGrid").draggable();
			});
		</script>
	</head>
	<body>
		<div class="page-header">
			<h2>${this.world.name}</h2>
			<h4><g:message code="worlds.show.header" /></h4>
		</div>
		
		<div id="hexViewport">
			<div id="hexGrid">
				<g:each in="${areas}" var="area">
					<div class="hex ${area.getTopographyAndBiomeColor()}" style="left: ${area.x+1}px; top: ${area.y+1}px"></div>
					<div class="hexIcon ${area.getTopographyIcon()}" style="left: ${area.x+33}px; top: ${area.y+13}px"></div>
				</g:each>
			</div>
		</div>
	</body>
</html>
