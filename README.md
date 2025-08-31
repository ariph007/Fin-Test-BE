## How to setup
- Run docker-compose up, it will create postgresql service with port 5458
- Open Dbeaver or other database tool and connect to your localhost:5458 as postgresql with user and password : postgres
- Create database "finology"
- Start your server and it will running in default port 8080

Flyway Database migration will run when you start your server, on init it will fetch from https://jsonplaceholder.typicode.com and save the data to the database(it only run once)

## Swagger
http://localhost:8080/swagger-ui.html

## Feature
- Pagination
- Sorting
- Filter
  (Using Spring Data JPA Specifications)
  
## Dependency
- Springboot
- Springdata JPA
- Postgresql
- Swagger
- Flyway
- Docker
