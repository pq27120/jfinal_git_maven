create table t_admin_user (
    id bigint not null auto_increment,
    email varchar(255),
    login_name varchar(255) not null unique,
    name varchar(255),
    password varchar(255),
    primary key (id)
)

insert into t_admin_user (email, login_name, name, password) values ('pq@126.com', 'pq', 'pq', 'e10adc3949ba59abbe56e057f20f883e');