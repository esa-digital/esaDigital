# Frontend tools

grunt module for compiling freemarker templates
	
create symlink of the assets folder in the app to the output folder for the tool:

	ln -s esa-digital/src/main/webapp/resources/assets/ frontend/tools/output/assets

install required modules:

	npm install

the default task will compile all templates to html in the output folder:

	grunt