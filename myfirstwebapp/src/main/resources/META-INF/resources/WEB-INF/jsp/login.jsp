<html>
	<head>
		<title> Login </title>
	</head>
	<body>
		<!-- expression language 
		${mname} here mname is sent from controller
		 is used for using 
		value from model that we sent from controller 
		<p>By ${mname}</p> -->
		
		<!-- if username password wrong -->
		<div class="container">
			<h1>Login</h1>
			<pre>${errorMessage}</pre>
			<form method="post"> 
				Name: <input type="text" name="name">
				Password: <input type="password" name="password">
				<input type="submit">
			</form>
		</div>
	</body>
</html>