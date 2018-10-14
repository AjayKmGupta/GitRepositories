var app = angular.module('HTMLParserApp', []);

app.controller('HTMLParserController', function($scope, $http) {

	$scope.submitHTMLText = submitHTMLText;
	$scope.clearText = clearText;

	function submitHTMLText() {
		if ($scope.htmlText != undefined) {
			if ($scope.parserHistoryDetailsArr == undefined) {
				var url = "parser/firstparse";
			} else {
				var url = "parser/parse";
			}
			$http({
				method : 'POST',
				url : url,
				data : $scope.htmlText,
				headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				}
			}).then(function(response) {
				console.log(response);
				$scope.parserHistoryDetailsArr = response.data;
			});
		}
	}

	function clearText() {
		$scope.htmlText = "";
	}

});