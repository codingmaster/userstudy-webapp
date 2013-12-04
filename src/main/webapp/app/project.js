angular.module('project', ['ngRoute', 'threadsServices'])

.config(function($routeProvider) {
  $routeProvider
    .when('/', {
      controller: 'UserController',
      templateUrl: 'app/create_user.html'
    })
    .when('/:userid', {
      controller: 'ThreadsController',
      templateUrl: 'app/threads.html'
    })
    .when('/:userid/thread/:threadid', {
      controller: 'ThreadController',
      templateUrl: 'app/thread.html'
    })
    .otherwise({
      redirectTo:'/'
    });
})

.controller('ThreadsController', function($scope, Threads) {
  /*$scope.threads = [
    {title: 'A thread title', description: 'a thread description'},
    {title: 'Another thread title', description: 'lorem ipsum'}
  ]*/
  $scope.threads = Threads.query();
})

.controller('UserController', function($scope, $location, Users) {
  $scope.user = {};
  
  $scope.createUser = function() {
    $scope.user = Users.save($scope.user, function() {
      $location.path($scope.user.id);
    });
    
  }
});