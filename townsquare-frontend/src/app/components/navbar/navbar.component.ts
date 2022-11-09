import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  constructor(private router: Router, private authService: AuthService,
    private userService: UserService) { }

  ngOnInit(): void {
    this.getCurrentUserImage();
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

}
