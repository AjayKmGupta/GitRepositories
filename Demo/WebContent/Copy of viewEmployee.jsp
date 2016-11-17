<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.List"%>
<%@page import="com.mindtree.sdet.entitiy.Employee"%>
<!DOCTYPE html>
<html>

<head>
<title>View Employee Details</title>

<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="css/jquery.bdt.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/fieldValidations.js"></script>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/jquery.bdt.min.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('#empTable').bdt({
			showSearchForm : 0,
			showEntriesPerPageField : 0
		});
	});
</script>

</head>
<body background="images/background.jpg">

	<div class="container-fluid">
		<div class="well search">
			<form action="SearchEmployee" method="get">
				<div class="control-group">
					<div class="row">

						<div class="col-sm-2 col-md-2">
							<label class="control-label searchBox" for="username">Search
								by MID : </label>
						</div>
						<div class="col-sm-4 col-md-5">
							<input type="text" id="username" name="search_id"
								placeholder="Enter Employee MID" class=" form-control input-md">
						</div>
						<div class="col-md-3">
							<input type="submit" value="Search Employee"
								class="btn btn-success" id="submit">
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="well col-md-2 left">
			<div class="left-nav">
				<a href="register.jsp"><button class="btn btn-link">Register</button></a>
			</div>
			<div class="left-nav">
				<hr class="myhrline"></hr>
			</div>
			<div class="left-nav">
				<a href="ViewAllEmployees">
					<button class="btn btn-link">View All</button>
				</a>
			</div>
		</div>


		<div class="well col-md-8 right">
			<%
			if(request.getAttribute("message").equals("success")) {
				List<Employee> elist = (List<Employee>) request
							.getAttribute("empList");
					if (elist.size() == 0) {
			%>
			<div class="text-warning">No Employee records to show</div>
			<%
				} else {
			%>
			<div class="text-success">
				Total of
				<%=elist.size()%>
				records found.
			</div>

			<!-- <div class="well">  -->
			<table class="content table table-striped table-bordered table-hover" id="empTable">
				<thead>
					<tr>
						<th>MID</th>
						<th>Employee Name</th>
						<th>Competency</th>
						<th>Practice</th>
						<th>Vertical</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<%
					for (Employee e : elist) {
				%>
				<tbody>
					<tr>
						<td><%=e.getMid()%></td>
						<td><%=e.getEname()%></td>
						<td><%=e.getCompetency()%></td>
						<td><%=e.getPractice()%></td>
						<td><%=e.getVertical()%></td>
						<td><a href='EditServlet?mid=<%=e.getMid()%>'><b>Edit</b></a></td>
						<td><a href='DeleteEmployee?id=<%=e.getMid()%>'><b>Delete</b></a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<%
				}
			} else {
				
				%>
				<div class="text-warning"><%=request.getAttribute("message")%></div>
				<%
			}
			%>
			<!-- 	</div> -->
		</div>
	</div>
</body>

</html>