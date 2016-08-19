clothingTipsApp.controller('ClothingTipsController', function(geolocation, clothingTipsService, $scope) {
	
	$scope.clothingTipsService = clothingTipsService;
	$scope.geolocation = geolocation;

	$scope.clothingTips.weatherText = "Das Wetter an Ihrem Standort wird ermittelt...";
	$scope.clothingTips.image = "https://${host.runtime.ip}:8843/loading.gif";
	
	$scope.geolocation.getLocation({
        timeout: 10000
    }).then(function(position) {
    	showRecommendation(position, "http://${host.runtime.ip}:8282/image/");
    }, function(error) {
    	$scope.clothingTips.weather = "Das Wetter an Ihrem Standort konnte nicht ermittelt werden.";
    });

	function showRecommendation(position, urlPrefix) {
		$scope.clothingTipsService.get({longitude: position.coords.longitude, latitude: position.coords.latitude}, function(recommendations) {
			$scope.clothingTips.weatherText = recommendations.temperature + " °C";
			$scope.clothingTips.image = urlPrefix + recommendations.temperatureSpec + "/";
			$scope.clothingTips.city = recommendations.city;
			$scope.$apply();
		});
	}

});
