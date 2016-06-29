'use strict';

var gulp = require('gulp');
var sass = require('gulp-sass');
var concat = require('gulp-concat');

var repo_root = __dirname + '/';

var assets = 'main/webapp/resources/assets/';
var toolkit = './node_modules/govuk_frontend_toolkit/';

// Compile scss files to css
gulp.task('styles', function() {
	return gulp.src('./scss/style.scss')
        .pipe(sass({
            includePaths: [
            	'node_modules'
            ]
        }).on('error', sass.logError))
        .pipe(gulp.dest(assets + 'stylesheets'));
});

// package required js files
gulp.task('scripts', function() {
	return gulp.src(['./js/vendor/jquery-1.11.3.js',
                     toolkit+'javascripts/govuk/selection-buttons.js',
					 './js/**/*.js'])
        .pipe(concat('application.js'))
        .pipe(gulp.dest(assets + 'javascripts'));
});

gulp.task('default', ['styles', 'scripts']);