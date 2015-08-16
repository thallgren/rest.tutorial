'use strict';

angular.module('restTutorialApp').controller('TrackListController', function($scope, $state, PopupService, $window, Track) {
  $scope.tracks = Track.query();

  $scope.deleteTrack = function(track) { // Delete a Track. Issues a
    if (PopupService.showPopup('Really delete this?')) {
      track.$delete(function() {
        $window.location.href = ''; // redirect to home
      });
    }
  };
}).controller('TrackViewController', function($scope, $stateParams, Track) {
  $scope.track = Track.get({
    id : $stateParams.id
  });
}).controller('TrackCreateController', function($scope, $state, $stateParams, Track) {
  $scope.track = new Track();

  $scope.addTrack = function() {
    $scope.track.$save(function() {
      $state.go('tracks'); // on success go back to home i.e. Tracks state.
    });
  };
}).controller('TrackEditController', function($scope, $state, $stateParams, Track) {
  $scope.updateTrack = function() {
    $scope.track.$update(function() {
      $state.go('tracks'); // on success go back to home i.e. Tracks state.
    });
  };

  $scope.loadTrack = function() {
    $scope.track = Track.get({
      id : $stateParams.id
    });
  };

  $scope.loadTrack(); // Load a Track which can be edited on UI
});
