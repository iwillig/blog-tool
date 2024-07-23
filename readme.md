# Blog Tool

A simple and easy blogging tool.

Built using Clojure, SQLite3 and htmlx

## Tests

```shell
make check
```

## Development Tools

### make rebuild-db

Rebuild the database schema locally

```shell
##
make rebuild-db
##
watchman-make -p database_example.sql database_schema.sql 'Makefile' -t rebuild-db
```

### make clj-lint

## Run

```shell
```
