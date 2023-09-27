import { Injectable, OnInit } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EnvironmentUrlService } from './environement-url.service'
import { RepositoryService } from './repository.service';
import { Router } from '@angular/router';
import {User} from '../app/interfaces/user.model';
import { TableDataPatient } from 'src/app/interfaces/tableDataPatient.model';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

  
@Injectable()
export class AuthService {
    public patient:TableDataPatient;
    public patients:TableDataPatient[]=[];

    constructor(
        private readonly httpClient: HttpClient,
        private readonly repositoryService: RepositoryService,
        private readonly router: Router,
   
        private readonly enviromentUrlService: EnvironmentUrlService) {
    }

    public login(body: any) {
        return this.httpClient.post('http://localhost:8081/login', body, { observe: 'response' });
    }

    public logout() {
        return this.httpClient.post("http://localhost:8081/logout", null);
    }

    public loginAndRedirect(userData: any) {
        this.login(JSON.stringify(userData)).subscribe(
            (resp: any) => {
                localStorage.setItem('token', resp.headers.get('authorization'));
                this.repositoryService.getData("user/loggedUser").subscribe(
                    res2 => {
                        const user = res2 as User;
                        this.setLoggedUser(user);
                        this.setLoggedUserRole(user.role);
                         if (user.role == 'DOCTOR') 
                            this.router.navigate(['doctor/crudPatient']);
                            if (user.role == 'PATIENT')
                                 this.router.navigate(['patient/data']);
                                 if (user.role == 'CAREGIVER'){
                                this.router.navigate(['caregiver/data'])

                                 }
                    },
                    err2 => { console.log(err2); }
                )
            })
    }

    public isLoggedIn() {
        if (localStorage.getItem('token') != null)
            return true;
        return false;
    }
    public isLoggedInRole() {
        let userRole;
        if (localStorage.getItem('token') != null)
            {
                userRole=localStorage.getItem('userRole');
                return userRole;
            }
        return null;
    }



     public setLoggedUser(user: User) {
        localStorage.setItem('user', JSON.stringify(user));
    }

    public setLoggedUserRole(role) {
        localStorage.setItem('userRole', JSON.stringify(role));
    }

    public getLoggedUser(): User {
        return JSON.parse(localStorage.getItem('user'));
    } 





     
}