# learning-microservices

Accounts, Cards and Loans are the 3 microservices with the main logic that are using mysql database.

Config server is being used to centralize all the configuration and with the help of message broker RabbitMQ and cloud bus we dont need to restart any microservice everytime a configuration is changed.

Eureka server is used for client side service registry so the microservices can talk to each other.

Accounts microservice can communicate with loans and cards microservices using eureka client.

Gateway server is as per the name suggests the API gateway that will be responsible to handle all requests and redirecting them to respective microservice.

Spring security using OAuth2(KeyCloak) is added in gateway server to authenticate all requests.