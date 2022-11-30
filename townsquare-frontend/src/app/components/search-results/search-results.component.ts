import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.scss']
})
export class SearchResultsComponent implements OnInit {
  searchString: any = localStorage.getItem("search");
  users: User[] = [];
  userId: any = localStorage.getItem("userId");
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userId = parseInt(this.userId);
    this.searchUsers();
  }

  searchUsers(): void {
    this.userService.getUserByUsername(this.searchString).subscribe((users: User[]) => {
      users
      this.users = users;
    })
  }

  viewUser(user: User): void {
    localStorage.setItem("visitedUser", user.userId.toString());
    this.router.navigate(['/user']);
  }

}
