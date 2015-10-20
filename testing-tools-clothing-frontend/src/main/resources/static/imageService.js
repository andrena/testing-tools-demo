clothingTipsApp.factory("imageService", function($resource) {
	
	return $resource('/clothing/recommendation/:longitude/:latitude/', {
		longitude : '@longitude',
		latitude : '@latitude'
	});
	
});
