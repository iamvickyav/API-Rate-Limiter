# **Steps to run the code**

This application uses Docker for Redis & MySQL. Once the Docker is started with Docker compose the scripts inside app/rate-limiter-app/src/Docker/scripts folder will run automatically & inititalise the DB.

For testing the endpoints, please use the postman collection saved under **artifacts/Postman/** folder

## Starting Redis & MySQL with Docker

```
/RateLimitApi > cd app/rate-limiter-app/src/Docker/
	
/Docker > docker-compose up
```

## Running the Application

### In a new terminal window, please run the following commands

```
/RateLimitApi > ./mvnw clean install

	(or)	

/RateLimitApi > ./mvnw clean install -DskipTests 

/RateLimitApi > ./mvnw spring-boot:run 
```
The last command will start the spring boot application in embedded Tomcat. 

**Note**: This application is still deployable (tested the same) in a standalone Tomcat as well

If you want to run it in standalone Tomcat, please run the following commands

```
/RateLimitApi > ./mvnw clean package

/RateLimitApi > cd app/rate-limiter-app/target/
```

There you can find the **RateLimitApi.war** which is deployable in a standalone Tomcat

## Validate Application running status

### /heartbeat Endpoint (GET Request)

A successful response to localhost:8080/heartbeat means the application is deployed & running without any issues

```
{
    "message": "Application is running"
}
```
### /healthcheck Endpoint (GET Request)

A successful response to localhost:8080/healthcheck means the DB & Redis is up and running

```
{
    "Database": "Up and running",
    "Redis": "Up and running"
}
```

