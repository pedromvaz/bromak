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
				<div class="hex-row">
					<div class="hex"></div>
				</div>
			</div>
		</div>
	</body>
</html>
