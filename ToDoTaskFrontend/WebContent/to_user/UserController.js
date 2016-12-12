/**
 * 
 */
'use strict';

app.controller('UserController', [
		'$scope',
		'UserService',
		'$location',
		'$rootScope',
		function($scope, UserService, $location, $rootScope) {
			console.log("UserController...")
			var self = this;
			self.user = {
				userid : '',
				username : '',
				password : '',
				confirmpassword:'',
				contact : '',
				email : '',
				city:'',
				ErrorCode : '',
				ErrorMsge:''
			};
			self.users = [];
			
			/*GET SELECTED USER DETAILS*/

			self.getSelectedUser = getUser

			function getUser(userid) {
				console.log("getting user! " + userid)
			  UserService.getUser(userid).then(function(d) {
					self.user = d;					
				}, function(errResponse) {
					console.error('Error while fetching user details');
				});
			};


			self.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					self.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};
			self.fetchAllUsers();

			/* CREATE USER */
			self.createUser = function(user) {
				console.log('submit a new user',self.user);
				UserService.createUser(user)
				.then(function(d){
				   self.user=d;	
				},
				function(errResponse) {
							console.error('Error while creating User');
						});
			};

			/* UPDATE USER */
			self.updateUser = function(user, id) {
				UserService.updateUser(user, id)
				.then(self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while updating User...');
						});
			};

			/* AUTHENTICATION OF USER */
			self.authenticate = function(user) {
				UserService.authenticate(user)
				.then(function(d) {
					console.log('authenticating user in UserController');
					self.user = d;
					if ($rootScope.currentUser) {
						$location.path('/');
					}
				}, function(errResponse) {
					console.error('Error while authenticate User...');
				});
			};

			/* DELETE USER */
			self.deleteUser = function(id) {
				UserService.deleteUser(id)
				.then(self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while deleting User');
						});
			};

			self.login = function() {
				{
					console.log('login validation????', self.user);
					self.authenticate(self.user);
				}
			};
			
			
			
			self.remove = function(id) {
				console.log('id to be deleted', id);
				if (self.user.id == id) {
					self.reset();
				}
				self.deleteUser(id);
			};
			
			self.logout=function(){
				console.log('Logging out');
				UserService.logout()
				.then(function() {
					if ($rootScope.currentUser='') {
						$location.path("/");
						alert("You have successfully logged out!")
					}},
						
						function(errResponse) {
					console.error('Error while logging out');
				});
				
				self.submit = function() {
						console.log('Saving New user', self.user);
						self.createUser(self.user);
					
				self.reset();
				};
				
				
				self.reset=function(){
					console.log('reset user',self.user);
					self.user={
							userid : '',
							username : '',
							password : '',
							confirmpassword:'',
							contact : '',
							email : '',
							city:'',
							ErrorMsge : '',
							ErrorCode:''

						};
				     $scope.myForm.$setPristine(); // reset 
				};

	
			/* END OF ALL */
};
	
		} ]);