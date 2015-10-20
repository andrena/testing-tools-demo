var clothingTipsApp = angular.module('clothingTipsApp', ['geolocation', 'ngResource']);

clothingTipsApp.config(['$resourceProvider', function($resourceProvider) {
	$resourceProvider.defaults.stripTrailingSlashes = false;
}]);