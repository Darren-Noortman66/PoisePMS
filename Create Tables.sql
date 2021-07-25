-- Creating the needed tables and their relationships.
-- Every single table in the databse is connected to the 'Projects' table

create database if not exists PoisePMS;
use PoisePMS;

drop table if exists architect;
CREATE TABLE architect (
                        name varchar(60) primary key,
						telephone varchar(15),
						email_address varchar(70),
						physical_address varchar(85)
) engine = innodb;

drop table if exists contractor;
CREATE TABLE contractor (
                        name varchar(60) primary key,
						telephone varchar(15),
						email_address varchar(70),
						physical_address varchar(85)
) engine = innodb;

drop table if exists customer;
CREATE TABLE customer (
                        name varchar(60) primary key,
						telephone varchar(15),
						email_address varchar(70),
						physical_address varchar(85)
) engine = innodb;

drop table if exists projects;
CREATE TABLE projects (
						project_number int primary key,
						project_name varchar(60),
						deadline date,
                        date_finalised date,
						finalised varchar(3),
						physical_address varchar(85),
						total_fee int,
						total_paid int,
						architect_name varchar(60),
						contractor_name varchar(60),
						customer_name varchar(60),
						FOREIGN KEY (architect_name) REFERENCES architect(name),
						FOREIGN KEY (contractor_name) REFERENCES contractor(name),
						FOREIGN KEY (customer_name) REFERENCES customer(name)
)engine = innodb;


drop table if exists building_info;
CREATE TABLE building_info (
						erd_number int primary key,
                        building_type varchar(85),
                        fk_project_number int,
						FOREIGN KEY (fk_project_number) REFERENCES projects(project_number)
)engine = innodb;

drop table if exists finalised_projects;
CREATE TABLE finalised_projects LIKE projects;

ALTER TABLE finalised_projects
  ADD erd_number int AFTER physical_address, ADD building_type varchar(85) AFTER erd_number;
  
ALTER TABLE finalised_projects
  ADD architect_telephone varchar(15) AFTER architect_name, 
  ADD architect_email_address varchar(70) AFTER architect_telephone, 
  ADD architect_physical_address varchar(85) AFTER architect_email_address;

ALTER TABLE finalised_projects
  ADD contractor_telephone varchar(15) AFTER contractor_name, 
  ADD contractor_email_address varchar(70) AFTER contractor_telephone, 
  ADD contractor_physical_address varchar(85) AFTER contractor_email_address;
    
ALTER TABLE finalised_projects
  ADD customer_telephone varchar(15) AFTER customer_name, 
  ADD customer_email_address varchar(70) AFTER customer_telephone, 
  ADD customer_physical_address varchar(85) AFTER customer_email_address;    
