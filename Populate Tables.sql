 -- Insert-Statements for populating tables..

insert into architect values ('Darren Noortman', '083 952 8453', 'DarrenNoortman@gmail.com', '29 Blourans Drive'),
							 ('Ricky Bobby', '083 952 7955', 'RickyBobby@gmail.com', '22 Dash Niner Niner');

insert into contractor values ('Jack Black', '083 542 5456', 'JackBlack@gmail.com', '10 William Drive'),
							  ('Ashton Kutcher', '083 952 7955', 'AshtonKutche@gmail.com', '8 William Nicol');
                             
insert into customer values ('Beyonce Unknown', '083 101 7787', 'BeyonceUnknown@gmail.com', '66 Vipers Voad'),
							('Matt Damon', '083 778 4897', 'MattDamony@gmail.com', '4 Dorene Road');					
                             
insert into projects values (1, 'Projects Mayhem', '2021-11-15', '2021-12-20', 'No', '123 Areal Road', 150000, 50000, 'Darren Noortman', 'Jack Black', 'Beyonce Unknown'),
							(2, 'Projects Bronco', '2021-09-10', '2021-09-07', 'No', '88 Cresendo Lane', 300000, 120000, 'Ricky Bobby', 'Ashton Kutcher', 'Matt Damon');							
                        
insert into building_info values ('10265520', 'Apartment', 1),
								 ('25648555', 'House', 2);
