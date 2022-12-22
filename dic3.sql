
create table users (
    id int not null auto_increment,
    username varchar(255),
    password varchar(255),
    primary key (id)
) engine=innodb;

insert into users (username, password) values ('admin', 'admin'), ('paul', 'passer123'), ('job', 'job123');