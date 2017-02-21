<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add/Edit Person</title>
</head>
<body>
	<h1>Add/Edit Person</h1>
	<table border="5">
		<TR>
			<th>Person ID</th>
			<th>Person Firstname</th>
			<th>Person Lastname</th>
			<th>Person Age</th>
			<th>
				<div>Actions</div>
			</th>
		</TR>
			<tr>
				<TD><input type="text" value="${person.id}"></TD>
				<TD><input type="text" value="${person.firstname}"></TD>
				<TD><input type="text" value="${person.lastname}"></TD>
				<TD><input type="text" value="${person.age}"></TD>
				<td>
					<div>
						<a href="edit?id=${person.id}"><img alt="add"
							src="pages/images/add_icon_15.png" border="0"></a>
					</div>
				</td>

			</tr>
	</table>

</body>
</html>