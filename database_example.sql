.mode box
.changes on
.log on

PRAGMA foreign_keys = ON;

select load_extension('./uuid');
select load_extension('./closure');


BEGIN TRANSACTION;




COMMIT;
