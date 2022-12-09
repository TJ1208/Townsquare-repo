import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Friend } from 'src/app/models/Friend';

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  constructor(private http: HttpClient) { }

  getAllFriends(): Observable<Friend[]> {
    return this.http.get<Friend[]>(`https://townsquare-backend.azurewebsites.net/api/friend`);
  }

  getAllUserFriends(userId: number): Observable<Friend[]> {
    return this.http.get<Friend[]>(`https://townsquare-backend.azurewebsites.net/api/friend/${userId}`);
  }

  getFriendById(userId: number, friendId: number): Observable<Friend> {
    return this.http.get<Friend>(`https://townsquare-backend.azurewebsites.net/api/friend/${userId}/${friendId}`);
  }

  addFriend(friend: Friend): Observable<String> {
    return this.http.post<String>(`https://townsquare-backend.azurewebsites.net/api/friend/add`, friend);
  }

  updateFriend(friend: Friend): Observable<String> {
    return this.http.put<String>(`https://townsquare-backend.azurewebsites.net/api/friend/update`, friend);
  }

  deleteFriend(userId: number, friendId: number): Observable<String> {
    return this.http.delete<String>(`https://townsquare-backend.azurewebsites.net/api/friend/delete/${userId}/${friendId}`);
  }
 
}
