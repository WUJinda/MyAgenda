import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-users-of-manager',
  templateUrl: './users-of-manager.component.html',
  styleUrls: ['./users-of-manager.component.scss']
})
export class UsersOfManagerComponent implements OnInit {

  public users:User[]=[];

  link = this.managerService.apiManager+"/date/export/";
  date: string = '';
  dateT = new Date();
  reportLink='';

  constructor(
    private http:HttpClient,
    private managerService:ApiService,
    public datePipe:DatePipe
  ) { }

  ngOnInit(): void {
    this.date = this.datePipe.transform(this.dateT,"yyyy-MM")!;

    this.managerService.getAll().subscribe(
      (res:User[])=>{
        this.users = res;
      },
      (err:HttpErrorResponse)=>{
        alert(err.message)
      }
    )
  }

  onGetCompteRendu(id: number): string{
    return this.link +''+ id + '/' + this.date;
  }

}
