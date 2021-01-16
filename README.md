## [jOOQ](https://www.jooq.org/) - Great library for dynamic SQL
## [RSQL](https://github.com/jirutka/rsql-parser) - FIQL Parser

### This is a project to demonstrate the power of jOOQ along with RSQL; dynamically constructing SQL queries with many filters specified in the `WHERE` condition.
#### Spring boot application, jOOQ, RSQL

#### To start Postgres on Docker
```
cd docker
```

- #### Run 
```
docker-compose up
```

#### Run

#### Example queries
`http://localhost:8080/authors/search?filter=first_name==John`
will translate to the SQL query
```
select 
  "author"."first_name", 
  "author"."last_name"
from "author"
where "author"."first_name" = 'John'
```

`http://localhost:8080/authors/search?filter=first_name==John,last_name==Sierra`
will translate to the SQL query
```
select 
  "author"."first_name", 
  "author"."last_name"
from "author"
where (
  "author"."first_name" = 'John'
  or "author"."last_name" = 'Sierra'
)
```

`http://localhost:8080/authors/search?filter=first_name==Bert;last_name==Bates`
will translate to the SQL query
```
select 
  "author"."first_name", 
  "author"."last_name"
from "author"
where (
  "author"."first_name" = 'Bert'
  and "author"."last_name" = 'Bates'
)
```
