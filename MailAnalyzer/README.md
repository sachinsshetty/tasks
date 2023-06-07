## Readme 

* Programming task:
  * Spring Boot Kafka Information Collector

* Max time for solving: 
  * 60min

* Goal: 
  * Consume a Kafka stream of events and collect certain information in
  memory to be able to deliver it to a caller via RESTful API.
  The event should contain at least one field containing a mail address. The
  application should consume the event stream and count the unique number of
  overall mail addresses and domains which occurred within the event stream.
  Bring in own ideas when something seems not be fully specified.

* TODO
  1. Setup Kafka streams  - confluent kafka - docker compose setup
  2. Generate stream of mail address - Random address generation + Validation  / Use sample json
  3. Store processed events in Database - PostgreSQL - docker container
  4. Data - mail address
  5. Count - unique mail address and domains
  
* Tech Stack
  * SpringBoot - Framework
  * SwaggerUI - REST endpoint + Documentation
  * PostgreSQL
  * kafka streams - confluent inc - docker compose
  * Junit - Testing 
  * Testcontainer - end to end testing
