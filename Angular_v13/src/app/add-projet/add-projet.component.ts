import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Project } from '../model/user.model';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-add-projet',
  templateUrl: './add-projet.component.html',
  styleUrls: ['./add-projet.component.scss']
})
export class AddProjetComponent implements OnInit {

  constructor(
    private managerService:ApiService,
    private router:Router
  ) { }

  ngOnInit(): void {
  }

  onAddProject(projectForm:NgForm){
    return this.managerService.createProject(projectForm.value).subscribe(
      (res:Project)=>{
        alert('Project Created!!')
        console.log(res)
        this.router.navigate(['manager'])
      }
    )
  }

}
