create table users (
    id_user varchar (255) primary key not null,
    username varchar(255) not null ,
    email varchar(255) not null,
    password varchar(255) not null,
    token varchar(255) unique,
    token_expired_at bigint
)engine InnoDB;

--insert into users (username , email , password)values (
--    "esa" , "esa@gmail.com" , "esa123"
--);
