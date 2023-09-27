import { Component, OnInit } from '@angular/core';
import { RepositoryService } from 'src/services/repository.service';
import { TableDataCaregiver } from '../interfaces/tableDataCaregiver.model';
import { TableDataPatient } from '../interfaces/tableDataPatient.model';
import Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';
@Component({
  selector: 'app-caregiver-data',
  templateUrl: './caregiver-data.component.html',
  styleUrls: ['./caregiver-data.component.css']
})
export class CaregiverDataComponent implements OnInit {
  public caregiver:TableDataCaregiver;
  public patients:TableDataPatient[]=[];
  public data:any=null;
  public ok=false;
  private serverUrl = 'http://localhost:8081/socket';
  private stompClient;
  constructor(private readonly repositoryService: RepositoryService) { }

  ngOnInit(): void {
    this.caregiver=JSON.parse(localStorage.getItem('user'))
    this.getData();
    this.initializeWebSocketConnection();
  }

  public getData(){
    this.repositoryService.getData(`caregiver/${this.caregiver.id}`).
    subscribe((ras:TableDataCaregiver) => {
      this.caregiver=ras;
      this.patients=ras.patients;
   },
   (error:any) => {  
   })
}


initializeWebSocketConnection(){
  this.ok=true;
  let ws = new SockJS(this.serverUrl);
  this.stompClient =  Stomp.over(ws);
  let that = this;
  let val:String;
  this.stompClient.connect({}, function(frame) {
    that.stompClient.subscribe("/topic/greetings",function (message) {
      if(message.body) {
        $(".message").append("<br>"+"Activitate: "+ JSON.parse(message.body).activity+" start:"+
        JSON.parse(message.body).start+" end:"+
        JSON.parse(message.body).end+"</br>")
      }
    });
  });
} 
}
