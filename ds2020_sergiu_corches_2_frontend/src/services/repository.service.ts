import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EnvironmentUrlService} from './environement-url.service'

@Injectable()
export class RepositoryService{
    [x: string]: any;


    constructor(public httpClient:HttpClient, public enviromentUrlService: EnvironmentUrlService){
    }



 /*    public login(body:any){
        return this.httpClient.post('http://localhost:8080/login',body,{observe:'response'});
    }

    public logout() {
        return this.httpClient.post("http://localhost:8080/logout", null);
    }

    public isLoggedIn(){
        if(localStorage.getItem('user')!=null)
            return true;
        return false;
    } */
    
    public getData(route: string, headers?:HttpHeaders){
        return this.httpClient.get(this.createCompleteRoute(route,this.enviromentUrlService.urlAddress));
    }

    public create(route:string, body:any, headers?:HttpHeaders){
        return this.httpClient.post(this.createCompleteRoute(route,this.enviromentUrlService.urlAddress),body);
    }

    public update(route:string, body:any,headers?:HttpHeaders){
        return this.httpClient.put(this.createCompleteRoute(route,this.enviromentUrlService.urlAddress),body);
    }

    public delete(route:string,body?:any,headers?:HttpHeaders){
        if(body===undefined){
            return this.httpClient.delete(this.createCompleteRoute(route,this.enviromentUrlService.urlAddress));
            
        }
        else
        {
            let token=localStorage.getItem('token');
            let headers = new HttpHeaders();
            headers = headers.set('Authorization', token);
            return this.httpClient.request('delete',this.createCompleteRoute(route,this.enviromentUrlService.urlAddress),{body:body, headers:headers},);
        }
    }


    public createCompleteRoute(route: string, envAddress: string) {
        return `${envAddress}/${route}`;
    }

}