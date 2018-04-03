(function () {
  'use strict';

  var  app = angular.module('demoApp',[])
    .controller('basicExampleCtrl', ['$scope','$http', function ($scope, $http) {
	
	$scope.countries = [];
	$scope.cities = [];
	$scope.districts = [];
	$scope.user = {name:"",email:"",password:"",addresses : [],phone:"",role:0,department:0};
	
	$http({
 		url: 'http://localhost:8081/api/listUsers',
	    	method: "POST",
	    	data: {},
	    	headers: {'Content-type': 'application/json'}
 	})
 	.then(function(response){
 		console.log('success');
 		$scope.userList = response.data;
 	},
 	function(response){
 		console.log('fail');
 	});
	
	
	$scope.countryChangeHandler = function(rowIndex){
		
		var selectedCountryId = $('#val-country-'+rowIndex).val().replace('number:','');
		var _cities = [];
		
		$http({
	 		url: 'http://localhost:8081/api/listCities',
		    	method: "POST",
		    	data: {countryId : selectedCountryId},
		    	headers: {'Content-type': 'application/json'}
	 	})
	 	.then(function(response){
	 		_cities = response.data;
	 		 $scope.user.addresses[rowIndex].cities = _cities;
	 		
	 	},
	 	function(response){
	 		console.log('fail');
	 	});
	}
	
	
	$scope.cityChangeHandler = function(rowIndex){
		
		var selectedCityId = $('#val-city-'+rowIndex).val().replace('number:','');
		var _districts = [];
		
		$http({
	 		url: 'http://localhost:8081/api/listDistricts',
		    	method: "POST",
		    	data: {cityId : selectedCityId},
		    	headers: {'Content-type': 'application/json'}
	 	})
	 	.then(function(response){
	 		_districts = response.data;
	 		 $scope.user.addresses[rowIndex].districts = _districts;  
	 		
	 	},
	 	function(response){
	 		console.log('fail');
	 	});
	}
	
	$http({
 		url: 'http://localhost:8081/api/listDepartment',
	    	method: "POST",
	    	data: {},
	    	headers: {'Content-type': 'application/json'}
 	})
 	.then(function(response){
 		console.log('success');
 		$scope.departments= response.data;
 	},
 	function(response){
 		console.log('fail');
 	});
     
	
	$http({
 		url: 'http://localhost:8081/api/listRole',
	    	method: "POST",
	    	data: {},
	    	headers: {'Content-type': 'application/json'}
 	})
 	.then(function(response){
 		console.log('success');
 		$scope.roles = response.data;
 	},
 	function(response){
 		console.log('fail');
 	});
    
	$scope.departmentChanged = function(){
		if($scope.user.role == 3 || $scope.user.role == '3'){
			
			$http({
	    		url: 'http://localhost:8081/api/controlDepartmentManager',
		    	method: "POST",
		    	data: {
		    		departmentId : $scope.user.department
		    	},
		    	headers: {'Content-type': 'application/json'}
	    	})
	    	.then(function(response){
	    		console.log('success');
	    		if(response.data != 0){
	    			alert('The department you chose already has a manager. This user will be assigned as the new manager of the department.');
	    		}
	    	},
	    	function(response){
	    		console.log('fail');
	    	});
		}
	}
	
	$scope.submit = function(){
    	
    	var username = $scope.name;
    	console.log(JSON.stringify($scope.user));
    	
    	$http({
    		url: 'http://localhost:8081/api/createUser',
	    	method: "POST",
	    	data: {
	    		user: JSON.stringify($scope.user)
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
     
     $scope.addAddress = function(){
    	var index = $scope.user.addresses.length;
    			
    		var _countries = [];
	    	$http({
	     		url: 'http://localhost:8081/api/listCountries',
	    	    	method: "POST",
	    	    	data: {},
	    	    	headers: {'Content-type': 'application/json'}
	     	})
	     	.then(function(response){
	     		console.log('success');
	     		_countries = response.data;
	     		$scope.user.addresses.push({id:index,name:'',country:-1,city:-1,district:-1,detailed:'', countries:_countries, cities:[], districts:[]});
	     	},
	     	function(response){
	     		console.log('fail');
	     	});
     }
     
     $scope.onNameKeyUp = function (event, index) {
         var newValue = event.target.value;
         var nodeData = $scope.user.addresses[index];
         nodeData.name = newValue;
     };
       
	 $scope.onDetailedKeyUp = function (event, index) {
	       var newValue = event.target.value;
	       var nodeData = $scope.user.addresses[index];
	       nodeData.detailed = newValue;
	 };
	 
    }]);

}());

