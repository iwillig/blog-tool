
.DEFAULT_GOAL := check

UNAME = $(shell uname -s)

DATABASE_FILE  = database.db
SQL_INIT       = database_schema.sql

UUID_MODULE    =
CLOSURE_MODULE =
GCC_INCLUDED   =
DYANMIC_LIB    =

ifeq ($(UNAME), Linux)
	UUID_MODULE = uuid.so
endif

ifeq ($(UNAME), Darwin)
	UUID_MODULE = uuid.dylib
endif


ifeq ($(UNAME), Linux)
	CLOSURE_MODULE = closure.so
endif

ifeq ($(UNAME), Darwin)
	CLOSURE_MODULE = closure.dylib
endif

ifeq ($(UNAME), Darwin)
	GCC_INCLUDED   =  -I/opt/homebrew/opt/sqlite/include
endif

ifeq ($(UNAME), Darwin)
	DYANMIC_LIB = -dynamiclib
endif

ifeq ($(UNAME), Linux)
	DYANMIC_LIB = -shared
endif

$(info "uname value" $(UNAME) $(UUID_MODULE) $(CLOSURE_MODULE))

$(UUID_MODULE):
	gcc -g $(GCC_INCLUDED) -fPIC $(DYANMIC_LIB) sqlite-ext/uuid.c -o $(UUID_MODULE)

$(CLOSURE_MODULE):
	gcc -g $(GCC_INCLUDED) -fPIC $(DYANMIC_LIB) sqlite-ext/closure.c -o $(CLOSURE_MODULE)

$(DATABASE_FILE): $(UUID_MODULE) $(CLOSURE_MODULE) database_schema.sql database_example.sql

	sqlite3 $(DATABASE_FILE) < $(SQL_INIT)
	sqlite3 $(DATABASE_FILE) < database_example.sql

clean:
	-rm -rf $(BUILDDIR)/html
	-rm database.db
	-rm closure.dylib
	-rm $(UUID_MODULE)
	-rm -r closure.dylib.dSYM/
	-rm -r uuid.dylib.dSYM/
	-rm -r target/
	-rm -r build/
	-rm junit.xml

rebuild-db: clean $(DATABASE_FILE)

watch-db:
	watchman-make -p database_example.sql database_schema.sql 'Makefile' -t rebuild-db

watch-tests:
	watchman-make -p database_example.sql database_schema.sql '**/*.clj' 'src/**/*.clj' 'test/**/*.clj' 'Makefile' -t tests

doc-api:
	clojure -X:codox

doc: doc-api

outdated:
	clojure -M:outdated

clj-lint:
	clj -M:lint --lint src

lint: clj-lint

tests: clean $(DATABASE_FILE)
	clojure -M:tests

repl: $(DATABASE_FILE)
	clojure -M:rebel

check: outdated tests

.PHONY: help Makefile clean check clj-lint lint outdated tests doc-api rebuild-db watch-db watch-tests
