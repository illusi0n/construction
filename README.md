# construction app

REST API in construction domain

# technology stack and libs

- java
- spring boot
- maven
- hibernate
- h2
- spring rest docs
- lombok

# write down any technical decision

- H2 as db, ease of development, later testing part, packaging project as executable
- enabled at first h2 console, though never used it
- spring boot tasks for processing accept offer requests
- written integration test to cover accept offer logic since it has the most business logic of the app

# project setup build and run with maven

Application tested on environment with

- java 15
- maven 3.6.2
- spring boot 2.4.2

Command to run tests and start up the app
`mvn test asciidoctor::process-asciidoc spring-boot:run`

App starts on http://localhost:8080.

API docs are in `target\generated-sources\documentation\api.html`.

Some api docs are missing in cases where non existing ids are passed. The request/response looks the same as in "2. Find
tender by non existing id" from the docs.

# general approach

- understand domain problem
- model domain problem with entities and REST endpoints
- first implement core entities (with must fields) and rest endpoints
- expand features as needed (e.g. tender status, processing accept offer logic)
- add exception handling and everything that was missed
- write api tests + spring rest docs

For better view check PRs or TODO list (the list of tasks is ordered as how I solved them).

# assumptions/notes

- bidder and issuer are different entities in the domain
- multiple accept offers can be sent for the same tender, and only one should succeed
- no login is required
- no pagination for offers, tenders