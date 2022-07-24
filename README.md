<h1>Application Project Preview:</h1>
<h5>Project Name: Take your Meds! <br>
Application Owner: Andreea Lupascu <br>
Tools used: IntelliJ IDEA, Maven, PostgreSQL, HTML, Lombok <br>
Programming Language: Java <br>
Framework: Spring Boot <br>
Architecture Style: 3-tier Architecture
</h5>

<h1>Project Overview:</h1>
<h5>
The purpose of the application is to monitor a drug treatment.
The user has the possibility to add several medicaments, differentiated by several attributes, and to generate a daily report which returns the medicaments according to the time of day. Thus, if the user asks for this information at 2:30 p.m., for example, the returned result will only contain the medicaments for Afternoon, Evening, combinations of the two, and ALL (represented by an enum called timeOfDay).
<br>
<br>
As a future functionality, I would like to implement a weekly planner (which also contains the exact time) and add a login page.
</h5>

<h1>Architecture:</h1>

<h5>
Data Access Layer (Repository/Domain), which provides a connection between the application and the PostgreSQL database <br>
<br>
Business Logic Layer (Service), which provides the logic for all the CRUD operations <br>
<br>
Presentation Layer (Controller/API) - One controller that provides the necessary resources for the web pages and one controller which provides the endpoints that allow the Business Logic to be accessed with external requests </h5>
