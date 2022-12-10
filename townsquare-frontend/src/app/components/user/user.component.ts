import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { User } from './../../models/User';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FriendService } from 'src/app/services/friend/friend.service';
import { Friend } from 'src/app/models/Friend';
import { Router } from '@angular/router';
import { RequestService } from 'src/app/services/request/request.service';
import { Request } from 'src/app/models/Request';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  friends: Friend[] = [];
  userId: any = localStorage.getItem("visitedUser");
  userId2: any = localStorage.getItem("userId");
  showAddFriend: boolean = true;
  showPendingRequest: boolean = false;
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
    private friendService: FriendService, private router: Router, private requestService: RequestService) {
  }

  ngOnInit(): void {
    this.getUserProfile();
    this.findRequest();
  }

  getUserProfile() {
    this.userService.getUserById(parseInt(this.userId)).subscribe((userProfile) => {
      this.userProfile = userProfile;
      this.getUserFriends();
    })
  }

  removeFriend(): void {
    this.friendService.deleteFriend(this.userId, this.userId2).subscribe(() => {
      this.showAddFriend = true;
      this.showPendingRequest = false;
    })
    this.friendService.deleteFriend(this.userId2, this.userId).subscribe()
  }

  sendFriendRequest(): void {
    let currentId: any = localStorage.getItem('userId');
    this.userService.getUserById(parseInt(currentId)).subscribe((user: User) => {
      let request: Request = {
        requestId: {
          receiverId: this.userProfile.userId,
          requesterId: user.userId
        },
        receiver: this.userProfile,
        requester: user
      }
      this.requestService.addRequest(request).subscribe(() => {
        this.showAddFriend = false;
        this.showPendingRequest = true;
      })
    })

  }

  findRequest(): void {
    this.requestService.getRequestById(this.userId, parseInt(this.userId2)).subscribe((request: Request) => {
      if (request) {
        this.showPendingRequest = true;
      }
    })
    this.requestService.getRequestById(parseInt(this.userId2), this.userId).subscribe((request: Request) => {
      if (request) {
        this.showPendingRequest = true;
      } else {
        this.showPendingRequest = false;
      }
    })
  }

  getUserFriends() {
    this.friendService.getAllUserFriends(this.userProfile.userId).subscribe((friends) => {
      this.friends = friends;
      if (this.friends.find((friend) => friend.friend.userId == parseInt(this.userId2))) {
        this.showAddFriend = false;
      }
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
    } else {
      location.reload();
    }
  }

}