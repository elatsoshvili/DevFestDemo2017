create table reviews (
    id int not null auto_increment,
    email varchar(100) not null,
    review text,
    stars int not null,
    PRIMARY KEY(id)
);
