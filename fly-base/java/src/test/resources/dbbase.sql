drop database if exists sys;

create database sys character set utf8;

GRANT ALL PRIVILEGES ON sys.* TO sys@localhost IDENTIFIED BY 'sys';