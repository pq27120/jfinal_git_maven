-- 宿舍人员表
create table user (
  id       int(8) auto_increment not null primary key,
  name     varchar(20)           not null
  comment '名字',
  time datetime              not null
  comment '最后编辑时间'
)
  comment '宿舍人员表';

-- 缴费/开销表
create table pay (
  id      int(8) auto_increment not null primary key,
  remark  varchar(4000)         not null
  comment '备注',
  time    datetime              not null
  comment '缴费/开销时间',
  amount  numeric(5)            not null
  comment '缴费/开销金额',
  user_id int(8)                not null
  comment '用户id',
  flag    char(1)               not null
  comment '0 缴费 1 开销 2 住宿费（5元/天）'
)
  comment '缴费/开销表';