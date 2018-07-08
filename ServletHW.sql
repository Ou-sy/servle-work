
create database Servlet_HW;
go

use Servlet_HW;
go


create table Author(
	Author_ID  int not null primary key,
	Name_zh  varchar(100) not null, 
	Name_ori  varchar(100) not null,
	nickName  varchar(50),
	Intro_Author  varchar(2000),
	Intro_Translator  varchar(2000)

);


create table Product(
	Product_ID  int  not null primary key,
	Book_ISBN  varchar(20)  null,
	product_name  varchar(50) not null,
	Book_name_zh   varchar(100) null,
	Book_name_ori   varchar(100) null,
	Author_zh   varchar(100) null ,
	Author_ori varchar(100)  null,
	Translator_zh   varchar(100)  null,
	Translator_ori  varchar(100)  null,
	Book_lang   varchar(20)  null,
	Book_publisher  varchar(50) null,
	Book_publish_date  date null,
	Book_price  int not null,
	Book_stockNum int null,
	Book_series  varchar(50) null,
	Book_pages   int not null,
	Book_version  varchar(20) null,
	Book_size    varchar(20) null,
	Book_weight  int null,
	Product_img varbinary(max) null,
	Book_skin   varchar (10) null,
	book_menu  varchar (1000) null,
	book_intro   varchar (MAX) null,
	Book_tag varchar(400) null,

	E_book_file  varbinary(max)
);

create table Member(
	Member_ID int identity primary key not null,
	Member_account nVarchar(50) not null,
	Member_password nVarchar(50) not null,
	Member_name nVarchar(50) not null,
	Member_nickname nVarchar(50) ,
	Member_gender nVarchar(10) not null,
	Member_phone nVarchar(50) not null,
	Member_address nVarchar(100),
	Member_email nVarchar(100)not null,
	Member_email2 nVarchar(100),
	Member_createDate nVarchar(50) not null,
	Member_lastLoginTime nVarchar(50) not null, 
);

create table Pd_order(
	Order_ID int identity primary key,
	Member_ID int references Member,
	Order_content nVarchar(MAX) Not null,
	Order_count Int Not null ,
	Order_amount Int Not null ,
	Order_discount Varchar(10),
);


create table OrderProduct(
	Order_ID  Int Not null foreign key references Pd_Order ( Order_ID) ,
	Product_ID  int identity  not null foreign key references Product ( Product_ID)

);


create table ProductAuthor(
	Author_ID  int not  null foreign key references Author(Author_ID),
	Product_ID  int   not null foreign key references Product (Product_ID)
);