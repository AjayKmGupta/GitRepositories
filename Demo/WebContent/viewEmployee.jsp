<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.List"%>
<%@page import="com.mindtree.sdet.entitiy.Employee"%>
<%@page import="com.mindtree.sdet.entitiy.Competency"%>
<%@page import="com.mindtree.sdet.entitiy.Vertical"%>
<!DOCTYPE html>
<html>

<head>
<title>View Employee Details</title>

<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="css/jquery.bdt.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/fieldValidations.js"></script>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/jquery.bdt.min.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('#empTable').bdt({
			showSearchForm : 0,
			showEntriesPerPageField : 0,
			"order" : [ [ 1, "desc" ] ]
		});
	});
</script>

</head>
<body background="images/background.jpg">

	<div class="container-fluid">
		<div class="well search">

			<div class="control-group">
				<div class="row">

					<div class=" col-sm-2 col-md-2">
						<a href="RegisterEmployee"><button
								class="col-md-12 btn btn-info menubtn">Register</button></a>
					</div>
					<div class="col-sm-2 col-md-2">
						<a href="ViewAllEmployees">
							<button class="col-md-12 btn btn-info menubtn">View All</button>
						</a>
					</div>
					<form action="SearchEmployee" method="get">
						<div class="col-sm-2 col-md-2">
							<label class="control-label searchBox" for="username">Search
								by MID : </label>
						</div>
						<div class="col-sm-4 col-md-3">
							<input type="text" id="username" name="search_id"
								placeholder="Enter Employee MID" class=" form-control input-md">
						</div>
						<div class="col-md-2">
							<input type="submit" value="Search Employee"
								class="btn btn-success" id="searchEmp">
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="well col-md-12">

			<div class="well col-md-11">
				<form action="ViewAllEmployees" method="get">
					<div class="col-sm-2 col-md-2">
						<label class="control-label searchBox" for="username">Filter
						</label>
					</div>
					<div class="col-sm-4 col-md-3">
						<select class="form-control input-md" name="competency"
							id="competency">
							<option value="Default">Select Competency</option>
							<%
								List<Competency> clist1 = (List<Competency>) request.getAttribute("competency");
								if (clist1 != null) {
									for (Competency c : clist1) {
							%>
							<option value=<%=c.getCode()%>>
								<%=c.getComptencyName()%> (<%=c.getCode()%>)
							</option>
							<%
								}
								}
							%>
						</select>
						<div class="text-danger" id="competencyError"></div>
					</div>

					<div class="col-sm-4 col-md-3">
						<select class="form-control input-md" name="vertical"
							id="competency">
							<option value="Default">Select Vertical</option>
							<%
								List<Vertical> verticalList = (List<Vertical>) request.getAttribute("vertical");
								if (verticalList != null) {
									for (Vertical v : verticalList) {
							%>
							<option value=<%=v.getCode()%>>
								<%=v.getVertName()%></option>
							<%
								}
								}
							%>
						</select>
						<div class="text-danger" id="competencyError"></div>
					</div>


					<div class="col-md-2">
						<input type="submit" value="Filter" class="btn btn-success"
							id="filter">
					</div>
				</form>
			</div>
			<%
				if (request.getAttribute("message").equals("success")) {
					List<Employee> elist = (List<Employee>) request.getAttribute("empList");
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
			<table class="content table table-striped table-bordered table-hover"
				id="empTable">
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
						<td><a href='EditServlet?mid=<%=e.getMid()%>'><button
									type="button" class="btn btn-info btn-sm">Edit</button></a></td>
						<td>
							<button type="button" class="btn btn-warning btn-sm"
								data-toggle="modal" data-target="#myModal">Delete</button>
						</td>
					</tr>
					<div class="modal" id="myModal">
						<div class="modal-dialog modal-head">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Delete Employee</h4>
								</div>
								<div class="modal-body">
									<p>
										Do you want to Delete Employee with ID :
										<%=e.getMid()%>.?
									</p>
								</div>
								<div class="modal-footer">
									<a id="empDel" href='DeleteEmployee?id=<%=e.getMid()%>'><button
											type="button" class="btn btn-warning">Remove</button></a>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
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
		</div>
	</div>
</body>

</html>