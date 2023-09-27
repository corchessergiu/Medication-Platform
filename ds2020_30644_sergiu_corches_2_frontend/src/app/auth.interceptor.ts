 import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let headers = new HttpHeaders();
        headers = headers.append('Access-Control-Allow-Origin', '*');
        if(request.url === "http://localhost:8081/login") {
            headers = headers.append('Content-Type','application/json')
        }
    
        if (localStorage.getItem('token') != null) {
            headers = headers.append('Authorization', localStorage.getItem('token'));
        }

        request = request.clone({
            headers: headers
        });

        return next.handle(request);
    }
}

 