import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Request } from '../../models/Request';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  
  constructor(private http: HttpClient) { }

  getAllRequests(): Observable<Request[]> {
    return this.http.get<Request[]>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/request`);
  }

  getAllUserRequests(userId: number): Observable<Request[]> {
    return this.http.get<Request[]>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/request/${userId}`);
  }

  getAllUserSentRequests(userId: number): Observable<Request[]> {
    return this.http.get<Request[]>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/request/sent/${userId}`);
  }

  getRequestById(receiverId: number, requesterId: number): Observable<Request> {
    return this.http.get<Request>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/request/${receiverId}/${requesterId}`);
  }

  addRequest(request: Request): Observable<any> {
    return this.http.post<any>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/request/send`, request);
  }

  updateRequest(request: Request): Observable<String> {
    return this.http.put<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/request/update`, request);
  }

  deleteRequest(receiverId: number, requesterId: number): Observable<String> {
    return this.http.delete<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/request/delete/${receiverId}/${requesterId}`);
  }
  
}
