<html>
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<body data-ng-app="HTMLParserApp">
	<h1>HTML Parser</h1>
	<br>
	<div data-ng-controller="HTMLParserController">
		<div class="form-group">
			<label for="HTML-Text">HTML Text:</label>
		</div>
		<textarea class="form-control" rows="5" cols="50"
			data-ng-model="htmlText" maxlength="2000"></textarea>
		<div>{{htmlText.length}}</div>
		<input class="form-group btn btn-info" type="submit"
			data-ng-click="submitHTMLText()">
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
