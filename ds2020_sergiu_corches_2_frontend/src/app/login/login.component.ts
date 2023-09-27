import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { RepositoryService } from 'src/services/repository.service';
import { AuthService } from 'src/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 
  public loginForm: FormGroup;


  constructor(  private readonly authService: AuthService,
    private readonly repositoryService: RepositoryService,
    private readonly router: Router
    ) {}


  ngOnInit() {
    this.loginForm = new FormGroup({
      username: new FormControl(''),
      password: new FormControl('')
    });
  }

public createUser(loginFormValue){
  if (loginFormValue.username.length > 0 && loginFormValue.password.length > 0) {
    this.login(loginFormValue.username, loginFormValue.password)
  }
}

public login(username: string, password: string) {

  var user = { username: username, password: password }; 
 this.authService.loginAndRedirect(user);
}
}
