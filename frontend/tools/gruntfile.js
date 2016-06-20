module.exports = function(grunt) {

    var viewPath = '../../esa-digital/src/main/webapp/WEB-INF/views/';
    var propertiesPath = '../../esa-digital/src/main/resources/';
    var jsonPath = '../../esa-digital/src/main/webapp/WEB-INF/views/json/';

    // Project configuration.
    grunt.initConfig({
        freemarker: {
            options: {
                views: viewPath,
                out: 'output'
            },
            src: "model/*.js"
        },

        watch: {
            scripts: {
                files: [viewPath + '**/*.ftl'],
                tasks: ['freemarker'],
                options: {
                    spawn: false,
                },
            },
        },

        propertiesToJSON: {
            main: {
                src: [propertiesPath + 'personal-details.properties'],
                dest: jsonPath
            }
        }
    });

    grunt.loadNpmTasks('grunt-freemarker');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-properties-to-json');

    grunt.registerTask('default', ['freemarker', 'propertiesToJSON']);

};
