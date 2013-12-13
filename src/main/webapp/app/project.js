angular.module('project', ['ngRoute', 'ngSanitize', 'restApi', 'userstudy_directives'])

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

.controller('ThreadController', function($scope, $routeParams, Threads, Questions) {
  $scope.participantId = $routeParams.participantid;
  $scope.thread = Threads.get({threadid: $routeParams.threadid});
  $scope.items = Threads.getItems({threadid: $routeParams.threadid});
  $scope.questions = Questions.query();
})

.controller('ItemController', function($scope, $routeParams, $http, Ratings) {
  
  $scope.likertOptions = [
    {value: 1,
    label: 'Strongly Disagree'},
    {value: 2,
    label: 'Somewhat Disagree'},
    {value: 3,
    label: 'Neither, nor'},
    {value: 4,
    label: 'Somewhat Agree'},
    {value: 5,
    label: 'Strongly Agree'}];
  
  $scope.saveLikertRating = function(rating) {
    rating.$save({participantid: $scope.participantid});
  };
  
  $scope.participantid = $routeParams.participantid;
  var ratingsByQuestion = {};
  
  $scope.likertItems = [];
  $scope.questions.$promise.then(
    function(questions) {
      var ratings = Ratings.query({participantid: $scope.participantid, itemId: $scope.item.id});
      ratings.$promise.then(
      function(ratings) {
        angular.forEach(ratings, function(rating) {
          ratingsByQuestion[rating.questionId] = rating;
        });
        
        angular.forEach(questions, function(question) {
          var rating = ratingsByQuestion[question.id];
          if (!rating) {
            rating = new Ratings({itemId: $scope.item.id, questionId: question.id});
          }
          var item = { question: question, rating: rating};
          $scope.likertItems.push(item);
        });
      });
    }
  )
  
  
  
  /*$http.get('rest/participants/' + $scope.participantid + '/ratings', {params: {itemId: $scope.item.id}, bypassInterceptor: {onError: 404}})
    .success(function(data, status, headers, config) {
      $scope.ratings = data;
    })
    .error(function(data, status, headers, config) {
      $scope.rating = Ratings.save({participantid: $scope.participantid}, {itemId: $scope.item.id})
    });*/
  
  $scope.saveRating = function(rating) {
    $scope.rating.$save({participantid: $scope.participantid});
    Ratings.save({participantid: $scope.participantid, ratingid: $scope.rating.id}, {rating: rating},
    function() {
      $scope.rating.rating = rating;
    });
  };
});
