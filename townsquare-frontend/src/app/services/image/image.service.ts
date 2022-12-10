import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Image } from 'src/app/models/Image';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient) { }

  getAllImages(): Observable<Image[]> {
    return this.http.get<Image[]>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/image`);
  }

  getAllUserImages(userId: number): Observable<Image[]> {
    return this.http.get<Image[]>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/image/${userId}`);
  }

  getImageById(imageId: number): Observable<Image> {
    return this.http.get<Image>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/image/id/${imageId}`);
  }

  addImage(image: Image): Observable<String> {
    return this.http.post<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/image/add`, image);
  }

  updateImage(image: Image): Observable<String> {
    return this.http.put<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/image/update`, image);
  }

  deleteImage(imageId: number): Observable<String> {
    return this.http.delete<String>(`http://localhost:8181https://townsquare-backend.azurewebsites.net/api/image/delete/${imageId}`);
  }
  
}
