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

insert into user_info (username, password, phonenum)values ('nieaowei12345',HEX(AES_ENCRYPT('nieaowei', 'password')),'12722011111');

update user_info set password='123' where username='nieaowei';

delete from user_info where username='nieaowei1';

drop table user_info;

select * from user_info where username='nieaowei1234' and md5(password)=md5('bdc084f13154d81708096482d7d614fa');

select md5(password) from user_info;

create table doc_list(
    dlid bigint primary key auto_increment comment '主键递增',
    name varchar(16) not null comment '文档名',
    path varchar(30) not null comment '存储位置',
    uiid bigint not null comment '文档上传人',
    duration int default 0 not null comment '学习时长',
    createtime timestamp default current_timestamp comment '创建时间',
    updatetime timestamp default current_timestamp on update current_timestamp comment '更新时间',
    FOREIGN KEY (uiid) REFERENCES user_info(uiid) ON DELETE CASCADE
)engine=innodb auto_increment=100 charset = utf8;

create table learn_record(
    lrid bigint auto_increment unique ,
    dlid bigint not null,
    uiid bigint not null,
    duration int default 0 not null ,
    createtime timestamp default current_timestamp comment '创建时间',
    updatetime timestamp default current_timestamp on update current_timestamp comment '更新时间',
    foreign key (uiid) references user_info(uiid) on delete cascade ,
    foreign key (dlid) references doc_list(dlid) on delete cascade,
    primary key (dlid,uiid)

)engine=innodb auto_increment=100 charset = utf8;
drop table learn_record;

select  * from doc_list;
insert into doc_list (name, path, uiid,duration) values('语文','/dsad/ad/asd',111,1000);
insert into doc_list (name, path, uiid,duration) values('语文1','/dsad/ad/asd',111,1000);

insert into learn_record (dlid, uiid,duration) values(101,111,0);
insert into learn_record (dlid, uiid,duration) values(102,111,0);
insert into learn_record (dlid, uiid,duration) values(101,113,0);


insert into learn_record (dlid, uiid,duration) values(101,null,0);

select * from learn_record;