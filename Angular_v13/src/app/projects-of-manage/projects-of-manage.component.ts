import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Project } from '../model/user.model';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-projects-of-manage',
  templateUrl: './projects-of-manage.component.html',
  styleUrls: ['./projects-of-manage.component.scss']
})
export class ProjectsOfManageComponent implements OnInit {

  projects:Project[]=[];
  index: number = 0;

  constructor(
    private managerService:ApiService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll():void{
    this.managerService.getAllProjects().subscribe(
      (res:Project[])=> {
        this.projects = res;
        console.log(res)
      }
    )
  }
}
