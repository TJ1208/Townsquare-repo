import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  requestHeader = new HttpHeaders(
    { "No-Auth": "True" }
  )

  constructor(private http: HttpClient) { }

  login(loginData: NgForm): Observable<any> {
    return this.http.post<any>(`http://localhost:8181/authenticate`, loginData, { headers: this.requestHeader });
  }

}
