angular.module('project', ['ngRoute', 'threadsServices'])

.config(function($routeProvider) {
  $routeProvider
    .when('/', {
      controller: 'ThreadsController',
      templateUrl: 'app/threads.html'
    })
    .otherwise({
      redirectTo:'/'
    });
})

.controller('ThreadsController', ['$scope', 'Threads', function($scope, Threads) {
  /*$scope.threads = [
    {title: 'A thread title', description: 'a thread description'},
    {title: 'Another thread title', description: 'lorem ipsum'}
  ]*/
  $scope.threads = Threads.query();
}]);
