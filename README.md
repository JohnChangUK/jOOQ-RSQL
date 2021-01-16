# [jOOQ](https://www.jooq.org/) - Great library for dynamic SQL
# [RSQL](https://github.com/jirutka/rsql-parser) - FIQL Parser

### This is a project to demonstrate the power of jOOQ along with RSQL; dynamically constructing SQL queries with many filters specified in the `WHERE` condition.
# Reasons to use jOOQ
- For statistical queries, nothing comes close to jOOQ due to the dynamic ability to construct SQL queries at runtime.
- This gives great flexibility in performing dynamic joins and filtering.
- jOOQ forces you to understand and write the raw SQL implementation, enabling you to optimize queries where necessary
- Type safety built in

#### To start Postgres on Docker
```
cd docker
```

- #### Run 
```
docker-compose up
```

#### Run

- This is a Spring app which uses Flyway to initialize the database schema under `src/main/resources/db/migration`.
- 3 tables will be created with populated values

## Author table
```
id    first_name      last_name
1	    Kathy     Sierra
2	    Bert      Bates
3	    Bryan     Basham
4	    JK        Rowling
5	    Bael      Dung
6	    John      C
```

## Book table
```
id          title
1	    Head First Java
2	    Head First Servlets and JSP
3	    OCA/OCP Java SE 7 Programmer
4	    Harry Potter
5	    Spring Tuts
6	    Invest
```

## Author_Book table
```
author_id    book_id
1	     1
1	     3
2	     1
4	     4
5	     5
5	     6
6            6
```


#### Example queries
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

`http://localhost:8080/authors/search?filter=first_name==Bert,first_name=in=(JK, John)`
will translate to the SQL query
```
select 
  "author"."first_name", 
  "author"."last_name"
from "author"
where (
  "author"."first_name" = 'Bert'
  or "author"."first_name" in (
    'JK', 'John'
  )
)
```
