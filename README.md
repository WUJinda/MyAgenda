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

![Products](screenshots/products.png)

## Technical stack 

Front-end will be built using Angular + Bootstrap.
Back-end will be built using Springboot with database MySQL.
The plugins and application used to test the interface are: SwaggerUI + Postman

## Development server

Before run back-end, you need to configure `application.properties`, you need to modify the database name, and the user name and password are the same as those of the local MySQL.
Before run front-end, you need to enter `npm install` on the command line to install the required installation package in the configuration file
Run `ng serve --open` in Terminal for front-end. Navigate to `http://localhost:4200/`. The app in angular will automatically reload if you change any of the source files.

中文补充：   

首先打开POC_BACKEND, 修改application.properties文件中的用户密码(指本地MySQL的用户名和密码)，之后设置数据库名称，这里叫kanban，与Navicat可视化数据库软件中所创建的链接中的数据库名称保持一致。
注意：先在数据库中输入role表的内容，是个漏洞，需要之后在后端初始化（有时间搞）。
打开数据库，然后完成springboot的运行环境配置，保证环境不出错，正常运行启动。

出现“Tomcat started on port：8090”就表示运行成功。

打开前端，配置运行环境，命令行输入npm install，安装配置文件中的所需的安装包。
然后命令行输入 ng s --open 运行前端程序。localhost:4200 弹出界面表示运行成功。

## Agenda API Docs

Interface documentation for the Swagger plugin   

![Products](screenshots/products.png)













