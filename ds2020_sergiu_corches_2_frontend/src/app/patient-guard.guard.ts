import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/services/auth.service';
import { User } from './interfaces/user.model';

@Injectable({
  providedIn: 'root'
})
export class PatientGuardGuard implements CanActivate {
  public ok:boolean=false;
  public user:User;
  public role:String;
  constructor(private readonly authService: AuthService){

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):  boolean {
      this.ok=this.authService.isLoggedIn();
      if(this.ok){
        this.user= this.authService.getLoggedUser();
        this.role=this.user.role;
          if(this.role==="PATIENT")
          return true;
          else
          return false;
      }
  }
  
}
