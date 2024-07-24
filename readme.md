# Blog Tool

A simple and easy hypermedia blogging tool.

Built using Clojure, SQLite3 and htmlx

## Clojure development

Load a REPL how you do normally. You can use `make repl` if you don't
have a preferred way of starting a REPL.

Then compile all the namespaces.

```clojure
user> (dev)

dev> (reload/reload)

dev> (k/run-all)
```

## Tests
Run the tests via the command line.

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
