import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService, private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/home']);
    }
  }

  login(loginForm: NgForm) {
    this.loginService.login(loginForm.value).subscribe((response: any) => {
      this.authService.setToken(response.jwtToken);
      console.log(response.user);
      if(response.user) {
        this.loginService.setCurrentUser(response.user);
      }
      
      this.router.navigate(['/home']);
    }, (error) => {
       console.log(error);
    });
  }

}
