import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { Router } from '@angular/router';
import { AddUser, Updateuser, User } from 'src/app/model/user.model';
import { AdminService } from 'src/app/service/admin.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  token:string='';

  constructor(public apiService: ApiService,public Adminservice: AdminService,private router: Router) { }
  public users: User[] = [] ;
  public user: User = <User>{};
  public edituser: User;
  public deleteuser: User;
  public usernames: string[];
  public listuser: User;
  private addAUser: AddUser;

  


  ngOnInit(): void {
    this.getUsers();
  }

  public getUsers(): void{
    this.Adminservice.getUsers().subscribe(
      (response: User[])  => {
        this.users = response;
        console.log(this.users);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      
    );
  }
  public searchUser(key: string): void {
    console.log(key);
    const results: User[] = [];
    for (let user of this.users) {
      if (user.username.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.firstname.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.lastname.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
      results.push(user);
      console.log(user.username);
      }
    }
    this.users = results;
    if (results.length === 0 || !key) {
      this.getUsers();
    }
  }
  public Onconfirm(editForm: NgForm){
    this.edituser=editForm.value;
    console.log("confimed");
  }
  public editUser(updateuser: Updateuser): void {

    this.Adminservice.updateUser(this.edituser.id, updateuser).subscribe(
      (response: User) => {
        this.edituser = response;
        this.getUsers();
        alert("edited");
        console.log(this.edituser);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public addUser(addForm: NgForm): void {
    this.addAUser = addForm.value; 
    this.addAUser.password = "123456";
    document.getElementById('add-user-form')!.click();
    this.Adminservice.addUser(this.addAUser).subscribe(
      (response: User) => {
        console.log(response);
        this.getUsers();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }
  public deleteUser(username: string): void {
    this.Adminservice.deleteUser(username).subscribe(
      (response: void) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public deleteUsers(): void {
    const usernames: string[]=[];
    for (const user of this.users) {
      usernames.push(user.username);
      }

    this.Adminservice.deleteUsers(usernames).subscribe(
      (response: User[]) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public onOpenModal(user: User= <User>{}, mode: string): void {
    const container = document.getElementById('main');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addUserModal');
    }
    if (mode === 'edit') {
      this.edituser = user;
      button.setAttribute('data-target', '#updateUserModal');
    }
    if (mode === 'delete') {
      this.deleteuser = user;
      button.setAttribute('data-target', '#deleteUserModal');
    }
    if (mode === 'deleteall') {
      button.setAttribute('data-target', '#deleteUsersModal');
    }
    container!.appendChild(button);
    button.click();
  }

  onLogout(){
    this.token = localStorage.getItem("token") || '';
    this.apiService.logout(this.token).subscribe(
      (res:string)=>{
        alert("Logout Successful")
        console.log(res)
        localStorage.clear()
        this.router.navigate(['login'])
      },
      (err:HttpErrorResponse)=>{
        localStorage.clear()
        this.router.navigate(['login'])        
        
      }
    )
  }
}
  
