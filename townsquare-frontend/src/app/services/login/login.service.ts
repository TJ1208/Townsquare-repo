import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  requestHeader = new HttpHeaders(
    { "No-Auth": "True" }
  )

  constructor(private http: HttpClient) { }

  login(loginData: NgForm): Observable<any> {
    return this.http.post<any>(`https://townsquare-backend.azurewebsites.net/authenticate`, loginData, { headers: this.requestHeader });
  }

}
