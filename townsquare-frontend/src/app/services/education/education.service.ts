import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Education } from 'src/app/models/Education';

@Injectable({
  providedIn: 'root'
})
export class EducationService {

  constructor(private http: HttpClient) { }

  getAllEducations(): Observable<Education[]> {
    return this.http.get<Education[]>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/education`);
  }

  getAllUserEducations(userId: number): Observable<Education[]> {
    return this.http.get<Education[]>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/education/${userId}`);
  }

  getEducationById(educationId: number): Observable<Education> {
    return this.http.get<Education>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/education/id/${educationId}`);
  }

  addEducation(education: Education): Observable<String> {
    return this.http.post<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/education/add`, education);
  }

  updateEducatione(education: Education): Observable<String> {
    return this.http.put<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/education/update`, education);
  }

  deleteEducation(educationId: number): Observable<String> {
    return this.http.delete<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/Education/delete/${educationId}`);
  }
  
}
