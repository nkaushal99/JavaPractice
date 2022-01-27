<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.GregorianCalendar, java.util.Calendar" %>
    
<%
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
%>

		<p>&copy; <%= year %> Nikhil Kaushal</p>
	</body>
</html>