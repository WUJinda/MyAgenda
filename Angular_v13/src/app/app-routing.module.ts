import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { AdminComponent } from './user/admin/admin.component';
import { ManagerComponent } from './user/manager/manager.component';
import { EmployeeComponent } from './user/employee/employee.component';
import { TimeComponent } from './time/time.component';
import { ChoixProjetComponent } from './choix-projet/choix-projet.component';
import { AddProjetComponent } from './add-projet/add-projet.component';
import { AddUserManagerComponent } from './add-user-manager/add-user-manager.component';
import { UsersOfManagerComponent } from './users-of-manager/users-of-manager.component';
import { ProjectsOfManageComponent } from './projects-of-manage/projects-of-manage.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: HomeComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'manager', component: ManagerComponent,
  children: [
    {path: 'create-project', component:AddProjetComponent},
    {path: 'add-user', component:AddUserManagerComponent},
    {path: 'users', component:UsersOfManagerComponent},
    {path: '', component:UsersOfManagerComponent},
    {path: 'projects', component:ProjectsOfManageComponent}
  ] },
  { path: 'employee', component: EmployeeComponent,
    children: [
      {path: 'add-time', component: ChoixProjetComponent},
      {path: 'time', component: TimeComponent},
      {path: '', component: TimeComponent}
  ]   },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [
  TimeComponent,
  ChoixProjetComponent
]