/**
 * 
 */
'use strict';

app.controller('TaskController', [
		'$scope',
		'TaskService','UserService',
		'$location',
		'$rootScope',
		function($scope, TaskService, $location, $rootScope) {
			console.log("TaskController...")
			var self = this; // self is alias name instead directly use this
			self.task = { // initialization
				taskid : '',
				userid:'',
				title : '',
				taskdate : '',
				description:'',
				status : '',
				ErrorCode:'',
				ErrorMsge:''
			};
			self.tasks = [];
						
				
			/*GET SELECTED TASK DETAILS*/

			self.getSelectedTask = getTask

			function getTask(taskid) {
				console.log("getting task! " + taskid)
			  TaskService.getTask(taskid).then(function(d) {
					self.task = d;					
				}, function(errResponse) {
					console.error('Error while fetching task details');
				});
			};

			/* GET LIST OF ALL TASKS */

			self.fetchAllTask = function() {
				console.log("getting list of tasks");
				TaskService.fetchAllTasks()
				.then(function(d) { 
					self.tasks = d;
				}, function(errResponse) {
					console.error('Error while fetching Tasks');
				});
			};
			self.fetchAllTasks();

			/*CREATE A TASK*/ 

			self.createTask = function(task) {
				console.log('submit a new task',self.task);
				TaskService.createTask(task)
				.then(function(d){
				   self.task=d;	
				},
				function(errResponse) {
							console.error('Error while creating Tasks');
						});
			};

			/* UPDATE A TASK*/ 

			self.updateTask = function(taskid,task) {
				console.log('updating task TaskController');
				TaskService.updateTask(taskid,task).then(self.fetchAllTasks,
						function(errResponse) {
							console.error('Error while updating Tasks');
						});
			};	

			/* DELETE A TASK*/ 
			
			self.deleteTask = function(taskid) {
				console.log('deleting task TaskController');
				TaskService.deleteTask(taskid).then(self.fetchAllTasks,
						function(errResponse) {
							console.error('Error while deleting Tasks');
						});
			};	
			

self.submit = function() {
		console.log('Saving New Task', self.task);
		
		self.createTask(self.task);
		self.reset();
};

self.edit=function(){
	
	console.log('Updating Task', self.task);
	
	self.updateTask(self.task.taskid,self.task);
	

self.reset();
};

self.reset=function(){
	console.log('resetting the task',self.task);
	self.task={
			taskid : '',
			userid:'',
			title : '',
			taskdate : '',
			description:'',
			status : '',
			ErrorCode:'',
			ErrorMsge:''
		};
     $scope.myForm.$setPristine(); //reset 

 
};
	
		} ]);