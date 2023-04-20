# demo-challenge


Initially, I found 3 entities to be persisted on the database. But those entities will probably change

Those entities are :

* Bank Account
First name
last name
rounting number
national id number
account number

* Transaction Info
currency
transaction fee
amount send
amount recipient

* Transaction
Amount
Currency
Date
Status (completed, refunded, failed, in progress)



I started the project using spring webclient to invoke the external APIs but I'm not used to it, so after some time struggling with that, I changed to the old and gold rest template

Here is a small description of the feature as per my understanding. If I had to share this with my team I would create a flow diagram to describe it.

A feature é sacar o seu dinheiro da sua carteira e transferir para uma conta em um banco
Para isso precisamos :

The feature is withdraw money from your wallet and send it to a bank account
In order to achieve it, we need :

1 - Receive a payment request (payment dto)
2 - Check Wallet balance
3 - Call payment service to perform the payment
4 - Discount / calc transaction fee
5 - Update transaction status and wallet balance.


We also want to be able to query the transactions ordered by descending “creation date” in a paginated table and
filtered by amount and date.


