angular.module('project', ['ngRoute', 'threadsServices'])

.config(function($routeProvider) {
  $routeProvider
    .when('/', {
      controller: 'UserController',
      templateUrl: 'app/create_user.html'
    })
    .when('/:participantid', {
      controller: 'ThreadsController',
      templateUrl: 'app/threads.html'
    })
    .when('/:participantid/thread/:threadid', {
      controller: 'ThreadController',
      templateUrl: 'app/thread.html'
    })
    .otherwise({
      redirectTo:'/'
    });
})

.controller('ThreadsController', ['$scope', '$routeParams', 'Threads', function($scope, $routeParams, Threads) {
  $scope.threads = Threads.query();
  $scope.participantid = $routeParams.participantid;
}])

.controller('UserController', function($scope, $location, Users) {
  $scope.participant = CurrentParticipant;
  
  $scope.createUser = function() {
    $scope.participant = Participants.save($scope.participant, function() {
      $location.path($scope.participant.id);
    });
  }
})

.controller('ThreadController', function($scope, $routeParams, Threads) {
  $scope.thread = Threads.get({threadid: $routeParams.threadid});
  $scope.items = Threads.getItems({threadid: $routeParams.threadid});
});