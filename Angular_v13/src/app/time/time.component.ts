import { HttpErrorResponse } from '@angular/common/http';
import { Component, NgModule, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { Time } from '../model/user.model';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { endOfDay, startOfDay } from 'date-fns';

@Component({
  selector: 'app-time',
  templateUrl: './time.component.html',
  styleUrls: ['./time.component.scss']
})
export class TimeComponent implements OnInit {

  private colors  = [
    {
      primary: '#ad2121',
      secondary: '#FAE3E3',
    },
    {
      primary: '#1e90ff',
      secondary: '#D1E8FF',
    },
    {
      primary: '#e3bc08',
      secondary: '#FDF1BA',
    }
  ];

  getColorRand():any{
    return Math.random() * (this.colors.length+1);
  }
  events :CalendarEvent[] =[];

  viewDate: Date = new Date();
  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;

  constructor(private employeeService:ApiService) { }

  ngOnInit(): void {

    console.log(new Date("2022-01-05T21:29:18"))
    this.employeeService.getAllTimes().subscribe(
      (res:Time[])=>{
       let events:CalendarEvent[]=[]
       for(let i=0;i< res.length;i++){
         let evenTime:CalendarEvent = {
           end: endOfDay(new Date(res[i].dateEnd)),
           start: startOfDay(new Date(res[i].dateStart)),
           title: "<h8>Description: </h8><p>"+res[i].project.description+"</p> <h8>Title</h8><p> "+res[i].project.title+"</p><h8>Manager</h8><p>"+res[i].project.manager.username+"</p>" ,
           color: this.colors[i%this.colors.length]
         }
         events.push(evenTime)
        }
        this.events = events
      },
      (err:HttpErrorResponse)=>{
        alert(err.message)
      }
    )

  }

  setView(view: CalendarView) {
    this.view = view;
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    console.log(date);
  }

}
