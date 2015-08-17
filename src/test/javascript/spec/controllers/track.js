'use strict';

describe('Controller: TrackListController', function () {

  // load the controller's module
  beforeEach(module('restTutorialApp'));

  var TrackListController,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TrackListController = $controller('TrackListController', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of tracks to the scope', function () {
  });
});
