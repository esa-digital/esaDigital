# ESA Digital

(overview)

## Development

### System requirements and setup

* JDK 1.8
* Maven
* Eclipse
* Vagrant (1.8.1+)
* Virtualbox

#### Eclipse

### Build

Build project in Eclipse (Right-click project > Run As ... > Maven install)

### Run

```
vagrant up
vagrant ssh
sudo -i
systemctl stop tomcat
cp /vagrant/esa-digital/target/dwp-*.war /usr/share/tomcat/webapps/esa.war
systemctl start tomcat
```

For convenience, the bundled `tools/watch` script will watch the compiled JAR file and automatically restart the service whenever it is rebuilt. Suggest running this in its own `screen`:

```
screen -S watch
cd /vagrant
./tools/watch
```

To quit watching press `Ctrl+\`. To exit the screen session press `Ctrl+A` then `D`. To reattach to the screen session `screen -r keyservicewatch`.

### Test

### Deploy



