create table defer_log(
    id int(8) auto_increment not null primary key,
    remark varchar(4000) not null comment '备注',
    time datetime comment '时间'
)
comment '日志表';