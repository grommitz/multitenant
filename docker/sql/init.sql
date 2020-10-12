create database client1db;
create database client2db;
create database client3db;

use client1db;
create table Person (
                        id int not null auto_increment primary key,
                        name varchar(255),
                        lastName varchar(255));

use client2db;
create table Person (
                        id int not null auto_increment primary key,
                        name varchar(255),
                        lastName varchar(255));

use client3db;
create table Person (
                        id int not null auto_increment primary key,
                        name varchar(255),
                        lastName varchar(255));

insert into client1db.Person (name, lastName) values("Dan", "Marino");
insert into client1db.Person (name, lastName) values("Joe", "Montana");
insert into client1db.Person (name, lastName) values("Brett", "Favre");