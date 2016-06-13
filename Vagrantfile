# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure(2) do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://atlas.hashicorp.com/search.
  config.vm.box = "CentOS-7.1.1503-x86_64-netboot"
  config.vm.box_url = "https://github.com/holms/vagrant-centos7-box/releases/download/7.1.1503.001/CentOS-7.1.1503-x86_64-netboot.box"

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  config.vm.network "private_network", ip: "192.168.20.11"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  config.vm.synced_folder "./esa-digital/", "/vagrant"

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  config.vm.provider "virtualbox" do |vb|
    # Display the VirtualBox GUI when booting the machine
    vb.gui = false
  
    # Customize the amount of memory on the VM:
    vb.memory = "1024"
  end

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.
  config.vm.provision "shell", inline: <<-SHELL
# Install dependencies
sudo -i
yum -y update

# Install nginx
yum -y install epel-release
yum -y install nginx
systemctl enable nginx

# Install Tomcat
# The YUM repo contains v7, but we want v8.
# Here we also setup an "admin" user (pw "admin") to manage the server from GUIs
yum -y install java-1.8.0-openjdk
cd /opt
wget http://www.us.apache.org/dist/tomcat/tomcat-8/v8.0.35/bin/apache-tomcat-8.0.35.tar.gz
tar xzf apache-tomcat-8.0.35.tar.gz
echo "export CATALINA_HOME="/opt/apache-tomcat-8.0.35"" >> ~/.bashrc
source ~/.bashrc
cd /opt/apache-tomcat-8.0.35
cp conf/tomcat-users.xml conf/tomcat-users.xml.backup
echo "<?xml version='1.0' encoding='utf-8'?>
<tomcat-users xmlns='http://tomcat.apache.org/xml'
              xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
              xsi:schemaLocation='http://tomcat.apache.org/xml tomcat-users.xsd'
              version='1.0'>
<!-- user manager can access only manager section -->
<role rolename='manager-gui' />
<role rolename='admin-gui' />
<user username='admin' password='admin' roles='manager-gui,admin-gui' />
</tomcat-users>
" > conf/tomcat-users.xml
./bin/startup.sh

# Allow HTTP(S) through firewall
# Opening Tomcat port (8080) for dev purposes, but production would not open it
firewall-cmd --permanent --zone=public --add-service=http
firewall-cmd --permanent --zone=public --add-service=https
firewall-cmd --permanent --zone=public --add-port=8080/tcp
firewall-cmd --reload

# Configure nginx to upstream requests to Tomcat

SHELL
end
