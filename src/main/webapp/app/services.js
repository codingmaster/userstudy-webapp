angular.module('threadsServices', ['ngResource'])

.factory('Threads', ['$resource', function($resource) {
  return $resource('rest/threads/:threadid', {}, []);
}]);