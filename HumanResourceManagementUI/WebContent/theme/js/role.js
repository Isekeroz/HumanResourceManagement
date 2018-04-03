var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
     
     $scope.saveRole = function(){
      	
      	var roleName = $scope.name;
      	
      	$http({
      		url: 'http://localhost:8081/api/createRole',
  	    	method: "POST",
  	    	data: {
  	    		name: roleName
  	    	},
  	    	headers: {'Content-type': 'application/json'}
      	})
      	.then(function(response){
      		console.log('success');
      	},
      	function(response){
      		console.log('fail');
      	});
      	
      	
       };
      
});



