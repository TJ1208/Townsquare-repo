import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/models/Address';
import { Education } from 'src/app/models/Education';
import { User } from 'src/app/models/User';
import { AddressService } from 'src/app/services/address/address.service';
import { EducationService } from 'src/app/services/education/education.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-profile-content',
  templateUrl: './profile-content.component.html',
  styleUrls: ['./profile-content.component.scss']
})
export class ProfileContentComponent implements OnInit {
  user: User = {
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
  addresses: Address[] = [];
  educations: Education[] = [];
  userId: any;
  constructor(private userService: UserService, private router: Router, private addressService: AddressService,
    private educationService: EducationService) { }

  ngOnInit(): void {
    if (this.router.url == "/profile") {
      this.userId = localStorage.getItem("userId")
    } else {
      this.userId = localStorage.getItem("visitedUser");
    }
    this.getUser();
    this.getUserAddresses();
    this.getUserEducation();
  }

  getUser(): void {
    this.userService.getUserById(parseInt(this.userId)).subscribe((user) => {
      this.user = user;
    })
  }

  getUserAddresses(): void {
    this.addressService.getAllUserAddressses(parseInt(this.userId)).subscribe((addresses: Address[]) => {
      this.addresses = addresses;
    })
  }

  getUserEducation(): void {
    this.educationService.getAllUserEducations(parseInt(this.userId)).subscribe((educations: Education[]) => {
      this.educations = educations;
      console.log(educations);
    })
  }

  formatDate(date: Date): string {
    const monthNames: String[] = ["January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
    ];
    let newDate: Date = new Date(date);
    let dateString: string = monthNames[newDate.getMonth()] + " " + (newDate.getDate() + 1) + `, ` + newDate.getFullYear();
    return dateString;
  }

}
