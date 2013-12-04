angular.module('threadsServices', ['ngResource'])

.factory('Threads', function($resource) {
  var url = 'rest/threads/:threadid';
  return $resource(url,
    {},
    {getItems: {method: 'GET', url: url + '/items', isArray: true}});
})

.factory('Participants', function($resource) {
  var url = 'rest/participants/:participantid';
  var ratingsUrl = url + '/ratings';
  return $resource('rest/participants/:participantid');
})

.factory('Ratings', function($resource) {
  return $resource('rest/participants/:participantid/ratings/:ratingid', {ratingid: '@id'});
});
