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
    return this.http.get<Friend[]>(`http://localhost:8181/api/friend`);
  }

  getAllUserFriends(userId: number): Observable<Friend[]> {
    return this.http.get<Friend[]>(`http://localhost:8181/api/friend/${userId}`);
  }

  getFriendById(userId: number, friendId: number): Observable<Friend> {
    return this.http.get<Friend>(`http://localhost:8181/api/friend/${userId}/${friendId}`);
  }

  addFriend(friend: Friend): Observable<String> {
    return this.http.post<String>(`http://localhost:8181/api/friend/add`, friend);
  }

  updateFriend(friend: Friend): Observable<String> {
    return this.http.put<String>(`http://localhost:8181/api/friend/update`, friend);
  }

  deleteFriend(userId: number, friendId: number): Observable<String> {
    return this.http.delete<String>(`http://localhost:8181/api/friend/delete/${userId}/${friendId}`);
  }
 
}
