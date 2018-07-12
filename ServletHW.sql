
drop database Servlet_HW;

create database Servlet_HW;
go

use Servlet_HW;
go


create table Author(
	Author_ID  int identity primary key,
	Name_zh  varchar(100) not null, 
	Name_ori  varchar(100) not null,
	nickName  varchar(50),
	Intro_Author  varchar(2000),
	Intro_Translator  varchar(2000)

);


create table Product(
	Product_ID  int identity primary key,
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

--資料1
insert into Product (Book_ISBN,product_name,Book_name_zh,Book_name_ori,Author_zh,Author_ori ,Translator_zh ,Translator_ori,Book_lang ,Book_publisher,
					Book_publish_date,Book_price,Book_stockNum,Book_series,Book_pages ,Book_version,Book_size,Book_weight,Product_img,Book_skin ,book_menu ,
					book_intro,Book_tag )
values('123456789','第一本書','第一本書中文名','第一本書原文名','作者中文名','作者原文名','譯者中文名','譯者原文名','語言','出版社' ,'2018/7/10',
		200,10,'系列1',150000 ,'v1','10*3*5mm',80,
	(SELECT BulkColumn from Openrowset ( Bulk 'D:\bk1_210x315.jpg', Single_Blob) as Product_img) ,
	'平裝','我是menu','我是書籍介紹','我是標籤' );
--資料2
insert into Product (Book_ISBN,product_name,Book_name_zh,Book_name_ori,Author_zh,Author_ori ,Translator_zh ,Translator_ori,Book_lang ,Book_publisher,
					Book_publish_date,Book_price,Book_stockNum,Book_series,Book_pages ,Book_version,Book_size,Book_weight,Product_img,Book_skin ,book_menu ,
					book_intro,Book_tag )
values('223456789','第2本書','第2本書中文名','第2本書原文名','作者2中文名','作者2原文名','譯者2中文名','譯者2原文名','語言2','出版社2' ,'2018/7/20',
		202,12,'系列2',120000 ,'v2','10*2*5mm',22,
	(SELECT BulkColumn from Openrowset ( Bulk 'D:\bk1_210x315.jpg', Single_Blob) as Product_img) ,
	'平裝2','我是menu2','我是書籍介紹2','我是標籤2' );
--資料3
insert into Product (Book_ISBN,product_name,Book_name_zh,Book_name_ori,Author_zh,Author_ori ,Translator_zh ,Translator_ori,Book_lang ,Book_publisher,
					Book_publish_date,Book_price,Book_stockNum,Book_series,Book_pages ,Book_version,Book_size,Book_weight,Product_img,Book_skin ,book_menu ,
					book_intro,Book_tag )
values('323456789','第3本書','第3本書中文名','第3本書原文名','作者3中文名','作者3原文名','譯者3中文名','譯者3原文名','語言3','出版社3' ,'2018/7/30',
		202,12,'系列3',13000 ,'v3','10*3*5mm',23,
	(SELECT BulkColumn from Openrowset ( Bulk 'D:\bk1_210x315.jpg', Single_Blob) as Product_img) ,
	'平裝3','我是menu3','我是書籍介紹3','我是標籤3' );


go  

select* from Product

