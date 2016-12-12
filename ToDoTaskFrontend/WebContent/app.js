var app=angular.module('myApp',['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	
	.when('/',{
		templateUrl:'to_home/home.html',
		controller:'HomeController'
	})
	
	.when('/home',{
		templateUrl:'to_home/home.html',
		controller:'HomeController'
	})

	.when('/about',{
		templateUrl:'to_home/about.html',
	})
	
	.when('/contact',{
		templateUrl:'to_home/contact.html',
	})

	.when('/login',{
		templateUrl:'to_user/login.html',
		controller:'UserController'
	})
	
	.when('/signup',{
		templateUrl:'to_user/signup.html',
		controller:'UserController'
		
	})
	/**
	 * TASK RELATED MAPPING
	 **/
	
	.when('/createtask',{
		templateUrl:'to_task/createtask.html',
		controller:'TaskController'
	})
	
	.when('/tasklist',{
		templateUrl:'to_task/tasklist.html',
		controller:'TaskController'
	})
	
	.when('/viewtask',{
		templateUrl:'to_task/viewtask.html',
		controller:'TaskController'
	})
	
	.when('/updatetask',{
		templateUrl:'to_task/updatetask.html',
		controller:'TaskController'
	})
	

	.otherwise({redirectTo: '/'});

});
