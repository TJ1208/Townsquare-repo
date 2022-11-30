import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth/auth.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  userId: any = localStorage.getItem("userId");
  userImg: any;
  users: User[] = [];
  constructor(private router: Router, private authService: AuthService,
    private userService: UserService) { }

  ngOnInit(): void {
    this.getCurrentUserImage();
    this.getAllUsers();
  }

  getAllUsers(): void {
    this.userService.getAllUsers().subscribe((users) => {
      this.users = users;
      console.log(this.users);
    })
  }
  getCurrentUserImage(): void {
    this.userService.getUserById(parseInt(this.userId)).subscribe((user) => {
      this.userImg = user.profileImg;
    })
  }

  logout(): void {
    this.authService.clear();
    this.router.navigate(['/login'])
  }

  searchUser(username: string): void {
    localStorage.setItem("search", username);
    this.router.navigate(['/user/search']);
    if (this.router.url == "/user/search") {
      setTimeout(() => {
        location.reload();
      }, 1)
    }
  }

}
