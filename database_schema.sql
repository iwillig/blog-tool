PRAGMA foreign_keys = ON;

select load_extension('./uuid');
select load_extension('./closure');

create table author (
       id integer primary key,
       created_at datetime default (datetime('now')) not null,
       last_updated_at datetime default (datetime('now')) not null,
       public_id uuid unique default (uuid()) not null,
       name text unique,
       author_email text unique
);

create table if not exists post (
       id integer primary key asc,
       created_at datetime default (datetime('now')) not null,
       last_updated_at datetime default (datetime('now')) not null,
       public_id uuid unique default (uuid()) not null,
       title text,
       content text,
       author_id integer references author
);

create table if not exists comment (
       id integer primary key asc,
       created_at datetime default (datetime('now')) not null,
       last_updated_at datetime default (datetime('now')) not null,
       public_id uuid unique default (uuid()) not null,
       content text,
       post_id integer references post,
       parent_id integer references comment,
       author_id integer references author
);

create index comment_idx1 on comment(parent_id);

create virtual table comment_hierarchy using transitive_closure(
       tablename='comment',
       idcolumn='id',
       parentcolumn='parent_id'
);
