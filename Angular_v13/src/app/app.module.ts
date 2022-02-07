import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule }   from '@angular/forms';
import { HttpClientModule} from "@angular/common/http";
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ApiService } from './service/api.service';
import { SignupComponent } from './signup/signup.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AdminComponent } from './user/admin/admin.component';
import { ManagerComponent } from './user/manager/manager.component';
import { EmployeeComponent } from './user/employee/employee.component';
import { TimeComponent } from './time/time.component';
import { ChoixProjetComponent } from './choix-projet/choix-projet.component';
import { AddProjetComponent } from './add-projet/add-projet.component';
import { AddUserManagerComponent } from './add-user-manager/add-user-manager.component';
import { UsersOfManagerComponent } from './users-of-manager/users-of-manager.component';
import { ProjectsOfManageComponent } from './projects-of-manage/projects-of-manage.component';
import { AdminService } from './service/admin.service';
import { DatePipe } from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    AdminComponent,
    ManagerComponent,
    EmployeeComponent,
    TimeComponent,
    ChoixProjetComponent,
    AddProjetComponent,
    AddUserManagerComponent,
    UsersOfManagerComponent,
    ProjectsOfManageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FontAwesomeModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    })
    
  ],
  providers: [ApiService,
            AdminService,
            DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
