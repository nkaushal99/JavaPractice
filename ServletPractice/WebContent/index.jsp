<%@ include file="header.html" %>
		<h1>My Form</h1>
		
		<form action="RegisterServlet" method="post">
			<table>
				<tr>
					<td>Enter your name: </td>
					<td><input type="text" name="user_name" placeholder="Enter here..."></td>
				</tr>
				<tr>
					<td>Enter your password: </td>
					<td><input type="password" name="user_password" placeholder="Enter here..."></td>
				</tr>
				<tr>
					<td>Enter your email: </td>
					<td><input type="email" name="user_email" placeholder="Enter here..."></td>
				</tr>
				<tr>
					<td>Select gender</td>
					<td>
						<input type="radio" name="user_gender" value="Male">Male &nbsp; &nbsp;
						<input type="radio" name="user_gender" value="Female">Female
					</td>
				</tr>
				<tr>
					<td>Select your course</td>
					<td>
						<select name="user_course">
							<option value="Java">Java</option>
							<option value="Php">Php</option>
							<option value="Python">Python</option>
							<option value="Android">Android</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<input type="checkbox" value="checked" name="condition">
					</td>
					<td>Agree to the terms and conditions</td>
				</tr>
				<tr>
					<td>
					
					</td>
					<td>
						<button type="submit">Register</button>
						<button type="reset">Reset</button>
					</td>
				</tr>
			</table>
		</form>
<%@ include file="footer.jsp" %>