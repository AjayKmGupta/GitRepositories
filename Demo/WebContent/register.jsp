<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.mindtree.sdet.entitiy.Competency"%>
<%@page import="com.mindtree.sdet.entitiy.Vertical"%>
<!DOCTYPE html>

<html>

<head>
<title>Register New Employee</title>
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="css/jquery.bdt.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body background="images/background.jpg">
	<c:import url="RegisterEmployee" />

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


		<div class="well col-md-12" id="main">
			<%
				if (request.getAttribute("status") == "Success") {
			%>
			<div class="text-success">
				<h3>
					<%=request.getAttribute("message")%>

				</h3>
			</div>
			<%
				} else if (request.getAttribute("status") == "Error") {
			%>
			<div class="text-warning">
				<h3>
					<%=request.getAttribute("message")%>

				</h3>
			</div>
			<%
				}
			%>
			<form class="form-horizontal" action="RegisterEmployee" method="post"
				onSubmit="return formValidation();">
				<div class="control-group field">
					<div class="row">

						<div class="col-md-3">
							<label class="control-label">Employee MID </label> <span
								class="astricStyle">*</span> :
						</div>
						<div class="col-sm-4 col-md-8">
							<input type="text" id="mid" name="mid"
								placeholder="Enter Employee MID" class="form-control input-md"
								required>
							<div class="text-danger" id="midError"></div>
						</div>
					</div>
				</div>

				<div class="control-group field">
					<div class="row">

						<div class="col-md-3">
							<label class="control-label">Employee Name</label> <span
								class="astricStyle">*</span> :
						</div>
						<div class="col-sm-4 col-md-8">
							<input type="text" id="ename" name="ename"
								placeholder="Enter Employee Name" class="form-control input-md"
								required>
							<div class="text-danger" id="nameError"></div>
						</div>
					</div>
				</div>

				<div class="control-group field">
					<div class="row">
						<div class="col-md-3">
							<label class="control-label">Sub Practice </label> <span
								class="astricStyle">*</span> :
						</div>
						<div class="col-sm-4 col-md-8">
							<input type="text" id="practice" name="practice"
								placeholder="Enter Sub Practice Name "
								class="form-control input-md" required>
							<div class="text-danger" id="pracError"></div>
						</div>
					</div>
				</div>

				<div class="control-group field">
					<div class="row">
						<div class="col-md-3">
							<label class="control-label">Competency </label> <span
								class="astricStyle">*</span> :
						</div>
						<div class="col-sm-4 col-md-8">
							<select class="form-control input-md" name="competency"
								id="competency">
								<option value="Default">Select Competency</option>
								<%
									List<Competency> clist = (List<Competency>) request.getAttribute("competency");
									if (clist != null) {
										for (Competency c : clist) {
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

					</div>
				</div>

				<div class="control-group field">
					<div class="row">
						<div class="col-md-3">
							<label class="control-label">Vertical </label> <span
								class="astricStyle">*</span> :
						</div>
						<div class="col-sm-4 col-md-8">
							<select class="form-control input-md" name="vertical"
								id="vertical">
								<option value="Default">Select Vertical</option>
								<%
									List<Vertical> vlist = (List<Vertical>) request.getAttribute("vertical1");
									if (vlist != null) {
										for (Vertical v : vlist) {
								%>
								<option value=<%=v.getCode()%>>
									<%=v.getVertName()%></option>
								<%
									}
									}
								%>
							</select>
							<div class="text-danger" id="verError"></div>
						</div>
					</div>
				</div>
				<div class="control-group field">
					<div class="row">
						<div class="col-md-6">
							<input type="submit" class="btn btn-success" value="Submit"
								id="submit">
						</div>
						<div class="col-md-6">
							<input type="reset" class="btn btn-danger" value="Reset All"
								id="reset" onclick="resetFields();">
						</div>
					</div>
				</div>

			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/fieldValidations.js"></script>
</html>