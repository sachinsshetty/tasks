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
  3. Data - mail address
  4. Count - unique mail address and domains
  
* Tech Stack
  * SpringBoot - Framework
  * kafka streams - confluent inc - docker compose
  * Junit - Testing 
  * SwaggerUI - REST endpoint + Documentation

* References
  * [Interactive Queries](https://docs.confluent.io/platform/current/streams/developer-guide/interactive-queries.html)
  * https://www.baeldung.com/spring-boot-kafka-streams