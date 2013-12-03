angular.module('threadsServices', ['ngResource'])

.factory('Threads', function($resource) {
  return $resource('rest/threads/:threadid', {}, []);
})

.factory('Users', function($resource) {
  return $resource('rest/participants/:participantid');
});