import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { User } from './../../models/User';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  newPicture$ = new BehaviorSubject<string>('');
  cast = this.newPicture$.asObservable();
  newPicture: string = "";
  userId: any = localStorage.getItem("userId");
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
  constructor(private userService: UserService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getUserProfile();
  }

  getUserProfile() {
    this.userService.getUserById(parseInt(this.userId)).subscribe((userProfile) => {
     
      this.userProfile = userProfile;
      console.log(this.userProfile);
    })
  }

  updateBackgroundPic() {
    console.log(this.userProfile);
    this.userProfile.backgroundImg = (<HTMLInputElement>document.getElementById("imageuri")).value;
    console.log(this.userProfile);
    this.userService.updateUser(this.userProfile).subscribe();
  }

  updateProfilePic() {
    this.userProfile.backgroundImg = (<HTMLInputElement>document.getElementById("imageuri2")).value;
    this.userService.updateUser(this.userProfile).subscribe();
  }

  updatePictureModal(picture: any) {
    this.modalService.open(picture, { size: 'md', centered: true });
  }

  getUploadPicture() {
    console.log((<HTMLInputElement>document.getElementById("imageuri")).value);
    return (<HTMLInputElement>document.getElementById("imageuri")).value;
  }

}
