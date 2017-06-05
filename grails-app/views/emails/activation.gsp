<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="Pedro Vaz">
		
		<title>Welcome to Bromak!</title>
	</head>
	<body>
		<span style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 1.42857143; color: #333;">
			<table>
				<tr>
					<td style="padding-bottom: 20px">
						<p>Hi ${username}!</p>
						<p>Welcome to Bromak! Thanks so much for joining our community.</p>
					</td>
				</tr>
				<tr>
					<td style="padding-bottom: 20px">
						<p>Bromak is a multiplayer browser game, set in a fantasy world that spans from the stone age to the medieval age.</p>
						<p>This world has many playable races, is rich in wild life and natural resources, and is waiting to be explored!</p>
						<p>In Bromak, every player action, no matter how small, matters to someone.<p>
					</td>
				</tr>
				<tr>
					<td style="padding-bottom: 20px">
						<p>Please click the link below to activate your player account:</p>
						<g:link controller="user" action="activate" base="http://localhost:8080" params="[username: username, hash: encodedEmail]">
							Activate your account!
						</g:link>
					</td>
				</tr>
				<tr>
					<td style="padding-bottom: 20px">
						<p>Your first action will be to choose your first Champion, by giving it a Name, Gender and Race.</p>
					</td>
				</tr>
				<tr>
					<td style="padding-bottom: 20px">
						<p>If you have any questions, we (and the community) will be more than happy to help you.</p>
					</td>
				</tr>
				<tr>
					<td style="padding-bottom: 20px">
						<p>We really hope you enjoy your adventure,</p>
						<p>The Bromak Team</p>
					</td>
				</tr>
			</table>
		</span>
	</body>
</html>
