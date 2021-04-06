# Todo API

To-do is a task management service which registered users can create to-do items and easily manage them. Each to-do item has a 'title' as name suggests 
and 'description' that define task. To-do items 'isDone' comes 'false' by deffault and 'createdAt' defined by system automatically.

Todo is implemented as Spring Boot app which is running embedded version of Tomcat.To store Todo item and user credentials used Postgres relational database accessed via Sprimg JPA.
Which means you have to have a Postgres server on port 5432 within deffault values. Or else you can implement your database configurations on 'application.yml'.
In this app Project Lombok used to avoid boilerplate codes on data objects.API uses Json Web Tokens(JWT) with Spring Security to authenticate and authorizate users.

# Build and Run

This api is Maven-based project including a pom.xml build file to load dependencies. Thanks to the Spring you just have to type      
```bash
$ mvn clean spring-boot:run 
```


# API Overview 

# User

| Method | HTTP Requests | Description | Returns | 
| ---  |     --    | --    |    -- |
|signup|POST /auth/auth| register | HTTP 201|
|login |POST /login| login | HTTP 200 with JWT token|

# Todo Item

| Method | HTTP Requests | Description | Returns | 
| ---  |     --    | --    |    -- |
|save| POST /item/new | create new item|TodoItem
|completed| PUT /item/edit/{id}| set completed | TodoItem|
|delete | DELETE /item/delete/{id}|delete item | boolean|
|getOne | GET /item/{id}| get item by id | TodoItem|
|getAll| GET /item/all | get all items | List<TodoItem>|

