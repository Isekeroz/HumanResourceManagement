var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {

	$http({
 		url: 'http://localhost:8081/api/listDepartment',
	    	method: "POST",
	    	data: {},
	    	headers: {'Content-type': 'application/json'}
 	})
 	.then(function(response){
 		console.log('success');
 		$scope.departmentList = response.data;
 	},
 	function(response){
 		console.log('fail');
 	});
     
     $scope.saveDepartment = function(){
      	
      	var departmentName = $scope.name;
      	
      	$http({
      		url: 'http://localhost:8081/api/createDepartment',
  	    	method: "POST",
  	    	data: {
  	    		name: departmentName
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



