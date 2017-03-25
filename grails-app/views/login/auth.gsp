<html>
<head>
	<meta name="layout" content="${gspLayout ?: 'main'}"/>
	<title><g:message code='springSecurity.login.title'/></title>
	<style type="text/css" media="screen">
	#login {
		margin: 15px 0px;
		padding: 0px;
		text-align: center;
	}

	#login .inner {
		width: 340px;
		padding-bottom: 6px;
		margin: 60px auto;
		text-align: left;
		border: 1px solid #aab;
		background-color: #f0f0fa;
		-moz-box-shadow: 2px 2px 2px #eee;
		-webkit-box-shadow: 2px 2px 2px #eee;
		-khtml-box-shadow: 2px 2px 2px #eee;
		box-shadow: 2px 2px 2px #eee;
	}

	#login .inner .fheader {
		padding: 18px 26px 14px 26px;
		background-color: #f7f7ff;
		margin: 0px 0 14px 0;
		color: #2e3741;
		font-size: 18px;
		font-weight: bold;
	}

	#login .inner .cssform p {
		clear: left;
		margin: 0;
		padding: 4px 0 3px 0;
		padding-left: 20px;
		margin-bottom: 10px;
		height: 1%;
	}
	
	#login .inner .text_ {
		width: 300px;
	}

	#login #submit {
		width:100px;
	}

	#login .inner .login_message {
		padding: 6px 25px 20px 25px;
		color: #c33;
	}
	</style>
</head>

<body>
<div id="login">
	<div class="inner">
		<div class="fheader"><g:message code='springSecurity.login.header'/></div>

		<g:if test='${flash.message}'>
			<div class="login_message">${flash.message}</div>
		</g:if>

		<form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="cssform" autocomplete="off">
			<p>
				<input type="text" class="text_" name="${usernameParameter ?: 'username'}" id="username" placeholder="<g:message code='springSecurity.login.username.label'/>"/>
			</p>

			<p>
				<input type="password" class="text_" name="${passwordParameter ?: 'password'}" id="password" placeholder="<g:message code='springSecurity.login.password.label'/>"/>
			</p>
			<!--
			<p>
				<label>
					<input type="checkbox" name="${rememberMeParameter ?: 'remember-me'}" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
					<g:message code='springSecurity.login.remember.me.label'/>
				</label>
			</p>
			-->
			<p>
				<input type="submit" id="submit" value="${message(code: 'springSecurity.login.button')}"/>
				<!-- TODO: These labels must be put in the messages.properties file -->
				<g:link controller="user" action="create">Register</g:link> | Lost your password?
			</p>
		</form>
	</div>
</div>
<script>
(function() {
	document.forms['loginForm'].elements['${usernameParameter ?: 'username'}'].focus();
})();
</script>
</body>
</html>
