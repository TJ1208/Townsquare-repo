import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Friend } from 'src/app/models/Friend';
import { Request } from 'src/app/models/Request';
import { User } from 'src/app/models/User';
import { FriendService } from 'src/app/services/friend/friend.service';
import { RequestService } from 'src/app/services/request/request.service';

@Component({
  selector: 'app-friend-requests',
  templateUrl: './friend-requests.component.html',
  styleUrls: ['./friend-requests.component.scss']
})
export class FriendRequestsComponent implements OnInit {
  requests: Request[] = [];
  sentRequests: Request[] = [];
  userId: any = localStorage.getItem("userId");
  sentRequests$ = new BehaviorSubject<Request[]>([]);
  requests$ = new BehaviorSubject<Request[]>([]);
  cast = this.sentRequests$.asObservable();
  castRequests = this.requests$.asObservable();
  constructor(private requestService: RequestService, private router: Router,
    private friendService: FriendService) { }

  ngOnInit(): void {
    this.getFriendRequests();
    this.getSentRequests();
    this.cast.subscribe((sentRequests) => {
      this.sentRequests = sentRequests;
    })
    this.castRequests.subscribe((requests) => {
      this.requests = requests;
    })
  }

  retrieveSentRequests(sentRequests: Request[]) {
    this.sentRequests$.next(sentRequests);
  }

  retrieveRequests(requests: Request[]) {
    this.requests$.next(requests);
  }

  viewUser(user: User): void {
    localStorage.setItem("visitedUser", user.userId.toString());
    this.router.navigate(['/user']);
  }

  getFriendRequests(): void {
    this.requestService.getAllUserRequests(parseInt(this.userId)).subscribe((requests: any) => {
      this.cast = requests;
      this.retrieveRequests(requests);
    })
  }

  getSentRequests(): void {
    this.requestService.getAllUserSentRequests(parseInt(this.userId)).subscribe((sentRequests: any) => {
      this.cast = sentRequests;
      this.retrieveSentRequests(sentRequests);
    })
  }

  acceptFriendRequest(request: Request): void {
    let friendReceiver: Friend = {
      friendId: {
        userId: parseInt(this.userId),
        friendId: request.requester.userId
      },
      user: request.receiver,
      friend: request.requester
    }

    let friendRequester: Friend = {
      friendId: {
        userId: request.requester.userId,
        friendId: parseInt(this.userId)
      },
      user: request.requester,
      friend: request.receiver
    }

    this.friendService.addFriend(friendReceiver).subscribe();
    this.friendService.addFriend(friendRequester).subscribe();

    this.requestService.deleteRequest(request.receiver.userId, request.requester.userId).subscribe();
    this.requestService.deleteRequest(request.requester.userId, request.receiver.userId).subscribe(() => {
      this.getFriendRequests();
    });
  }

  declineFriendRequest(request: Request): void {
    this.requestService.deleteRequest(request.receiver.userId, request.requester.userId).subscribe();
    this.requestService.deleteRequest(request.requester.userId, request.receiver.userId).subscribe(() => {
      this.getFriendRequests();
    });
  }

  deleteSentRequest(request: Request): void {
    this.requestService.deleteRequest(request.requester.userId, request.receiver.userId).subscribe(() => {
      this.getSentRequests()
    })
  }

}
