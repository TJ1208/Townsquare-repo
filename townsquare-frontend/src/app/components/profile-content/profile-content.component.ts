import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ICountry } from 'country-state-city';
import { Address } from 'src/app/models/Address';
import { Education } from 'src/app/models/Education';
import { Friend } from 'src/app/models/Friend';
import { User } from 'src/app/models/User';
import { AddressService } from 'src/app/services/address/address.service';
import { EducationService } from 'src/app/services/education/education.service';
import { FriendService } from 'src/app/services/friend/friend.service';
import { UserService } from 'src/app/services/user/user.service';
import { Country, State, City } from 'country-state-city';
import { ICity, IState } from 'country-state-city/lib/interface';
import { NgForm } from '@angular/forms';
import { Work } from 'src/app/models/Work';
import { WorkService } from 'src/app/services/work/work.service';
import { BehaviorSubject, Observable } from 'rxjs';

@Component({
  selector: 'app-profile-content',
  templateUrl: './profile-content.component.html',
  styleUrls: ['./profile-content.component.scss']
})
export class ProfileContentComponent implements OnInit {
  maxDate = new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate();
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
  user$ = new BehaviorSubject<User>(this.user);
  castUser = this.user$.asObservable();
  addresses: Address[] = [];
  addresses$ = new BehaviorSubject<Address[]>([]);
  castAddress = this.addresses$.asObservable();
  educations: Education[] = [];
  friends: Friend[] = [];
  workplaces: Work[] = [];
  workplaces$ = new BehaviorSubject<Work[]>([]);
  castWorkplaces = this.workplaces$.asObservable();
  userId: any;
  countries: ICountry[] = [];
  states: IState[] = [];
  cities: ICity[] = [];
  currentCountries: ICountry[] = [];
  currentStates: IState[] = [];
  currentCities: ICity[] = [];
  url: string = this.router.url;
  constructor(private userService: UserService, private router: Router, private addressService: AddressService,
    private educationService: EducationService, private friendService: FriendService,
    private modalService: NgbModal, private workService: WorkService) { }

  ngOnInit(): void {
    if (this.url == "/profile") {
      this.userId = localStorage.getItem("userId")
    } else {
      this.userId = localStorage.getItem("visitedUser");
    }
    this.getUser();
    this.getUserAddresses();
    this.getUserEducation();
    this.getUserFriends();
    this.getUserWork();
    this.castUser.subscribe((user) => {
      this.user = user;
    })
    this.castAddress.subscribe((addresses: Address[]) => {
      this.addresses = addresses;
    })
    this.castWorkplaces.subscribe((workplaces: Work[]) => {
      this.workplaces = workplaces;
    })
  }

  retrieveUser(user: User): void {
    this.user$.next(user);
  }

  getUser(): void {
    this.userService.getUserById(parseInt(this.userId)).subscribe((user: any) => {
      this.castUser = user;
      this.retrieveUser(user);
    })
  }

  updateUser(fName: string, lName: string, birthDate: string, state: any, city: any,
    currentState: any, currentCity: any, company: string, jobCity: string, role: string, country: any): void {
    this.userService.updateUser(this.user).subscribe();
    let newUser: User = {
      userId: this.user.userId,
      firstName: fName,
      lastName: lName,
      username: this.user.username,
      password: this.user.password,
      profileImg: this.user.profileImg,
      email: this.user.email,
      profileBio: this.user.profileBio,
      backgroundImg: this.user.backgroundImg,
      birthDate: new Date(birthDate),
      birthplace: city == "City" || state == "State" ? country : city + ", " + state,
      homeTown: ''
    }

    let newAddress: Address = {
      addressId: this.addresses.length == 0 ? 0 : this.addresses[0].addressId,
      city: currentCity == 'City' ? '' : currentCity,
      state: currentState == 'State' ? country : currentState,
      street: '',
      zipcode: '',
      country: '',
      apartmentNum: '',
      user: newUser
    }

    let newWorkplace: Work = {
      workplaceId: this.workplaces.length == 0 ? 0 : this.workplaces[0].workplaceId,
      company: company,
      position: role,
      city: jobCity == '' ? country : jobCity,
      description: '',
      startDate: new Date(),
      endDate: new Date(),
      user: newUser
    }

    this.userService.updateUser(newUser).subscribe();

    if (newAddress.addressId == 0) {
      this.addressService.addAddress(newAddress).subscribe();
    } else {
      this.addressService.updateAddress(newAddress).subscribe();
    }

    if (newWorkplace.workplaceId == 0) {
      this.workService.addWorkplace(newWorkplace).subscribe();
    } else {
      this.workService.updateWorkplace(newWorkplace).subscribe();
    }

    this.getUser();
  }

  filterStates(countryCode: string): void {
    this.states = State.getStatesOfCountry(countryCode).filter((state) => state.isoCode.startsWith);
    this.cities = [];
  }

  filterCity(stateCode: string, countryCode: string): void {
    this.cities = City.getCitiesOfState(countryCode, stateCode);
  }

  filterCurrentStates(countryCode: string): void {
    this.currentStates = State.getStatesOfCountry(countryCode).filter((state) => state.isoCode.startsWith);
    this.currentCities = [];
  }

  filterCurrentCity(stateCode: string, countryCode: string): void {
    this.currentCities = City.getCitiesOfState(countryCode, stateCode);
  }

  routeToUser(friend: Friend): void {
    this.url = this.router.url;
    let userId: any = localStorage.getItem("userId");
    if (this.url == '/user' && friend.friend.userId != parseInt(userId)) {
      localStorage.setItem("visitedUser", friend.friend.userId.toString());
      location.reload();
    } else if (this.url == "/profile") {
      localStorage.setItem("visitedUser", friend.friend.userId.toString());
      this.router.navigate(["/user"]);
    } else {
      this.router.navigate(["/profile"]);
    }
  }

  updateProfileModal(picture: any) {
    this.modalService.open(picture, { size: 'sm', centered: true, scrollable: true });
    this.countries = Country.getAllCountries();
    this.currentCountries = this.countries;
  }


  getUserFriends(): void {
    this.friendService.getAllUserFriends(parseInt(this.userId)).subscribe((friends: Friend[]) => {
      this.friends = friends;
    })
  }

  retrieveAddresses(addresses: Address[]): void {
    this.addresses$.next(addresses);
  }

  getUserAddresses(): void {
    this.addressService.getAllUserAddressses(parseInt(this.userId)).subscribe((addresses: any) => {
      this.castAddress = addresses;
      this.retrieveAddresses(addresses);
    })
  }

  getUserEducation(): void {
    this.educationService.getAllUserEducations(parseInt(this.userId)).subscribe((educations: Education[]) => {
      this.educations = educations;
    })
  }

  retrieveWorkplaces(workplaces: Work[]): void {
    this.workplaces$.next(workplaces);
  }

  getUserWork(): void {
    this.workService.getAllUserWorkplaces(parseInt(this.userId)).subscribe((workplaces: any) => {
      this.castWorkplaces = workplaces;
      this.retrieveWorkplaces(workplaces);
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

