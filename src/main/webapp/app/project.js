angular.module('project', ['ngRoute', 'restApi', 'userstudy_directives'])

.config( function($provide, $httpProvider, $routeProvider) {
  $routeProvider
    .when('/', {
      controller: 'ParticipantsController',
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
    
  $provide.factory('HttpErrorInterceptor', function($rootScope, $q) {
    return {
      'responseError': function(rejection) {
        $rootScope.errors.push(rejection.data);
        return $q.reject(rejection);
      }
    }
  });
  
  $httpProvider.interceptors.push('HttpErrorInterceptor');
})

.controller('ThreadsController', ['$scope', '$routeParams', 'Threads', function($scope, $routeParams, Threads) {
  $scope.threads = Threads.query();
  $scope.participantid = $routeParams.participantid;
}])

.controller('ParticipantsController', function($scope, $rootScope, $location, Participants) {
  $scope.participant = {};
  
  $scope.createParticipant = function() {
    $scope.participant = Participants.save($scope.participant, function() {
      $location.path($scope.participant.id);
      $rootScope.notifications.push({message: 'Make sure to save this url, if you want to continue your work later on'});
    });
  }
})

.controller('ThreadController', function($scope, $routeParams, Threads) {
  $scope.thread = Threads.get({threadid: $routeParams.threadid});
  $scope.items = Threads.getItems({threadid: $routeParams.threadid});
})

.controller('ItemController', function($scope, $routeParams, Ratings) {
  $scope.participantid = $routeParams.participantid;
  $scope.rating = Ratings.get({participantid: $scope.participantid, itemId: $scope.item.id}, function() {
  }, function(response) {
    if (response.status == 404) {
      $scope.rating = Ratings.save({participantid: $scope.participantid}, {itemId: $scope.item.id});
    }
  });
  
  $scope.saveRating = function(rating) {
    $scope.rating.rating = rating;
    $scope.rating.$save({participantid: $scope.participantid});
  };
});
