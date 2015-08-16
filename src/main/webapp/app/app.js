'use strict';

angular.module('restTutorialApp', [ 'ui.router', 'ngResource' ]).config(function($stateProvider) {
  $stateProvider.state('tracks', { // state for showing all tracks
    url : '/tracks',
    templateUrl : 'app/components/track/tracks.html',
    controller : 'TrackListController'
  }).state('viewTrack', { // state for showing single track
    url : '/track/:id/view',
    templateUrl : 'app/components/track/track-view.html',
    controller : 'TrackViewController'
  }).state('newTrack', { // state for adding a new track
    url : '/track/new',
    templateUrl : 'app/components/track/track-add.html',
    controller : 'TrackCreateController'
  }).state('editTrack', { // state for updating a track
    url : '/track/:id/edit',
    templateUrl : 'app/components/track/track-edit.html',
    controller : 'TrackEditController'
  });
}).run(function($state) {
  $state.go('tracks'); // make a transition to tracks state
  // when app starts
});