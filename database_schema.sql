PRAGMA foreign_keys = ON;

select load_extension('./uuid');
select load_extension('./closure');

create table if not exists tag (
       id integer primary key,
       created_at datetime default (datetime('now')) not null,
       last_updated_at datetime default (datetime('now')) not null,
       public_id uuid unique default (uuid()) not null,
       slug text unique,
       name text
);


create table if not exists author (
       id integer primary key,
       created_at datetime default (datetime('now')) not null,
       last_updated_at datetime default (datetime('now')) not null,
       public_id uuid unique default (uuid()) not null,
       name text unique,
       author_email text unique
);

create table if not exists article (
       id integer primary key asc,
       created_at datetime default (datetime('now')) not null,
       last_updated_at datetime default (datetime('now')) not null,
       public_id uuid unique default (uuid()) not null,
       title text,
       content text,
       author_id integer references author
);

create table if not exists article_tag (
       id integer primary key asc,
       article_id integer references article(id),
       tag_id integer references tag
);


create table if not exists comment (

       id integer primary key asc,
       created_at datetime default (datetime('now')) not null,
       last_updated_at datetime default (datetime('now')) not null,
       public_id uuid unique default (uuid()) not null,

       content text,
       article_id integer references article(id),
       parent_id integer references comment,
       author_id integer references author
);


-- TODO Full text

create index comment_idx1 on comment(parent_id);

create virtual table comment_hierarchy using transitive_closure(
       tablename='comment',
       idcolumn='id',
       parentcolumn='parent_id'
);
