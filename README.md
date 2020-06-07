## Currency Converter

### Back End : Implement the service which satisfies the following requirements:
### Should fetch currency rates from any open public api :
```
### Current Exchange rate
https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_cu
rrency=USD&to_currency=JPY&apikey=demo

(Example: https://www.alphavantage.co/documentation/ API key can be retrieved free during
sign up) - current exange rates between EUR, USD, GBP

```
### Historic Data
### Daily graph
```
- historical data for displaying swing chart
- data for every 5 minutes in case of daily graph
https://www.alphavantage.co/query?function=FX_INTRADAY&from_symbol=EUR&to_sy
mbol=USD&interval=5min&apikey=demo
```
### Weekly graph
```
- data for every 60 minutes in case of daily/weekly graph
https://www.alphavantage.co/query?function=FX_INTRADAY&from_symbol=EUR&to_sy
mbol=USD&interval=60min&outputsize=full&apikey=demo
```
### Monthly graph
```
- data for each day in case of monthly graph
https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=EUR&to_symbol
=USD&apikey=demo
```
### Storage - Redis (Required -  Please update port and host into application.properties)
```
Data shoud be stored in Redis
- Key should contain information about from and to currency conversion should proceed (e.g.
USDTOEUR) and date when it was fetched (e.g. format yyyyMMddhhmm)
- When service receives a request to show the rates, the latest rate should be selected (latest by
date yyyyMMddhhmm)
```

```
Listen to a queue where events for rates updates will come. The message should contain
an object indicating from which and to what currency the conversion should be made.
{
"from":"EUR",
"to":"USD"
}

The listener trigger fetching of new rates and store them in Redis as described above and send a message to this queue.
```

### Possible options:
```
- command line
- another service. There should be public web api which triggers message sending to the
queue (PUT http://service2:8080/rates?from=EUR&to=USD)
```

### Build 
```
mvn clean 
mvn compile
mvn install
mvn package

java -jar <jar-file>.jar
```

### Configured Port: 8085
