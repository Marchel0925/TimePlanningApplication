CREATE DATABASE IF NOT EXISTS TimePlanningApplication;
USE TimePlanningApplication;
drop table dateTable, timeTable, detailsTable;
drop database TimePlanningApplication;

CREATE TABLE dateTable(id INTEGER NOT NULL AUTO_INCREMENT
				       ,startDate DATE NOT NULL
					   ,endDate DATE DEFAULT NULL
				       ,PRIMARY KEY(id)
				       );
                                 
CREATE TABLE timeTable(id INTEGER NOT NULL AUTO_INCREMENT
                      ,startTime TIME NOT NULL
                      ,endTime TIME NOT NULL
					  ,dateId INTEGER NOT NULL
					  ,PRIMARY KEY(id)
					  ,FOREIGN KEY (dateId) REFERENCES dateTable(id)
					  );
						
CREATE TABLE detailsTable(id INTEGER NOT NULL AUTO_INCREMENT
                         ,location VARCHAR(100) DEFAULT NULL
                         ,description VARCHAR(2000)
                         ,timeId INTEGER NOT NULL
					     ,PRIMARY KEY(id)
					     ,FOREIGN KEY (timeId) REFERENCES timeTable(id)
                         );
                         
INSERT INTO dateTable(startDate) VALUES ("2020-09-25");