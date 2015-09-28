<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h1>${error}</h1>
	<div>
		<h2>Login </h2>
		<sf:form method="POST" action="/SpringWeb/onLogin">
			<fieldset>
				<table cellspacing="0">
					<tr>
						<th><label for="userId">User Id:</label></th>
						<td><input type="text" name="userId" id="userId" size="10" maxlength="10"/><%-- <sf:input path="userId" size="15" id="userId" /> --%></td>
					</tr>
					<tr>
						<th><label for="password">Password:</label></th>
						<td><input type="password" name="password" id="password" size="10" maxlength="10"/><%-- <sf:password path="password" size="10"
								showPassword="true" id="password" /> --%>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Submit" /></td>
					</tr>
				</table>
			</fieldset>
		</sf:form>
	</div>
	<h3><a href="/SpringWeb/register">Sign Up</a></h3>
</body>
</html>