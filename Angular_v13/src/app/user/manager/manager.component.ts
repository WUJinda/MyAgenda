import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.scss']
})
export class ManagerComponent implements OnInit {
  token:string='';

  link = this.managerService.apiUser+"/date/export/";
  date: string = '';
  dateT = new Date();
  reportLink='';

  constructor(private router:Router, 
    private managerService: ApiService, 
    public datePipe:DatePipe) { }

  ngOnInit(): void {
    this.date = this.datePipe.transform(this.dateT,"yyyy-MM")!;
    this.reportLink = this.onGetCompteRendu();
    console.log(this.reportLink)
  }

  onLogout(){
    this.token = localStorage.getItem("token") || '';
    this.managerService.logout(this.token).subscribe(
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
