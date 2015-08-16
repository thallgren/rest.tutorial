'use strict';

angular.module('restTutorialApp').factory('Track', function($resource) {
  return $resource('api/tracks/:id', {
    id : '@id'
  }, {
    update : {
      method : 'PUT' // this method issues a PUT request
    }
  });
});
