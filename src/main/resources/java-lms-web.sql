create database lms;
use lms;
create table user_info(
    uiid BIGINT(20) primary key auto_increment comment '主键递增',
    username varchar(20) not null unique comment'用户名',
    password varchar(128) not null comment '用户密码',
    phonenum varchar(11) not null unique comment '手机号码',
    createtime timestamp default current_timestamp comment '创建时间',
    updatetime timestamp default current_timestamp on update current_timestamp comment '更新时间'
)engine=innodb auto_increment=100 charset = utf8;

select * from user_info;

insert into user_info (username, password, phonenum)values ('nieaowei',md5('nieaowei'),'13701011111');

update user_info set password='123' where username='nieaowei';

delete from user_info where username='nieaowei1';

drop table user_info;

select * from user_info where username='nieaowei' and password=md5('nieaowei');