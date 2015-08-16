// Karma configuration
// http://karma-runner.github.io/0.12/config/configuration-file.html
// Generated on 2015-07-22 using
// generator-karma 1.0.0

module.exports = function(config) {
  'use strict';

  config.set({
    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,

    // base path, that will be used to resolve files and exclude
    basePath: '../../',

    // testing framework to use (jasmine/mocha/qunit/...)
    // as well as any additional frameworks (requirejs/chai/sinon/...)
    frameworks: [
      "jasmine"
    ],

    // list of files / patterns to load in the browser
    files: [
      // bower:js
      'main/webapp/assets/bower_components/modernizr/modernizr.js',
      'main/webapp/assets/bower_components/jquery/dist/jquery.js',
      'main/webapp/assets/bower_components/bootstrap/dist/js/bootstrap.js',
      'main/webapp/assets/bower_components/json3/lib/json3.js',
      'main/webapp/assets/bower_components/angular/angular.js',
      'main/webapp/assets/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
      'main/webapp/assets/bower_components/angular-ui-router/release/angular-ui-router.js',
      'main/webapp/assets/bower_components/angular-resource/angular-resource.js',
      'main/webapp/assets/bower_components/angular-cookies/angular-cookies.js',
      'main/webapp/assets/bower_components/angular-sanitize/angular-sanitize.js',
      'main/webapp/assets/bower_components/angular-translate/angular-translate.js',
      'main/webapp/assets/bower_components/messageformat/messageformat.js',
      'main/webapp/assets/bower_components/angular-translate-interpolation-messageformat/angular-translate-interpolation-messageformat.js',
      'main/webapp/assets/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js',
      'main/webapp/assets/bower_components/angular-translate-loader-partial/angular-translate-loader-partial.js',
      'main/webapp/assets/bower_components/angular-dynamic-locale/src/tmhDynamicLocale.js',
      'main/webapp/assets/bower_components/angular-local-storage/dist/angular-local-storage.js',
      'main/webapp/assets/bower_components/angular-cache-buster/angular-cache-buster.js',
      'main/webapp/assets/bower_components/ngInfiniteScroll/build/ng-infinite-scroll.js',
      'main/webapp/assets/bower_components/ng-file-upload/ng-file-upload.js',
      'main/webapp/assets/bower_components/angular-mocks/angular-mocks.js',
      // endbower
      'main/webapp/app/app.js',
      'main/webapp/app/**/*.js',
      'main/webapp/app/**/*.html',
      'main/webapp/*.html',
      'test/javascript/**/!(karma.conf).js'
    ],

    // list of files / patterns to exclude
    exclude: [
    ],

    // web server port
    port: 8080,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: [
      "PhantomJS"
    ],

    // Which plugins to enable
    plugins: [
      "karma-phantomjs-launcher",
      "karma-jasmine"
    ],

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: false,

    colors: true,

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_INFO,

    // Uncomment the following lines if you are using grunt's server to run the tests
    // proxies: {
    //   '/': 'http://localhost:9000/'
    // },
    // URL root prevent conflicts with the site root
    // urlRoot: '_karma_'
  });
};
