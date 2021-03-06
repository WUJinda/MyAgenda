# MyAgenda
School project with Springboot + Angular + MySQL. This web program is designed to handle time and project information for employees, managers and administrators.
> Made by [Ilham BEN-NAR](https://github.com/ilberr/APPLICATION-DE-SAISIE-DE-TEMPS), [Mouad RIALI](https://github.com/RIALI-MOUAD/POC_Backend) and [WU Jinda](https://github.com/WUJinda/MyAgenda)

Demo show on Youtube: [Link](https://youtu.be/Edx1L4j-Fyw) 

## Subject

Realization of a web application allowing the entry, consultation, editing of times in a development company.


User stories
.As a user,you can connect via a Login Password (supported profiles are Admin, Manager, User)
.As a User, you can enter your times, at the granularity of the hour (time project choice)
.As a User, you can edit a monthly report (PDF export)

.As a Manager, you can consult the times entered by the Users who are attached to him
.As a Manager, you can edit the monthly reports of the Users attached to it (PDF export)
.As a Manager, you can enter projects
.As a Manager, you can enter new Users who will be attached to it

.As an Admin, you can change the status of a User (Manager Admin)
.As an Admin, you can change the assignment of a User (change of Manager)

Optional user stories
.As an unauthenticated user, I cannot request the AP (except to authenticate myself)
.As a User, you can enter your CRA in offline mode (with the projects on which you have already charged). The synchronization will be done automatically when you find the network again.

## Screenshots

![HomePage](screenshots/HomePage.jpg)
![RegisterC](screenshots/RegisterC.jpg)
![token](screenshots/token.png)
![verification](screenshots/verification.jpg)
![Login](screenshots/Login.jpg)
![Login Success](screenshots/Login Success.jpg)
![EmployeeSpace](screenshots/EmployeeSpace.jpg)
![addTime](screenshots/addTime.jpg)
![Times](screenshots/Times.jpg)
![week](screenshots/week.jpg)
![userReport](screenshots/userReport.jpg)
![addproject](screenshots/addproject.jpg)
![projectslist](screenshots/projectslist.jpg)
![adduser](screenshots/adduser.jpg)
![usersList](screenshots/usersList.jpg)
![managerReport](screenshots/managerReport.jpg)
![adminDash](screenshots/adminDash.jpg)
![newUser](screenshots/newUser.jpg)
![findUser](screenshots/findUser.jpg)
![deleteUser](screenshots/deleteUser.jpg)
![afterDel](screenshots/afterDel.jpg)

## Technical stack 

Front-end will be built using Angular + Bootstrap.
Back-end will be built using Springboot with database MySQL.
The plugins and application used to test the interface are: SwaggerUI + Postman

## Development server

Before run back-end, you need to configure `application.properties`, you need to modify the database name, and the user name and password are the same as those of the local MySQL.
Before run front-end, you need to enter `npm install` on the command line to install the required installation package in the configuration file
Run `ng serve --open` in Terminal for front-end. Navigate to `http://localhost:4200/`. The app in angular will automatically reload if you change any of the source files.

???????????????   

????????????POC_BACKEND, ??????application.properties????????????????????????(?????????MySQL?????????????????????)??????????????????????????????????????????kanban??????Navicat?????????????????????????????????????????????????????????????????????????????????
?????????????????????????????????role?????????????????????????????????????????????????????????????????????????????????
??????????????????????????????springboot?????????????????????????????????????????????????????????????????????

?????????Tomcat started on port???8090???????????????????????????

???????????????????????????????????????????????????npm install????????????????????????????????????????????????
????????????????????? ng s --open ?????????????????????localhost:4200 ?????????????????????????????????

## Agenda API Docs

Interface documentation for the Swagger plugin   

![Products](screenshots/home-resources.png)
![admin-controll](screenshots/admin-controll.png)
![Products](screenshots/employee-controller.png)
![Products](screenshots/manager-controller.png)
![Products](screenshots/user-controller.png)
![Products](screenshots/registration-controller.png)















