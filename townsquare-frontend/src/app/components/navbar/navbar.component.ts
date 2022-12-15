import { NgLocalization } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth/auth.service';
import { RequestService } from 'src/app/services/request/request.service';
import { UserService } from 'src/app/services/user/user.service';
import { Request } from '../../models/Request';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  userId: any = localStorage.getItem("userId");
  userImg: any;
  users: User[] = [];
  userNames: User[] = [];
  keyword: string = "firstName";
  searchWord: string = "";
  requests: Request[] = [];
  requests$ = new BehaviorSubject<Request[]>([]);
  cast = this.requests$.asObservable();
  constructor(private router: Router, private authService: AuthService,
    private userService: UserService, private requestService: RequestService) { }

  ngOnInit(): void {
    this.getCurrentUserImage();
    this.getAllUsers();
    this.getFriendRequests();
    this.cast.subscribe((requests: Request[]) => {
      this.requests = requests;
    })
  }

  getAllUsers(): void {
    this.userService.getAllUsers().subscribe((users) => {
      this.userNames = users.filter((user) => user.userId != this.userId).filter((user) => user.firstName = user.firstName + ' ' + user.lastName);
      this.users = users;
    })
  }

  getCurrentUserImage(): void {
    this.userService.getUserById(parseInt(this.userId)).subscribe((user) => {
      this.userImg = user.profileImg;
    })
  }

  retrieveRequests(requests: Request[]): void {
    this.requests$.next(requests);
  }

  getFriendRequests(): void {
    this.requestService.getAllUserRequests(parseInt(this.userId)).subscribe((requests: any) => {
      this.cast = requests;
      this.retrieveRequests(requests);
    })
  }

  selectEvent(user: User): void {
    localStorage.setItem("visitedUser", user.userId.toString());
    if (this.router.url == '/user') {
      location.reload();
    }
    this.router.navigate(['/user']);
  }

  selectEventPicture(userId: number): void {
    localStorage.setItem("visitedUser", userId.toString());
    if (this.router.url == '/user') {
      location.reload();
    }
    this.router.navigate(['/user']);
  }

  updateSearchWord(search: string): void {
    this.searchWord = search;
  }

  logout(): void {
    this.authService.clear();
    this.router.navigate(['/login'])
  }

  searchUser(username: string): void {
    localStorage.setItem("search", username);
    this.router.navigate(['/user-search']);
    if (this.router.url == "/user-search") {
      setTimeout(() => {
        location.reload();
      }, 1)
    }
  }

}
