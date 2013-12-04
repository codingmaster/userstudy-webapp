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
});