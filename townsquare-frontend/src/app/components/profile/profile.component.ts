import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { User } from './../../models/User';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BehaviorSubject } from 'rxjs';
import { FriendService } from 'src/app/services/friend/friend.service';
import { Friend } from 'src/app/models/Friend';
import { Router } from '@angular/router';
import { ImageService } from 'src/app/services/image/image.service';
import { Image } from 'src/app/models/Image';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/post/post.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  newPicture$ = new BehaviorSubject<string>('');
  cast = this.newPicture$.asObservable();
  userId: any = localStorage.getItem("userId");
  friends: Friend[] = [];
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
      private friendService: FriendService, private router: Router, private imageService: ImageService,
      private postService: PostService) { }

  ngOnInit(): void {
    this.getUserProfile();
  }

  getUserProfile() {
    this.userService.getUserById(parseInt(this.userId)).subscribe((userProfile) => {
      this.userProfile = userProfile;
      this.getUserFriends();
    })
  }

  getUserFriends() {
    this.friendService.getAllUserFriends(this.userProfile.userId).subscribe((friends) => {
      this.friends = friends;
    })
  }

  updateBackgroundPic(): void {
    this.userProfile.backgroundImg = (<HTMLInputElement>document.getElementById("imageuri")).value;
    this.userService.updateUser(this.userProfile).subscribe();
    let image: Image = {
      imageId: 0,
      imageUrl: this.userProfile.backgroundImg,
      imageDate: new Date(),
      user: this.userProfile
    }
    this.imageService.addImage(image).subscribe();
  }

  postBackgroundPic(): void {
    this.updateBackgroundPic()
    let post: Post = {
      postId: 0,
      title: '',
      description: this.userProfile.firstName + " " + this.userProfile.lastName
      + " updated their background picture.",
      likes: 0,
      dislikes: 0,
      shares: 0,
      imageUrl: this.userProfile.backgroundImg,
      date: new Date(new Date().getTime() + 8.64e+7),
      user: this.userProfile
    }
    this.postService.addPost(post).subscribe();
  }

  postProfilePic(): void {
    this.updateProfilePic()
    let post: Post = {
      postId: 0,
      title: '',
      description: this.userProfile.firstName + " " + this.userProfile.lastName
      + " updated their profile picture.",
      likes: 0,
      dislikes: 0,
      shares: 0,
      imageUrl: this.userProfile.profileImg,
      date: new Date(new Date().getTime() + 8.64e+7),
      user: this.userProfile
    }
    this.postService.addPost(post).subscribe();
  }

  updateProfilePic() {
    this.userProfile.profileImg = (<HTMLInputElement>document.getElementById("imageuri2")).value;
    this.userService.updateUser(this.userProfile).subscribe();
    let image: Image = {
      imageId: 0,
      imageUrl: this.userProfile.profileImg,
      imageDate: new Date(),
      user: this.userProfile
    }
    this.imageService.addImage(image).subscribe();
  }



  updatePictureModal(picture: any) {
    this.modalService.open(picture, { size: 'sm', centered: true });
  }

  userProfileRedirect(friend: Friend): void {
    localStorage.setItem("visitedUser", friend.friend.userId.toString());
    this.router.navigate(['/user']);
  }

  

}
