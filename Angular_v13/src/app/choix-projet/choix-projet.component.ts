import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Project, Time, TimeRequest } from '../model/user.model';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-choix-projet',
  templateUrl: './choix-projet.component.html',
  styleUrls: ['./choix-projet.component.scss']
})
export class ChoixProjetComponent implements OnInit {

  projects:Project[]=[];
  projectId:number = 0;
  constructor(
    private employeeService:ApiService
  ) { }

  ngOnInit(): void {
    this.getProjects();
  }
  selected = "----"
  
  update(e:any){
    this.projectId = e.target.value;
    this.selected = e.target.value
  }
  onAddTime(tiemForm:NgForm):void{
    let timeReq:TimeRequest = tiemForm.value;
    timeReq.projectId = this.projectId;
    this.employeeService.choixProjet(timeReq).subscribe(
      (res:Time)=>{
        console.log(res);
        alert('Time Created')
        tiemForm.reset()
      },
      (err:HttpErrorResponse)=>{
        tiemForm.reset()
        alert("Wrong Input!!")
      }
    )
  }

  getProjects():void{
    this.employeeService.getAllUsersProjects().subscribe(
      (res:Project[])=>{
        this.projects=res
        console.log(this.projects)
      }
    )
  }

}
