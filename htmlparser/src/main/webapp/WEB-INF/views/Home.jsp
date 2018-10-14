<html>
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<body data-ng-app="HTMLParserApp">
	<h1 align="center" style="color: #33cc33;">HTML Parser</h1>
	<br>
	<div data-ng-controller="HTMLParserController">
		<form name="parserForm" novalidate>
			<div class="form-group">
				<label for="HTML-Text" style="margin-left: 20px;">HTML Text:</label>
				<textarea class="form-control" name="htmlText" rows="5" cols="50"
					data-ng-model="htmlText" style="max-width: 90%; margin-left: 20px;"
					maxlength="2000" required></textarea>
				<span style="color: red;"
					data-ng-show="parserForm.htmlText.$touched && parserForm.htmlText.$invalid">
					<span data-ng-show="parserForm.htmlText.$error.required">Please
						enter text.</span>
				</span>
				<div>{{htmlText.length}}</div>
			</div>
			<div style="margin-left: 400px;" class="form-group">
				<input class="btn btn-info" type="submit"
					data-ng-click="submitHTMLText()"> <input
					style="margin-left: 100px;" type="reset" class="btn btn-danger"
					data-ng-click="clearText()">
			</div>
		</form>
		<table class="table">
			<tr>
				<td>Attempt Number</td>
				<td>Date/Time</td>
				<td>Validation Result</td>
				<td>Time Taken in Processing</td>
			</tr>
			<tr data-ng-repeat="val in parserHistoryDetailsArr">
				<td>{{val.attemptNum}}</td>
				<td>{{val.dateTime}}</td>
				<td>{{val.status}}</td>
				<td>{{val.timeTaken}}</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/angular.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/HTMLParserApp.js"></script>
</html>
