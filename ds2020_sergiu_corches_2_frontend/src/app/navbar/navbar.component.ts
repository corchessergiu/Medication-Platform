import { ElementSchemaRegistry } from '@angular/compiler';
import { isGeneratedFile } from '@angular/compiler/src/aot/util';
import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/services/auth.service';
import {User } from '../interfaces/user.model';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent  implements OnInit{

  public user:User;
  public caregiver:boolean=false;
  public patient:boolean=false;
  public doctor:boolean=false;
  public role:String;
  public ok:boolean=false;
  constructor(private readonly authService: AuthService,
    private readonly router: Router) { }

     ngOnInit(): void {  
      this.ok=this.authService.isLoggedIn();
      if(this.ok){
      this.user= this.authService.getLoggedUser();
      this.role=this.user.role;
        if(this.role==="DOCTOR"){
        this.doctor=true;
        }
        else{
        this.doctor=false;
        }
        if(this.role==="PATIENT"){
          this.patient=true;
          }
          else{
          this.patient=false;
          }
      }
    } 

 public toLogin() {
  this.router.navigate(['/login']);
}
 public isLoggedIn() {
  return this.authService.isLoggedIn();
}

 public logout() {
  this.authService.logout().subscribe(() => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    localStorage.removeItem('userRole');
    this.authService.setLoggedUser(undefined);
    this.router.navigate(["/start"]);
  })
} 
  public goHome() {
    this.router.navigate(["/start"]);
  }
   public crudOnPatient(){
    this.router.navigate(["/doctor/crudPatient"]);
  } 
  public crudOnMedication(){
    this.router.navigate(["/doctor/crudMedication"]);
  }

  public crudOnCaregiver(){
    this.router.navigate(["/doctor/crudCaregiver"]);

  }

  public addMedicationPlan(){
    this.router.navigate(["doctor/addMedicationPlan"]);
  }


}
