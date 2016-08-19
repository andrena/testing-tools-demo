clothingTipsApp.controller('ClothingTipsController', function(geolocation, clothingTipsService, $scope) {
	
	$scope.clothingTipsService = clothingTipsService;
	$scope.geolocation = geolocation;

	$scope.clothingTips.weatherText = "Das Wetter für Ihren Standort wird ermittelt...";
	$scope.clothingTips.image = "http://${host.runtime.ip}:8080/loading.gif";
	
	$scope.geolocation.getLocation({
        timeout: 10000
    }).then(function(position) {
    	showRecommendation(position);
    }, function(error) {
    	$scope.clothingTips.weather = "Das Wetter für Ihren Standort konnte nicht ermittelt werden.";
    });

	function showRecommendation(position) {
		$scope.clothingTipsService.get({longitude: position.coords.longitude, latitude: position.coords.latitude}, function(recommendations) {
			$scope.clothingTips.weatherText = recommendations.temperature + " °C";
			$scope.clothingTips.image = "http://${imageservice.host}:8282/image/" + recommendations.temperatureSpec + "/";
			$scope.clothingTips.city = recommendations.city;
			$scope.$apply();
		} );
	}

});
