angular.module('restApi', ['ngResource'])

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
  return $resource('rest/participants/:participantid/ratings/:ratingid', {ratingid: '@id'},
    {
      count: {method: 'GET', url: 'rest/participants/:participantid/ratings/count'}
    });
})

.factory('Questions', function($resource) {
  return $resource('rest/questions/:questionid');
});
