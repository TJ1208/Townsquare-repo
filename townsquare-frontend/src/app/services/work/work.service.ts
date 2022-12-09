import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Work } from 'src/app/models/Work';

@Injectable({
  providedIn: 'root'
})
export class WorkService {

  constructor(private http: HttpClient) { }

  getAllWorkplaces(): Observable<Work[]> {
    return this.http.get<Work[]>(`https://townsquare-backend.azurewebsites.net/api/work`);
  }

  getAllUserWorkplaces(userId: number): Observable<Work[]> {
    return this.http.get<Work[]>(`https://townsquare-backend.azurewebsites.net/api/work/${userId}`);
  }

  getWorkplaceById(workplaceId: number): Observable<Work> {
    return this.http.get<Work>(`https://townsquare-backend.azurewebsites.net/api/work/id/${workplaceId}`);
  }

  addWorkplace(workplace: Work): Observable<String> {
    return this.http.post<String>(`https://townsquare-backend.azurewebsites.net/api/work/add`, workplace);
  }

  updateWorkplace(workplace: Work): Observable<String> {
    return this.http.put<String>(`https://townsquare-backend.azurewebsites.net/api/work/update`, workplace);
  }

  deleteWorkplace(workplaceId: number): Observable<String> {
    return this.http.delete<String>(`https://townsquare-backend.azurewebsites.net/api/work/delete/${workplaceId}`);
  }
  
}
