PRAGMA foreign_keys = ON;

select load_extension('./uuid');
select load_extension('./closure');



create table if not exists post (
       id integer primary key asc,
       created_at datetime default (datetime('now')) not null,
       public_id uuid unique default (uuid()) not null

);


create table if not exists comment (
       id integer primary key asc,
       created_at datetime default (datetime('now')) not null,
       public_id uuid unique default (uuid()) not null
);
