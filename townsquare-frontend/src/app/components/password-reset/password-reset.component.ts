import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.scss']
})
export class PasswordResetComponent implements OnInit {
  showChangePasswordError: boolean = false;
  showPasswordReset: boolean = true;
  date: Date = new Date("00-00-0000");
  maxDate = new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate();
  user: User = {
    userId: 0,
    firstName: '',
    lastName: '',
    username: '',
    password: '',
    profileImg: '',
    email: '',
    profileBio: '',
    backgroundImg: '',
    birthDate: this.date,
    birthplace: '',
    homeTown: ''
  }
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {

  }

  verifyUser(): void {
    this.user = {
      userId: 0,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      username: this.user.username,
      password: '',
      profileImg: '',
      email: '',
      profileBio: '',
      backgroundImg: '',
      birthDate: this.user.birthDate,
      birthplace: '',
      homeTown: ''
    }
    this.userService.findUserByUsername(this.user.username).subscribe((user: User) => {
      if (this.user.username == user.username && this.user.birthDate == user.birthDate &&
        this.user.firstName == user.firstName && this.user.lastName == user.lastName) {
        this.showChangePasswordError = false;
        this.showPasswordReset = true;
      }
    },
      (error) => {
        this.showChangePasswordError = true;
        console.log(error);
      })
  }

  resetPassword(): void {
    this.userService.findUserByUsername(this.user.username).subscribe((user: User) => {
      user.password = this.user.password;
      this.user = user;
    });
    this.userService.updateUser(this.user).subscribe(() => {
      this.router.navigate(['/login']);
      this.showChangePasswordError = false;
      this.showPasswordReset = false;
    });
  }

}
