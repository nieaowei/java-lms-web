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

insert into user_info (username, password, phonenum)values ('nieaowei',md5('nieaowei'),'12222012111');

update user_info set password='123' where username='nieaowei';

delete from user_info where username='nieaowei1';

drop table user_info;

select * from user_info where username='nieaowei1234' and md5(password)=md5('bdc084f13154d81708096482d7d614fa');

select md5(password) from user_info;

select d.dlid,d.name,d.duration doc_duration,l.duration from doc_list d ,learn_record l where l.dlid = d.dlid and l.uiid=103;

create table doc_list(
    dlid bigint primary key auto_increment comment '主键递增',
    name varchar(16) not null comment '文档名',
    path varchar(100) not null comment '存储位置',
    cover varchar(100) not null comment '封面图片位置',
    uiid bigint not null comment '文档上传人',
    duration int default 0 not null comment '学习时长',
    createtime timestamp default current_timestamp comment '创建时间',
    updatetime timestamp default current_timestamp on update current_timestamp comment '更新时间',
    FOREIGN KEY (uiid) REFERENCES user_info(uiid) ON DELETE CASCADE
)engine=innodb auto_increment=100 charset = utf8;

alter table doc_list modify column path varchar(100);
alter table doc_list add column cover varchar(100) not null comment '封面图片位置';

create table learn_record(
    lrid bigint auto_increment primary key ,
    dlid bigint not null ,
    uiid bigint not null ,
    duration int default 0 not null ,
    createtime timestamp default current_timestamp comment '创建时间',
    updatetime timestamp default current_timestamp on update current_timestamp comment '更新时间',
    foreign key (uiid) references user_info(uiid) on delete cascade ,
    foreign key (dlid) references doc_list(dlid) on delete cascade,
    constraint unique (dlid,uiid)
)engine=innodb auto_increment=100 charset = utf8;

drop table learn_record;

select  * from doc_list;
update doc_list set duration=6666 where dlid=106;
insert into doc_list (name, path, uiid,duration) values('轮刘军民是不是傻逼','/dsad/ad/asd',103,1000);
insert into doc_list (name, path, uiid,duration) values('语文1','/dsad/ad/asd',111,1000);
insert into doc_list (name, path, uiid,duration) values('数学','/dsad/ad/asd',103,1000);


insert into learn_record (dlid, uiid,duration) values(101,133,0);
insert into learn_record (dlid, uiid,duration) values(102,133,0);
insert into learn_record (dlid, uiid,duration) values(104,133,0);

insert into learn_record (dlid, uiid,duration) values(106,103,500);
insert into learn_record (dlid, uiid,duration) values(101,113,0);

insert into learn_record (dlid, uiid) values(104,103);


insert into learn_record (dlid, uiid,duration) values(101,null,0);

select * from learn_record;

update learn_record set duration=899 where lrid=100;

delete from learn_record where lrid > 100;

select * from doc_list where dlid=103;


create table video_list(
                         vlid bigint primary key auto_increment comment '主键递增',
                         name varchar(16) not null comment '文档名',
                         path varchar(100) not null comment '存储位置',
                         cover varchar(100) not null comment '封面图片位置',
                         uiid bigint not null comment '视频上传人',
                         duration int default 0 not null comment '学习时长',
                         createtime timestamp default current_timestamp comment '创建时间',
                         updatetime timestamp default current_timestamp on update current_timestamp comment '更新时间',
                         FOREIGN KEY (uiid) REFERENCES user_info(uiid) ON DELETE CASCADE
)engine=innodb auto_increment=100 charset = utf8;

insert into video_list (name, path, cover, uiid) VALUES ('java入门','/dds','/asdasd',103);
select * from video_list;

create table video_record(
                             lrid bigint auto_increment primary key ,
                             vlid bigint not null ,
                             uiid bigint not null ,
                             duration int default 0 not null ,
                             createtime timestamp default current_timestamp comment '创建时间',
                             updatetime timestamp default current_timestamp on update current_timestamp comment '更新时间',
                             foreign key (uiid) references user_info(uiid) on delete cascade ,
                             foreign key (vlid) references video_list(vlid) on delete cascade,
                             constraint unique (vlid,uiid)
)engine=innodb auto_increment=100 charset = utf8;

select * from video_record;

drop table video_record;

create table test_info(
    tiid bigint auto_increment primary key ,
    title varchar(50) not null comment '试题名',
    uiid bigint not null comment '上传人',
    num int not null comment '题目数量',
    createtime timestamp default current_timestamp comment '创建时间',
    updatetime timestamp default current_timestamp on update current_timestamp comment '更新时间',
    foreign key (uiid) references user_info(uiid) on delete cascade

)engine=innodb auto_increment=100 charset = utf8;

drop table test_info;

create table test_content(
    tcid bigint auto_increment primary key ,
    tiid bigint not null comment '所属试题',
    question varchar(100) not null comment '题目',
    score int not null comment '分数',
    opa varchar(30) not null ,
    opb varchar(30) not null ,
    opc varchar(30) not null ,
    opd varchar(30) not null ,
    answer int  CHECK ( answer in (1,2,3,4)) ,
    foreign key (tiid) references test_info(tiid) on delete cascade ,
    createtime timestamp default current_timestamp comment '创建时间',
    updatetime timestamp default current_timestamp on update current_timestamp comment '更新时间'
)engine=innodb auto_increment=100 charset = utf8;

drop table test_content;

create table test_record(
    trid bigint auto_increment primary key ,
    tiid bigint not null comment '试卷号',
    grade int not null default 0 comment '成绩',
    foreign key (tiid) references test_info(tiid) on delete cascade
)engine = innodb auto_increment=100 charset = utf8;