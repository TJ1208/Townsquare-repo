import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Friend } from 'src/app/models/Friend';
import { User } from 'src/app/models/User';
import { FriendService } from 'src/app/services/friend/friend.service';
import { RequestService } from 'src/app/services/request/request.service';
import { UserService } from 'src/app/services/user/user.service';
import { Request } from '../../models/Request';
@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.scss']
})
export class SearchResultsComponent implements OnInit {
  searchString: any = localStorage.getItem("search");
  users: User[] = [];
  userId: any = localStorage.getItem("userId");
  isFriend: boolean = false;
  friends: Friend[] = []
  friends$ = new BehaviorSubject<Friend[]>([]);
  castFriends = this.friends$.asObservable();
  requests: Request[] = [];
  requests$ = new BehaviorSubject<Request[]>([]);
  castRequests = this.requests$.asObservable();
  friendRequests: Request[] = [];
  friendRequests$ = new BehaviorSubject<Request[]>([]);
  castFriendRequests = this.friendRequests$.asObservable();
  user: any;
  constructor(private userService: UserService, private router: Router,
    private friendService: FriendService, private requestService: RequestService) { }

  ngOnInit(): void {
    this.searchUsers();
    this.getAllUserFriends();
    this.getCurrentUser();
    this.getRequests();
    this.getFriendRequests();
    this.castRequests.subscribe((requests) => {
      this.requests = requests;
    })
    this.castFriends.subscribe((friends) => {
      this.friends = friends;
    })
    this.castFriendRequests.subscribe((friendRequests) => {
      this.friendRequests = friendRequests;
    })
  }

  getFriendRequests(): void {
    this.requestService.getAllUserRequests(parseInt(this.userId)).subscribe((friendRequests: any) => {
      this.castFriendRequests = friendRequests;
      this.retrieveFriendRequests(friendRequests);
    }) 
  }

  getRequests(): void {
    this.requestService.getAllUserSentRequests(parseInt(this.userId)).subscribe((requests: any) => {
      this.castRequests = requests;
      this.retrieveRequests(requests);
    })
  }

  getCurrentUser(): void {
    this.userService.getUserById(parseInt(this.userId)).subscribe((user) => {
      this.user = user;
    })
  }

  retrieveRequests(requests: Request[]) {
    this.requests$.next(requests);
  }

  retrieveFriends(friends: Friend[]) {
    this.friends$.next(friends);
  }

  retrieveFriendRequests(friendRequests: Request[]) {
    this.friendRequests$.next(friendRequests);
  }

  searchUsers(): void {
    this.userService.getUserByUsername(this.searchString).subscribe((users: User[]) => {
      this.users = users;
    })
  }

  viewUser(user: User): void {
    localStorage.setItem("visitedUser", user.userId.toString());
    this.router.navigate(['/user']);
  }

  getAllUserFriends(): void {
    this.friendService.getAllUserFriends(parseInt(this.userId)).subscribe((friends: any) => {
      this.castFriends = friends;
      this.retrieveFriends(friends);
    })
  }

  findFriend(user: User): boolean {
    if (this.friends.find((friend: Friend) => friend.friend.userId == user.userId)) {
      this.isFriend = true;
      return true;
    }
    this.isFriend = false;
    return false;
  }

  findRequest(user: User): boolean {
    if (this.requests.find((request: Request) => request.receiver.userId == user.userId)) {
      return true;
    }
    return false;
  }

  findFriendRequest(user: User): boolean {
    if (this.friendRequests.find((request: Request) => request.requester.userId == user.userId)) {
      return true;
    }
    return false;
  }

  removeFriend(user: User): void {
    this.friendService.deleteFriend(parseInt(this.userId), user.userId).subscribe();
    this.friendService.deleteFriend(user.userId, parseInt(this.userId)).subscribe(() => {
      this.getAllUserFriends();
    });

  }

  sendFriendRequest(user: User): void {
    let request: Request = {
      requestId: {
        receiverId: user.userId,
        requesterId: parseInt(this.userId)
      },
      receiver: user,
      requester: this.user
    }
    this.requestService.addRequest(request).subscribe(() => {
      this.getRequests();
    });
  }

  deleteRequest(user: User): void {
    this.requestService.deleteRequest(parseInt(this.userId), user.userId).subscribe(() => {
      this.getRequests();
    });
  }

  acceptFriendRequest(user: User): void {
    let friendReceiver: Friend = {
      friendId: {
        userId: parseInt(this.userId),
        friendId: user.userId
      },
      user: this.user,
      friend: user
    }

    let friendRequester: Friend = {
      friendId: {
        userId: user.userId,
        friendId: parseInt(this.userId)
      },
      user: user,
      friend: this.user
    }

    this.friendService.addFriend(friendReceiver).subscribe();
    this.friendService.addFriend(friendRequester).subscribe();

    this.requestService.deleteRequest(user.userId, this.userId).subscribe(() => {
      this.getFriendRequests();
      this.getRequests();
      this.getAllUserFriends();
    });
  }

  declineFriendRequest(user: User): void {
    this.requestService.deleteRequest(user.userId, this.userId).subscribe(() => {
      this.getFriendRequests();
      this.getRequests();
      this.getAllUserFriends();
    });
  }

}
