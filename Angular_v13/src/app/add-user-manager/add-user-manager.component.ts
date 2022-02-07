import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-add-user-manager',
  templateUrl: './add-user-manager.component.html',
  styleUrls: ['./add-user-manager.component.scss']
})
export class AddUserManagerComponent implements OnInit {

  constructor(
    private managerService:ApiService,
    private router:Router
  ) { }

  ngOnInit(): void {
  }

  onRegister(usertForm:NgForm):void{
    usertForm.value.roleId = 1;
    this.managerService.addUserAdmin(usertForm.value).subscribe(
      (res:User)=>{
        console.log(res)
        alert("User CREATED")
        this.router.navigate(['/manager'])
      },
      (err:HttpErrorResponse)=>{
        console.log(err.message)
      }
    )
  }
}
