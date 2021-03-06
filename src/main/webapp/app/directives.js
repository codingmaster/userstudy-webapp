angular.module('userstudy_directives', ['ngRoute', 'restApi'])

.directive('userstudyRating', function() {
  return {
    restrict: 'A',
    templateUrl: 'app/directives/rating.html',
    scope: {
      ratingValue: '=',
      max: '=',
      title: '=',
      onRatingSelected: '&'
    },
    link: function (scope, element, attributes) {
      //scope.ratingValue = 0;
      
      var updateStars = function() {
        scope.stars = [];
        for (var i = 0; i < scope.max; i++) {
          scope.stars.push({filled: i < scope.ratingValue});
        }
      };
      
      scope.$watch('ratingValue', function(newVal, oldVal) {
        if (newVal) {
          updateStars();
        }
      });
      
      scope.toggle = function(index) {
        scope.ratingValue = index + 1;
        scope.onRatingSelected({newRating: index + 1});
      }
      
      updateStars();
    }
  }
})

.directive('navigation', function($routeParams) {
  return {
    restrict: 'A',
    templateUrl: 'app/directives/navigation.html',
    link: function(scope, element, attributes) {
      scope.links = {};
      var updateParticipantLink = function() {
        if ($routeParams.participantid) {
          scope.links['participant'] = ({ref: '#/' + $routeParams.participantid, label: 'Home'});
        }
      };
      
      updateParticipantLink();
      
      scope.$on('$routeChangeSuccess', function() {
        updateParticipantLink();
      });
    }
  };
})

.directive('likertItem', function() {
  return {
    restrict: 'A',
    templateUrl: 'app/directives/likert-item.html',
    scope: {
      item: '=',
      options: '=',
      subjectId: '=',
      onRatingSelected: '&'
    },
    
    link: function(scope, element, attributes) {
      scope.$watch('item.rating.rating', function(newVal, oldVal) {
        if (newVal && newVal != oldVal) {
          scope.onRatingSelected({rating: scope.item.rating});
        }
      });
    }
  }
})

.directive('likertItems', function() {
  return {
    restrict: 'A',
    templateUrl: 'app/directives/likert-items.html',
    scope: {
      items: '=',
      options: '=',
      subjectId: '=',
      onRatingSelected: '&'
    },
    link: function(scope, element, attributes) {
      scope.saveRating = function(rating) {
        scope.onRatingSelected({rating: rating});
      }
    }
  }
});
