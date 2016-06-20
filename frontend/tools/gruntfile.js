module.exports = function(grunt) {

  var viewPath = '../../esa-digital/src/main/webapp/WEB-INF/views';
  
  // Project configuration.
  grunt.initConfig({
    freemarker: {
      options: {
        views : viewPath,
        out : 'output'
      },
      src: "model/*.js"
    }
  });

  grunt.loadNpmTasks('grunt-freemarker');

  grunt.registerTask('default', ['freemarker']);

};
