'use strict';

angular.module('restTutorialApp').service('PopupService', function($window) {
  this.showPopup = function(message) {
    return $window.confirm(message);
  };
});
