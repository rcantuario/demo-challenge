

# Ontop demo challenge

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is the implementation for ontop challenge.

	
## Technologies
Project is created with:
* JDK 8 or above
* Spring boot 3.0.5
* H2 database

	
## Setup
To run this project, install it locally using Maven, once the jar is running you can access it through the url:

localhost:8080/api/payments

here is an example of the payload to create a new payment : 

{
  "accountId": 1,
  "walletId": 1,
  "amount": 4,
  "currency": "USD"
}

The same url (localhost:8080/api/payments) can be used to retrieve a list of transactions, but you have to specify date and amount for filtering

The file Renan challenge.postman_collection.json is a collection of requests to show the API usage and also some possible exceptions

The database is available at http://localhost:8080/h2-console/  

user :sa
password : password

During the application startup the class DataStartup inserts some registers on the database so it does not start empty.






