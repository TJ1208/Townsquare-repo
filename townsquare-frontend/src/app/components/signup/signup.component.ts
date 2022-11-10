import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth/auth.service';
import { LoginService } from 'src/app/services/login/login.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  maxDate = new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate();
  date: Date = new Date("00-00-0000");
  loginForm: any;
  showSignupError: boolean = false;
  userEmail: string = "";
  user: User = {
    userId: 0,
    username: "",
    password: "",
    profileBio: "",
    profileImg: "",
    email: "",
    firstName: "",
    lastName: "",
    birthplace: "",
    backgroundImg: "",
    homeTown: "",
    date: this.date
  }
  constructor(private loginService: LoginService, private authService: AuthService,
    private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    if (new Date().getDate() <= 9) {
      this.maxDate = new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-0" + new Date().getDate();
    }
    console.log(this.maxDate);
    console.log(this.user.date instanceof Date);
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/home']);
    }
  }

  registerUser() {
    this.user = {
      userId: 0,
      username: this.user.email,
      password: this.user.password,
      profileBio: "",
      profileImg: "https://res.cloudinary.com/dwzhlnnwa/image/upload/v1668018658/townsquare/avatar_2x_yuavg7.png",
      email: this.user.email,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      birthplace: "",
      backgroundImg: "",
      homeTown: "",
      date: this.user.date
    }

    this.userService.addUser(this.user).subscribe((user: any) => {
      this.loginForm = {
        username: this.user.email,
        password: this.user.password
      }
      console.log(this.loginForm);
      this.showSignupError = false;
      this.loginService.login(this.loginForm).subscribe((response: any) => {
        this.authService.setToken(response.jwtToken);
        console.log(response.user);
        if (response.user) {
          localStorage.setItem("userId", response.user.userId);
        }
        this.router.navigate(['/home']);
      }, (error) => { 
        this.router.navigate(['/signup']);
        console.log(error);
      });
    }, (error) => {
      this.showSignupError = true;
      console.log(error);
    });
    this.userEmail = this.user.email;
  }

}
