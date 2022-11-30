import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { User } from './../../models/User';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FriendService } from 'src/app/services/friend/friend.service';
import { Friend } from 'src/app/models/Friend';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  friends: Friend[] = [];
  userId: any = localStorage.getItem("visitedUser");
  userProfile: User = {
    userId: 0,
    firstName: '',
    lastName: '',
    username: '',
    password: '',
    profileImg: '',
    email: '',
    profileBio: '',
    backgroundImg: '',
    birthDate: new Date(),
    birthplace: '',
    homeTown: ''
  };
  constructor(private userService: UserService, private modalService: NgbModal,
    private friendService: FriendService, private router: Router) {
  }

  ngOnInit(): void {
    this.getUserProfile();
  }

  getUserProfile() {
    console.log(this.userId);
    this.userService.getUserById(parseInt(this.userId)).subscribe((userProfile) => {
      this.userProfile = userProfile;
      console.log(userProfile);
      this.getUserFriends();
    })
  }

  getUserFriends() {
    console.log(this.userProfile.userId);
    this.friendService.getAllUserFriends(this.userProfile.userId).subscribe((friends) => {
      this.friends = friends;
      console.log(this.friends);
      console.log(friends);
    })
  }

  updateBackgroundPic() {
    this.userProfile.backgroundImg = (<HTMLInputElement>document.getElementById("imageuri")).value;
    this.userService.updateUser(this.userProfile).subscribe();
  }

  updateProfilePic() {
    this.userProfile.profileImg = (<HTMLInputElement>document.getElementById("imageuri2")).value;
    this.userService.updateUser(this.userProfile).subscribe();
  }



  updatePictureModal(picture: any) {
    this.modalService.open(picture, { size: 'sm', centered: true });
  }

  userProfileRedirect(friend: Friend): void {
    localStorage.setItem("visitedUser", friend.friend.userId.toString());
    this.userId = localStorage.getItem("visitedUser");
    this.getUserProfile();
    if (friend.friend.userId.toString() == localStorage.getItem("userId")) {
      this.router.navigate(['/profile']);
    }
  }

}
