create database sklad;
use sklad;

create table provider(
	id int(11) not null AUTO_INCREMENT,
	name varchar(255),
	adres varchar(255),
	phone varchar(255),
	email varchar(255),
	primary key(id)
);

INSERT INTO provider(id, name, adres,phone,email) VALUES
(1,'Поставщик 1','космонавтов 25','88005553535','one@yandex.ru'),
(2,'Поставщик 2','ломоносова 72','80295487998','two@mail.ru'),
(3,'Поставщик 3','брехатино 228','80442184914','three@google.com'),
(4,'Поставщик 4','липелова 89','80254897651','Four@yahoo.de'),
(5,'Поставщик 5','кузьминино 351','80335487618','F@list.ru');

create table product(
	id int(11) not null AUTO_INCREMENT,
	name varchar(255),
	price int ,
	primary key(id)	
);

INSERT INTO product(id, name,price) VALUES 
(1,'Молоко',200),
(2,'Спирт',998),
(3,'ПК',2600),
(4,'Стол березовый',50),
(5,'Ручки синие',1);

create table delivery(
	id int(11) not null AUTO_INCREMENT,
	primary key(id),    
    id_product int not null,
    id_provider int not null,	
	dates date,
	count int not null,
	foreign key (id_product) references product(id),
    foreign key (id_provider) references provider(id)
);
INSERT INTO delivery(id,id_product,id_provider,dates,count) VALUES
(1,1,5,'2020-10-01',20),
(2,2,4,'2020-10-02',40),
(3,3,3,'2020-10-03',8),
(4,4,2,'2020-10-06',16),
(5,5,1,'2020-10-06',56);

create table warehouse(
	id int(11) not null AUTO_INCREMENT,
	id_product int not null,
    count int not null,
	primary key (id),
	foreign key (id_product) references product(id)
);
INSERT INTO warehouse(id,id_product,count) VALUES 
(1,1,56),
(2,2,16),
(3,3,8),
(4,4,40),
(5,5,20);
create table employee(
	id int(11) not null AUTO_INCREMENT,
	primary key(id),
	fio varchar(255) not null,
	phone varchar(255) not null,
	email varchar(255) not null
);
INSERT INTO employee(id,fio,phone,email) VALUES
(1,'Иван Васильевич Грозный','88005553535','one@mail.ru'),
(2,'Корней Минатович Тутенко','808005553668','two@yandex.ru'),
(3,'Михаил петрович Гарусь','808005554777','tree@google.ru');


create table client(
	id int(11) not null AUTO_INCREMENT,
	primary key(id),
	fio varchar(255) not null,
	phone varchar(255) not null,
	email varchar(255) not null
);
INSERT INTO client(id,fio,phone,email) VALUES
(1,'Будько Анна Семенович','88005453535','one2@mail.ru'),
(2,'Кагура Андрей Боярский','808005562668','two3@yandex.ru'),
(3,'Вдовец Александр Васильевич','808005558277','tree1@google.ru');

create table orders(
	id int(11) not null AUTO_INCREMENT,
	primary key(id),
	id_employee int not null,
	id_client int not null,
	id_product int not null,
	count int not null,
	dates date,
	foreign key (id_employee) references employee(id),
	foreign key (id_client) references client(id),
	foreign key (id_product) references product(id)
);
INSERT INTO orders(id,id_employee,id_client,id_product,count,dates) VALUES
(1,1,3,1,2,'2020-10-07'),
(2,2,2,2,2,'2020-10-07'),
(3,3,1,3,2,'2020-10-10'),
(4,1,3,4,3,'2020-10-11'),
(5,2,2,5,3,'2020-10-13');