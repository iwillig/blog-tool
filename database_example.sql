.mode box
.changes on
.log on

PRAGMA foreign_keys = ON;

select load_extension('./uuid');
select load_extension('./closure');

begin transaction;

insert into tag (id, name, slug)
values
(1, 'Engineering', 'engineering'),
(2, 'Clojure', 'clojure'),
(3, 'Hypermedia', 'hypermedia')
returning *;



insert into author (id, name, author_email)
values (1, 'ivan', 'ivan@example.com')
returning *;

insert into article (id, title, content, author_id)
values (1, 'How to build a blog software', '....', 1)
returning *;


insert into article_tag (article_id, tag_id)
values (1, 1),
       (1, 2),
       (1, 3)
returning *;

insert into comment (id, content, article_id, author_id, parent_id)
values
(1, 'This is a comment', 1, 1, Null),
(2, 'This is a reply to that the first comment', 1, 1, 1)
returning *;





commit;
