import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Project, User, Time } from 'src/app/model/user.model';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  user: User;
  time: Time;
  project: Project;
  token:string='';

  link = this.employeeService.apiUser+"/date/export/";
  date: string = '';
  dateT = new Date();
  reportLink='';

  constructor(public employeeService: ApiService, 
    private router:Router, 
    public datePipe:DatePipe ) {}

  ngOnInit(): void {
    this.date = this.datePipe.transform(this.dateT,"yyyy-MM")!;
    this.reportLink = this.onGetCompteRendu();
    console.log(this.reportLink)
  }

  onLogout(){
    this.token = localStorage.getItem("token") || '';
    this.employeeService.logout(this.token).subscribe(
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
  
  onGetCompteRendu(): string{
    return this.link  + '' + this.date;
  }
  
}
