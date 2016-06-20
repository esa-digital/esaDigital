'use strict';

var gulp = require('gulp');
var sass = require('gulp-sass');

var repo_root = __dirname + '/';

var assets = '../esa-digital/src/main/webapp/resources/assets/';

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
