# Webcrawler
## Title
The project calculates how many hyperlinks a website has
and their depth level according to host url.


## Installation

Clone repository.
Connect to database using application.properties file.
Run project

## Test

URLs for test endpoints:

- Parse domain url and show info.
  Get method http://localhost:8080/domain?domainUrl="domainUrl"

- Get pages from database for specific domain.
  Get method http://localhost:8080/domain/pages/"domainId"

- Delete domain and its pages from database.
  Delete method http://localhost:8080/domain/"domainId"
