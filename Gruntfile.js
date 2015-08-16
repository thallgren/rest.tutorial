// Generated on 2015-08-11 using generator-angular 0.12.1
'use strict';

var fs = require('fs');

var parseString = require('xml2js').parseString;
// Returns the second occurence of the version number
var parseVersionFromPomXml = function() {
    var version;
    var pomXml = fs.readFileSync('pom.xml', "utf8");
    parseString(pomXml, function (err, result){
        version = result.project.version[0];
    });
    return version;
};

module.exports = function (grunt) {
  require('load-grunt-tasks')(grunt);

  // Time how long tasks take. Can help when optimizing build times
  require('time-grunt')(grunt);

  var serveStatic = require('serve-static');

  // Automatically load required Grunt tasks
  require('jit-grunt')(grunt, {
    useminPrepare: 'grunt-usemin',
    ngtemplates: 'grunt-angular-templates',
    cdnify: 'grunt-google-cdn'
  });

  // Configurable paths for the application
  var appConfig = {
    app: require('./bower.json').appPath || 'app',
    test: require('./bower.json').testPath || 'test',
    dist: 'target/webapp'
  };

  // Define the configuration for all the tasks
  grunt.initConfig({
	appConfig: appConfig,

	// Watches files for changes and runs tasks based on the changed files
    watch: {
      bower: {
        files: ['bower.json'],
        tasks: ['wiredep']
      },
      js: {
        files: ['<%= appConfig.app %>/app/**/*.js'],
        tasks: ['newer:jshint:all'],
        options: {
          livereload: '<%= connect.options.livereload %>'
        }
      },
      jsTest: {
        files: ['<%= appConfig.test %>/spec/**/*.js'],
        tasks: ['newer:jshint:test', 'karma']
      },
      styles: {
        files: ['<%= appConfig.app %>/assets/styles/{,*/}*.css'],
        tasks: ['newer:copy:styles', 'autoprefixer']
      },
      gruntfile: {
        files: ['Gruntfile.js']
      },
      ngconstant: {
          files: ['Gruntfile.js', 'pom.xml'],
          tasks: ['ngconstant:dev']
      },
      livereload: {
        options: {
          livereload: '<%= connect.options.livereload %>'
        },
        files: [
          '<%= appConfig.app %>/*.html',
          '<%= appConfig.app %>/app/**/*.html',
          '.tmp/assets/styles/{,*/}*.css',
          '<%= appConfig.app %>/assets/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}'
        ]
      }
    },

    // The actual grunt server settings
    connect: {
      options: {
        port: 9000,
        // Change this to '0.0.0.0' to access the server from outside.
        hostname: 'localhost',
        livereload: 35729
      },
      livereload: {
        options: {
          open: true,
          middleware: function () {
            return [
              serveStatic('.tmp'),
              serveStatic(appConfig.app)
            ];
          }
        }
      },
      test: {
        options: {
          port: 9001,
          middleware: function () {
            return [
              serveStatic('.tmp'),
              serveStatic('<%= appConfig.test %>'),
              serveStatic(appConfig.app)
            ];
          }
        }
      },
      dist: {
        options: {
          open: true,
          base: '<%= appConfig.dist %>'
        }
      }
    },

    // Make sure code styles are up to par and there are no obvious mistakes
    jshint: {
      options: {
        jshintrc: '.jshintrc',
        reporter: require('jshint-stylish')
      },
      all: {
        src: [
          'Gruntfile.js',
          '<%= appConfig.app %>/app/**/*.js'
        ]
      },
      test: {
        options: {
          jshintrc: '<%= appConfig.test %>/.jshintrc'
        },
        src: ['<%= appConfig.test %>/spec/**/*.js']
      }
    },

    // Empties folders to start fresh
    clean: {
      dist: {
        files: [{
          dot: true,
          src: [
            '.tmp',
            '<%= appConfig.dist %>/{,*/}*',
            '!<%= appConfig.dist %>/.git{,*/}*'
          ]
        }]
      },
      server: '.tmp'
    },

    // Add vendor prefixed styles
    autoprefixer: {
      options: {
        browsers: ['last 1 version']
      },
      server: {
        options: {
          map: true,
        },
        files: [{
          expand: true,
          cwd: '.tmp/assets/styles/',
          src: '{,*/}*.css',
          dest: '.tmp/assets/styles/'
        }]
      },
      dist: {
        files: [{
          expand: true,
          cwd: '.tmp/assets/styles/',
          src: '{,*/}*.css',
          dest: '.tmp/assets/styles/'
        }]
      }
    },

    // Automatically inject Bower components into the app
    wiredep: {
      app: {
        src: ['<%= appConfig.app %>/index.html'],
        ignorePath:  /\.\.\//
      },
      test: {
        devDependencies: true,
        src: '<%= karma.unit.configFile %>',
        ignorePath: /\.\.\/\.\.\//, // remove ../../ from paths of injected javascripts
        fileTypes:{
          js: {
            block: /(([\s\t]*)\/{2}\s*?bower:\s*?(\S*))(\n|\r|.)*?(\/{2}\s*endbower)/gi,
              detect: {
                js: /'(.*\.js)'/gi
              },
              replace: {
                js: '\'{{filePath}}\','
              }
            }
          }
      }
    },

    // Renames files for browser caching purposes
    filerev: {
      dist: {
        src: [
          '<%= appConfig.dist %>/app/**/*.js',
          '<%= appConfig.dist %>/assets/styles/{,*/}*.css',
          '<%= appConfig.dist %>/assets/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
          '<%= appConfig.dist %>/assets/styles/fonts/*'
        ]
      }
    },

    // Reads HTML for usemin blocks to enable smart builds that automatically
    // concat, minify and revision files. Creates configurations in memory so
    // additional tasks can operate on them
    useminPrepare: {
      html: '<%= appConfig.app %>/index.html',
      options: {
        dest: '<%= appConfig.dist %>',
        flow: {
          html: {
            steps: {
              js: ['concat', 'uglifyjs'],
              css: ['cssmin']
            },
            post: {}
          }
        }
      }
    },

    // Performs rewrites based on filerev and the useminPrepare configuration
    usemin: {
      html: ['<%= appConfig.dist %>/**/*.html'],
      css: ['<%= appConfig.dist %>/assets/styles/{,*/}*.css'],
      js: ['<%= appConfig.dist %>/app/**/*.js'],
      options: {
        assetsDirs: [
          '<%= appConfig.dist %>/assets',
          '<%= appConfig.dist %>/assets/images',
          '<%= appConfig.dist %>/assets/styles'
        ],
        patterns: {
          js: [[/assets\/(images\/[^''""]*\.(png|jpg|jpeg|gif|webp|svg))/g, 'Replacing references to images']]
        }
      }
    },

    // The following *-min tasks will produce minified files in the dist folder
    // By default, your `index.html`'s <!-- Usemin block --> will take care of
    // minification. These next options are pre-configured if you do not wish
    // to use the Usemin blocks.
    // cssmin: {
    //   dist: {
    //     files: {
    //       '<%= appConfig.dist %>/assets/styles/main.css': [
    //         '.tmp/styles/{,*/}*.css'
    //       ]
    //     }
    //   }
    // },
    // uglify: {
    //   dist: {
    //     files: {
    //       '<%= appConfig.dist %>/scripts/scripts.js': [
    //         '<%= appConfig.dist %>/scripts/scripts.js'
    //       ]
    //     }
    //   }
    // },
    // concat: {
    //   dist: {}
    // },

    imagemin: {
      dist: {
        files: [{
          expand: true,
          cwd: '<%= appConfig.app %>/assets/images',
          src: '{,*/}*.{png,jpg,jpeg,gif}',
          dest: '<%= appConfig.dist %>/assets/images'
        }]
      }
    },

    svgmin: {
      dist: {
        files: [{
          expand: true,
          cwd: '<%= appConfig.app %>/assets/images',
          src: '{,*/}*.svg',
          dest: '<%= appConfig.dist %>/assets/images'
        }]
      }
    },

    htmlmin: {
      dist: {
        options: {
          collapseWhitespace: true,
          conservativeCollapse: true,
          collapseBooleanAttributes: true,
          removeCommentsFromCDATA: true
        },
        files: [{
          expand: true,
          cwd: '<%= appConfig.dist %>',
          src: ['*.html'],
          dest: '<%= appConfig.dist %>'
        }]
      }
    },

    ngtemplates: {
      dist: {
        options: {
          module: 'restTutorialApp',
          htmlmin: '<%= htmlmin.dist.options %>',
          usemin: 'app/app.js'
        },
        cwd: '<%= appConfig.app %>',
        src: 'app/components/**/*.html',
        dest: '.tmp/templateCache.js'
      }
    },

    // ng-annotate tries to make the code safe for minification automatically
    // by using the Angular long form for dependency injection.
    ngAnnotate: {
      dist: {
        files: [{
          expand: true,
          cwd: '.tmp/concat/scripts',
          src: '*.js',
          dest: '.tmp/concat/scripts'
        }]
      }
    },

    // Replace Google CDN references
    cdnify: {
      dist: {
        html: ['<%= appConfig.dist %>/*.html']
      }
    },

    // Copies remaining files to places other tasks can use
    copy: {
      dist: {
        files: [{
            expand: true,
            cwd: '<%= appConfig.app %>/assets',
            dest: '<%= appConfig.dist %>/assets',
            src: [
              'images/{,*/}*.{webp}',
              'styles/fonts/{,*/}*.*'
            ]
        },{
          expand: true,
          dot: true,
          cwd: '<%= appConfig.app %>',
          dest: '<%= appConfig.dist %>',
          src: [
            '*.{ico,png,txt}',
            '.htaccess',
            '*.html'
          ]
        }, {
          expand: true,
          cwd: '.tmp/assets/images',
          dest: '<%= appConfig.dist %>/assets/images',
          src: ['generated/*']
        }, {
          expand: true,
          cwd: '<%= appConfig.app %>/assets/bower_components/bootstrap/dist',
          src: 'fonts/*',
          dest: '<%= appConfig.dist %>'
        }]
      },
      styles: {
        expand: true,
        cwd: '<%= appConfig.app %>/assets/styles',
        dest: '.tmp/assets/styles/',
        src: '{,*/}*.css'
      }
    },

    // Run some tasks in parallel to speed up the build process
    concurrent: {
      server: [
        'copy:styles'
      ],
      test: [
        'copy:styles'
      ],
      dist: [
        'copy:styles',
        'imagemin',
        'svgmin'
      ]
    },

    ngconstant: {
        options: {
            name: 'restTutorialApp',
            deps: false,
            wrap: '"use strict";\n// DO NOT EDIT THIS FILE, EDIT THE GRUNT TASK NGCONSTANT SETTINGS INSTEAD WHICH GENERATES THIS FILE\n{%= __ngModule %}'
        },
        dev: {
            options: {
                dest: 'src/main/webapp/app/app.constants.js'
            },
            constants: {
                ENV: 'dev',
                VERSION: parseVersionFromPomXml()
            }
        },
        prod: {
            options: {
                dest: '.tmp/app/app.constants.js'
            },
            constants: {
                ENV: 'prod',
                VERSION: parseVersionFromPomXml()
            }
        }
    },

    // Test settings
    karma: {
      unit: {
        configFile: '<%= appConfig.test %>/karma.conf.js',
        singleRun: true
      }
    }
  });


  grunt.registerTask('serve', 'Compile then start a connect web server', function (target) {
    if (target === 'dist') {
      return grunt.task.run(['build', 'connect:dist:keepalive']);
    }

    grunt.task.run([
      'clean:server',
      'wiredep',
      'concurrent:server',
      'autoprefixer:server',
      'connect:livereload',
      'watch'
    ]);
  });

  grunt.registerTask('server', 'DEPRECATED TASK. Use the "serve" task instead', function (target) {
    grunt.log.warn('The `server` task has been deprecated. Use `grunt serve` to start a server.');
    grunt.task.run(['serve:' + target]);
  });

  grunt.registerTask('test', [
    'clean:server',
    'wiredep',
    'ngconstant:dev',
    'concurrent:test',
    'autoprefixer',
    'connect:test',
    'karma'
  ]);

  grunt.registerTask('build', [
    'clean:dist',
    'wiredep',
    'ngconstant:prod',
    'useminPrepare',
    'concurrent:dist',
    'autoprefixer',
    'ngtemplates',
    'concat',
    'ngAnnotate',
    'copy:dist',
    'cdnify',
    'cssmin',
    'uglify',
    'filerev',
    'usemin',
    'htmlmin'
  ]);

  grunt.registerTask('default', [
    'newer:jshint',
    'test',
    'build'
  ]);
};
