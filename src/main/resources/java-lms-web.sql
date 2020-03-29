create schema if not exists lms collate latin1_swedish_ci;
use lms;
create table if not exists user_info
(
    uiid bigint auto_increment comment '主键递增'
        primary key,
    username varchar(20) not null comment '用户名',
    password varchar(128) not null comment '用户密码',
    phonenum varchar(11) not null comment '手机号码',
    createtime timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updatetime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    admin tinyint(1) default 0 not null comment '是否管理员',
    constraint phonenum
        unique (phonenum),
    constraint username
        unique (username)
)
    charset=utf8;

create table if not exists doc_list
(
    dlid bigint auto_increment comment '主键递增'
        primary key,
    name varchar(16) not null comment '文档名',
    path varchar(100) null,
    uiid bigint not null comment '文档上传人',
    duration int default 0 not null comment '学习时长',
    createtime timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updatetime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    cover varchar(100) not null comment '封面图片位置',
    constraint doc_list_ibfk_1
        foreign key (uiid) references user_info (uiid)
            on delete cascade
)
    charset=utf8;

create index uiid
    on doc_list (uiid);

create table if not exists learn_record
(
    lrid bigint auto_increment
        primary key,
    dlid bigint not null,
    uiid bigint not null,
    duration int default 0 not null,
    createtime timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updatetime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint dlid
        unique (dlid, uiid),
    constraint learn_record_ibfk_1
        foreign key (uiid) references user_info (uiid)
            on delete cascade,
    constraint learn_record_ibfk_2
        foreign key (dlid) references doc_list (dlid)
            on delete cascade
)
    charset=utf8;

create index uiid
    on learn_record (uiid);

create table if not exists test_info
(
    tiid bigint auto_increment
        primary key,
    title varchar(50) not null comment '试题名',
    uiid bigint not null comment '上传人',
    num int not null comment '题目数量',
    cover varchar(100) not null,
    sum int not null comment '总分',
    createtime timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updatetime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint test_info_ibfk_1
        foreign key (uiid) references user_info (uiid)
            on delete cascade
)
    charset=utf8;

create table if not exists test_content
(
    tcid bigint auto_increment
        primary key,
    tiid bigint not null comment '所属试题',
    question varchar(100) not null comment '题目',
    score int not null comment '分数',
    type int not null comment '类型',
    opa varchar(30) null,
    opb varchar(30) null,
    opc varchar(30) null,
    opd varchar(30) null,
    answer varchar(7) not null,
    createtime timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updatetime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint test_content_ibfk_1
        foreign key (tiid) references test_info (tiid)
            on delete cascade
)
    charset=utf8;

create index tiid
    on test_content (tiid);

create index uiid
    on test_info (uiid);

create table if not exists test_record
(
    trid bigint auto_increment
        primary key,
    tiid bigint not null comment '试卷号',
    uiid bigint not null comment '用户号',
    grade int default 0 not null comment '成绩',
    count int default 2 not null comment '可考试次数',
    createtime timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updatetime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint tiid
        unique (tiid, uiid),
    constraint test_record_ibfk_1
        foreign key (tiid) references test_info (tiid)
            on delete cascade,
    constraint test_record_ibfk_2
        foreign key (uiid) references user_info (uiid)
            on delete cascade
)
    charset=utf8;


create index uiid
    on test_record (uiid);

create table if not exists video_list
(
    vlid bigint auto_increment comment '主键递增'
        primary key,
    name varchar(16) not null,
    path varchar(100) not null comment '存储位置',
    cover varchar(100) not null comment '封面图片位置',
    uiid bigint not null comment '视频上传人',
    duration int default 0 not null comment '学习时长',
    createtime timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updatetime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint video_list_ibfk_1
        foreign key (uiid) references user_info (uiid)
            on delete cascade
)
    charset=utf8;

create index uiid
    on video_list (uiid);

create table if not exists video_record
(
    lrid bigint auto_increment
        primary key,
    vlid bigint not null,
    uiid bigint not null,
    duration int default 0 not null,
    createtime timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updatetime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint vlid
        unique (vlid, uiid),
    constraint video_record_ibfk_1
        foreign key (uiid) references user_info (uiid)
            on delete cascade,
    constraint video_record_ibfk_2
        foreign key (vlid) references video_list (vlid)
            on delete cascade
)
    charset=utf8;

create index uiid
    on video_record (uiid);

