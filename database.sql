--create table users (
--    id_user varchar (255) primary key not null,
--    username varchar(255) not null ,
--    email varchar(255) not null,
--    password varchar(255) not null,
--    token varchar(255) unique,
--    token_expired_at bigint
--)engine InnoDB;

-- create table pertanyaan (
--    id_pertanyaan varchar(255) primary key not null,
--    header text not null ,
--    deskripsi text not null,
--    suka int
-- )engine InnoDB;

--create table komentar  (
--    id_komentar varchar(255) primary key not null,
--    deskripsi text not null ,
--    tanggal timestamp
--)engine InnoDB;
create table store_gambar (
    id_gambar varchar(255) primary key not null,
    nama_gambar varchar(255) not null ,
    path varchar(255) not null,
    ext varchar(255) not null,
    tanggal timestamp not null
)engine InnoDB;


--insert into users (username , email , password)values (
--    "esa" , "esa@gmail.com" , "esa123"
--);
--select * from pertanyaan;