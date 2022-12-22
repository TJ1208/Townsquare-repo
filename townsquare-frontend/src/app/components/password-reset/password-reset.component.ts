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
  maxDate = new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate();
  date: Date = new Date("00-00-0000");
  showResetError: boolean = false;
  showPasswordReset: boolean = false;
  showSuccessMessage: boolean = false;
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
    birthDate: this.date
  }
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  verifyUser(): void {
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
      birthDate: this.user.birthDate
    }
    this.userService.getUserByUsername(this.user.firstName + " " + this.user.lastName).subscribe((users: User[]) => {
      if (users.length != 0) {
        if (users[0].username == this.user.username && users[0].firstName == this.user.firstName
          && users[0].lastName == this.user.lastName && users[0].birthDate == this.user.birthDate) {
          this.user = users[0];
          this.showPasswordReset = true;
          this.showResetError = false;
        } else {
          this.showResetError = true;
        }
      } else {
        this.showResetError = true;
      }
    });
  }

  resetPassword(password: any): void {
    this.user.birthDate = new Date(new Date(this.user.birthDate).getTime() + 8.64e+7);
    this.user.password = password;
    this.userService.updateUser(this.user).subscribe(() => {
      this.showSuccessMessage = true;
      setTimeout(() => {
        this.router.navigate(["/login"]);
      }, 2000);
    });
  }

}
