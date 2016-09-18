'use strict';

var
    angularFilesort = require('gulp-angular-filesort'),
    es = require('event-stream'),
    gulp = require('gulp'),
    inject = require('gulp-inject'),
    runSequence = require('run-sequence');

var config = {
    app: 'src/main/webapp/'
};

gulp.task('build', [], function (cb) {
    runSequence('inject', cb);
});

gulp.task('inject', function () {
    return gulp.src(config.app + 'index.html')
        .pipe(inject(es.merge(
            gulp.src(
                [config.app + 'app/**/*.css', config.app + 'assets/css/**.css'],
                { read: false }
            ),
            gulp.src(config.app + 'app/**/*.js').pipe(angularFilesort())
        ), { relative: true }))
        .pipe(gulp.dest(config.app));
});
