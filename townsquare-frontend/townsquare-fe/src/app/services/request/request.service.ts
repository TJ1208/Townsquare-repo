import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  
  constructor(private http: HttpClient) { }

  getAllRequests(): Observable<Request[]> {
    return this.http.get<Request[]>(`http://localhost:8181/api/request`);
  }

  getAllUserRequests(userId: number): Observable<Request[]> {
    return this.http.get<Request[]>(`http://localhost:8181/api/request/${userId}`);
  }

  getRequestById(receiverId: number, requesterId: number): Observable<Request> {
    return this.http.get<Request>(`http://localhost:8181/api/request/${receiverId}/${requesterId}`);
  }

  addRequest(request: Request): Observable<String> {
    return this.http.post<String>(`http://localhost:8181/api/request/send`, request);
  }

  updateRequest(request: Request): Observable<String> {
    return this.http.put<String>(`http://localhost:8181/api/request/update`, request);
  }

  deleteRequest(receiverId: number, requesterId: number): Observable<String> {
    return this.http.delete<String>(`http://localhost:8181/api/request/delete/${receiverId}/${requesterId}`);
  }
  
}
