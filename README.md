# Treasury Application
## Introduction

Treasury application is a application to manage revenue , customers and vendors 
and provide analytics on the same. Aim is used build system which can provide analytics according to busisness use case scenario's like cash position , daily outstanding and others.


## Features
- Plot revenue, vendors , customer number on map according to region
- Plot line graph for revenue for last 120 days
- List out customer , revenue , customer on a list
- Show info box dashboard with customer , revenue and customer details.

## Upcoming (Feel free to contribute )
- Side panel on front end with crud operations on entities along with pagination 
- Add busisness rules tab where user can select different rules and when submitted the
rules are executed and the consolidated data can be exported as csv
- Consume csv and excel file and persist the data in databse 

## Tech

- ReactJs - Front end library
- Spring boot framework - Java based web framework to create API end point.
- MySql - SQL based relational database to persist data


## Dependency
- Leaflet JS - Leaflet is used show and intreact with map
- Open Street View - Used as tile layer in the map
- Chart js - Used to draw graphs
- Spring Data JPA - Used to interact with database
- Spring Web
- MySql database

## Installation

Treasury Application requires [Node.js](https://nodejs.org/) v10+ to run front end and
java 1.8+ to execute back end and My Sql Server to run database server.

Install the dependencies and devDependencies and start the server.

```sh
cd front-end-react
npm -i
npm start
http://localhost:3000/
```

For Database
```sh
Use My Sql Server to start database server
Create tables and their relationship according to entity classes in com.pankhudi.model
Configure port and database name in application properties in back end
```

For back end

```sh
Open pankh project in eclipse/perferred ide
Run PankhApplication class as Java application
http://localhost:8080/
```

## Output Images

Output : PC UI
![Alt text](/images/pcui.png "Pc Ui")

Output : Mobile Responsive Layout UI 1
![Alt text](/images/mobileUiResponsive1.png "Mobile Layout UI 1")

Output : Mobile Responsive Layout UI 2
![Alt text](/images/mobileUiResponsive2.png "Mobile Layout UI 2")

Output : Database Tables
![Alt text](/images/db_tables.png "Database Tables")

Output : Back End
![Alt text](/images/backendEclipse.png "Back End")
