clothingTipsApp.factory("clothingTipsService", function($resource) {
	
	return $resource('/clothing/recommendation/:longitude/:latitude/', {
		longitude : '@longitude',
		latitude : '@latitude'
	});
	
});
