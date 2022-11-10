import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  userId: any = localStorage.getItem("userId");
  userProfile: any;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUserProfile();
  }

  getUserProfile(): void {
    this.userService.getUserById(parseInt(this.userId)).subscribe((userProfile) => {
      this.userProfile = userProfile;
      console.log(this.userProfile);
    })
  }

}
