# Pricing Discount API

The Pricing Discount API provides services for managing discounts, a SpringBoot demo application running with an in-memory H2 database, following the hexagonal architecture.

## Getting Started

### Prerequisites

Before you can use the application, ensure you have the following prerequisites:

- Java Development Kit (JDK) 17 or later.
- Git (for cloning repositories).
- Maven (for building and managing dependencies).
- Docker runtime (if you want to execute the application in a Docker container)

### Installation

1. Clone this repository to your local machine

### Running the Utility

If can choose between two options

#### Java

1. Build the application: mvn clean install
2. run: java -jar target/pricing-discount-demo-0.0.1-SNAPSHOT.jar

#### Docker

1. Build the application and image: mvn clean package jib:dockerBuild
2. run: docker run -p 8080:8080 pricing-discount-demo

The service will be acessible in http://localhost:8080

## Endpoints

### Documentation

Swagger: http://localhost:8080/swagger-ui/index.html

OpenAPI: http://localhost:8080/v3/api-docs

### Healthcheck

http://localhost:8080/actuator/health/liveness

http://localhost:8080/actuator/health/readiness

### API Endpoints

#### Public

The following endpoints can be use to get the discounted price using two different policies.

1. Endpoint that returns a percentage based discounted price for a given product

    GET
    /api/public/products/{productId}/discount/percentage

2. Endpoint that returns a count based discounted price for a given product

    GET
    /api/public/products/{productId}/discount/count?quantity=xxx

#### Authenticated

The following endpoints can be use to manage the discount configurations.

Note: This demo application is using basic authentication. User credentials can be found on `src/main/resources/application.properties`

1. Endpoints that manage the percentage based discount configurations for a given product

    GET
    /api/discounts/percentage/{productId}

    POST
    /api/discounts/percentage/{productId}

2. Endpoints that manage the count based discount configurations for a given product

    GET
    /api/discounts/count/{productId}

    POST
    /api/discounts/count/{productId}

## Final notes

This is a demo application focusing on the discount management and calculation, it is assumed there is a product catalogue service in the system so the application is using a mocked list of products, for more details check `src/main/java/com/aafc/pricing/application/product/service/ProductServiceImpl.java`

When starting the application only two products have discount configurations, for more details check `src/main/resources/data.sql`

Prossible future improvements:

1. Change the database (instead of in-memory H2)
2. Change authentication mechanism to a more secure protocol
3. Add proper logging
4. Implement the remaining CRUD endpoints and product service integration
5. Add a cache mechanism over the discount configurations for increased performance
6. Improve API documentation with proper descriptions
