create table manager(
                        id bigint not null auto_increment,
                        pw varchar(50),
                        username varchar(20),
                        email varchar(50),
                        createdate TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP,
                        updatedate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        primary key(id)
);
create table schedule(
                         id bigint not null auto_increment,
                         content varchar(300),
                         createdate TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP,
                         updatedate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         managerId bigint not null,
                         foreign key(managerId) references manager(id) on delete cascade,
                         primary key(id)
);


