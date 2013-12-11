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
        if (rejection.config.bypassInterceptor && rejection.config.bypassInterceptor.onError == rejection.status) {
          return $q.reject(rejection);
        }
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
  $scope.participantId = $routeParams.participantid;
  $scope.thread = Threads.get({threadid: $routeParams.threadid});
  $scope.items = Threads.getItems({threadid: $routeParams.threadid});
})

.controller('ItemController', function($scope, $routeParams, $http, Ratings) {
  $scope.likertItems = [{
    id: 'q1',
    stimulus: 'The entities mentioned in the article belong to this topic',
  },
  {
    id: 'q2',
    stimulus: 'The article introduces unexepected relationships between entities'},
  {
    id: 'q3',
    stimulus: 'The article combines the known information with a new topical area'}];
  
  $scope.likertOptions = [
    {value: 1,
    label: 'Strong Disagree'},
    {value: 2,
    label: 'Somewhat Disagree'},
    {value: 3,
    label: 'Neither, nor'},
    {value: 4,
    label: 'Somewhat Agree'},
    {value: 5,
    label: 'Strong Agree'}];
  
  $scope.saveLikertRating = function(rating, likertItemId) {
    console.log('Rating: ' + rating);
    console.log('Item: ' + likertItemId);
  };
  
  $scope.participantid = $routeParams.participantid;
  $http.get('rest/participants/' + $scope.participantid + '/ratings', {params: {itemId: $scope.item.id}, bypassInterceptor: {onError: 404}})
    .success(function(data, status, headers, config) {
      $scope.rating = data;
    })
    .error(function(data, status, headers, config) {
      $scope.rating = Ratings.save({participantid: $scope.participantid}, {itemId: $scope.item.id})
    });
  
  $scope.saveRating = function(rating) {
    //$scope.rating.$save({participantid: $scope.participantid});
    Ratings.save({participantid: $scope.participantid, ratingid: $scope.rating.id}, {rating: rating},
    function() {
      $scope.rating.rating = rating;
    });
  };
});
