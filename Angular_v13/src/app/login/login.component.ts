import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm,} from "@angular/forms";
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { ApiService } from "../service/api.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup;
  constructor(
    private formBuilder : FormBuilder,
    private http : HttpClient,
    private registerService:ApiService,
    private router : Router
  ) { }
  ngOnInit(): void {
  }
  
  public onLogin(loginForm: NgForm){
    this.registerService.login(loginForm.value).subscribe(
      (res:User)=>{
        console.log(res)
        alert("Login Successful")
        loginForm.reset();
        localStorage.setItem('username', res.username);
        localStorage.setItem('firstname', res.firstname);
        localStorage.setItem('lastname', res.lastname);
        localStorage.setItem('role', res.role.label);
        localStorage.setItem('token', res.tokenSignature);
        console.log(localStorage);
        this.router.navigate([this.getRole(res).toLowerCase()]);

      },
      (err:HttpErrorResponse)=>{
        alert(err.message)
      }
    )
  }

  public getRole(user:User):String{
    return user.role.label;
  }

}
