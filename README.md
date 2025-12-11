# repositoryscorer
An app to score GitHub repositories

## Technologies used

 - Java 25
 - Spring Boot 4

### How to run locally

 - ./gradlew bootRun
 - Go to the API definition http://localhost:8080/swagger-ui/index.html#/Scoring/score

### About the implementation

I wanted to keep the design and implementation lean for this task. If I was implementing more production grade code I 
would consider to add some more DTOs between the design layers for clearer separation between them, going towards clean
architecture. 

The implementation of the scoring algorithm itself is very naive. If implementing a more advanced one I would think 
about factors like, what does a star mean and what does a fork mean, which should be weighted more etc.

Githubs API has a default of 30 results per page, but I made this configurable via a property in the application.yml. It
would make sense to implement a solution for fetching more pages, but when I experimented with it I hit the API rate 
limit, which is around 60 calls per hour for unauthenticated requests, and I decided to leave it as is. 

Note on testing, I have tested happy cases only, so would like to add more tests for exceptions and make it more 
resilient that way. 
