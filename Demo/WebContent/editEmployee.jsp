<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.mindtree.sdet.entitiy.Employee"%>
<!DOCTYPE html>

<html>

<head>
<title>Edit Employee Details</title>
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="css/jquery.bdt.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
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
								class="btn btn-success" id="editEmp">
						</div>
					</form>
				</div>

				<div class="well col-md-12">
					<div>
						<h3>
							<span style="color: green"> </span>

						</h3>
					</div>
					<%
						Employee emp = (Employee) request.getAttribute("employee");
					%>
					<form class="form-horizontal" action="UpdateDetails" method="post"
						onSubmit="return formValidation();">

						<div class="control-group field">
							<div class="row">

								<div class="col-md-3">
									<label class="control-label">Employee MID</label> <span
										class="astricStyle">*</span> :
								</div>
								<div class="col-sm-4 col-md-8">
									<input type="text" value="<%=emp.getMid()%>" disabled
										class="form-control input-md"> <input type="hidden"
										name="mid" id="mid" value="<%=emp.getMid()%>">
									<div class="text-danger" id="midError"></div>
								</div>
							</div>
						</div>

						<div class="control-group field">
							<div class="row">

								<div class="col-md-3">
									<label class="control-label">Employee Name </label> <span
										class="astricStyle">*</span> :
								</div>
								<div class="col-sm-4 col-md-8">
									<input type="text" id="ename" name="ename"
										value="<%=emp.getEname()%>" placeholder="Enter Employee Name"
										class="form-control input-md" required>
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
										value="<%=emp.getPractice()%>"
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
											if (emp.getCompetency().contains("T4")) {
										%>
										<option value="T4" selected>Competency-T4 (T4)</option>
										<%
											} else {
										%><option value="T4">Competency-T4 (T4)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C1")) {
										%>
										<option value="C1" selected>Competency-1 (C1)</option>
										<%
											} else {
										%><option value="C1">Competency-1 (C1)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C2")) {
										%><option value="C2" selected>Competency-2 (C2)</option>
										<%
											} else {
										%><option value="C2">Competency-2 (C2)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C3")) {
										%><option value="C3" selected>Competency-3 (C3)</option>
										<%
											} else {
										%><option value="C3">Competency-3 (C3)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C4")) {
										%><option value="C4" selected>Competency-4 (C4)</option>
										<%
											} else {
										%><option value="C4">Competency-4 (C4)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C5")) {
										%><option value="C5" selected>Competency-5 (C5)</option>
										<%
											} else {
										%><option value="C5">Competency-5 (C5)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C6")) {
										%><option value="C6" selected>Competency-6 (C6)</option>
										<%
											} else {
										%><option value="C6">Competency-6 (C6)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C7")) {
										%>
										<option value="C7" selected>Competency-7 (C7)</option>
										<%
											} else {
										%><option value="C7">Competency-7 (C7)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C8")) {
										%><option value="C8" selected>Competency-8 (C8)</option>
										<%
											} else {
										%><option value="C8">Competency-8 (C8)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C9")) {
										%><option value="C9" selected>Competency-9 (C9)</option>
										<%
											} else {
										%><option value="C9">Competency-9 (C9)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C10")) {
										%><option value="C10" selected>Competency-10 (C10)</option>
										<%
											} else {
										%><option value="C10">Competency-10 (C10)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C11")) {
										%><option value="C11" selected>Competency-11 (C11)</option>
										<%
											} else {
										%><option value="C11">Competency-11 (C11)</option>
										<%
											}
										%>
										<%
											if (emp.getCompetency().contains("C12")) {
										%><option value="C12" selected>Competency-12 (C12)</option>
										<%
											} else {
										%><option value="C12">Competency-12 (C12)</option>
										<%
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
									<input type="text" id="vertical" name="vertical"
										value="<%=emp.getVertical()%>"
										placeholder="Enter Vertical Name "
										class="form-control input-md" required>
									<div class="text-danger" id="verError"></div>
								</div>
							</div>
						</div>
						<div class="control-group field">
							<div class="row">
								<div class="col-md-6">
									<input type="submit" class="btn btn-success " value="  Submit"
										id="submit">
								</div>

								<div class="col-md-6">
									<input type="reset" class="btn btn-danger" value="Cancel"
										id="reset"
										onClick="javascript:location.href='ViewAllEmployees';">
								</div>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="js/fieldValidations.js"></script>
</html>
