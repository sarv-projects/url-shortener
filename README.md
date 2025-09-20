
# URL Shortener Backend  


A Java Spring Boot backend application that provides URL shortening service with PostgreSQL integration. This project generates unique short codes for long URLs, supports redirection, and tracks visit counts. Designed to handle 100+ daily requests reliably.

## Features

- Shorten long URLs to compact 6-character codes  
- Redirect short URLs to original URLs seamlessly  
- Persist URL mappings and visit counts in PostgreSQL  
- Basic input validation for URLs  


## Tech Stack

- Java 21  
- Spring Boot (Spring Web, Spring Data JPA)  
- PostgreSQL  
- Maven (build tool)  

## Setup and Run

1. Clone the repository

```bash
git clone https://github.com/sarv-projects/url-shortener.git
cd url-shortener
````

2. Configure PostgreSQL

* Create a database `url_shortener`
* Create user with privileges (see `application.properties` for details)

3. Update `src/main/resources/application.properties` with your DB credentials.

4. Build and run the app:

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

5. Test APIs:

* POST `/shorten` with JSON body `{ "url": "https://example.com" }` to create a short URL
* GET `/{shortCode}` to redirect to original URL

## Future Improvements

* Add frontend UI for easier usage
* Enhance analytics with detailed visit tracking (Python integration)
* Add authentication and rate-limiting
* Dockerize for easy deployment





