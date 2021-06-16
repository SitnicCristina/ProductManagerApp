CREATE DATABASE weather
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
create table weatherForecast(
	city varchar(12),
	dateForecast date,
	precipitation float,
	wint float,
	temperature int
);

insert into weatherForecast (city, dateForecast, precipitation, wint, temperature) 
	values ('Chisinau',CURRENT_DATE, 0.55, 5.5, 21)  ;

insert into weatherForecast (city, dateForecast, precipitation, wint, temperature) 
	values ('Balti',CURRENT_DATE, 1.55, 5.4, 19);
	
insert into weatherForecast (city, dateForecast, precipitation, wint, temperature) 
	values ('Cahul',CURRENT_DATE, 1.45, 0.4, 25);
	
insert into weatherForecast (city, dateForecast, precipitation, wint, temperature) 
	values ('Ungheni',CURRENT_DATE, 0.5, 5.3, 23);
	
select * from weatherForecast;

select * from weatherForecast where temperature > 20;

select city,temperature from weatherForecast order by temperature