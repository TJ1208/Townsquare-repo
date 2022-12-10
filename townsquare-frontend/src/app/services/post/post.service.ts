import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from 'src/app/models/Post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`https://townsquare-backend.azurewebsites.net/api/post`);
  }

  getAllUserPosts(userId: number): Observable<Post[]> {
    return this.http.get<Post[]>(`https://townsquare-backend.azurewebsites.net/api/post/${userId}`);
  }

  getPostById(postId: number): Observable<Post> {
    return this.http.get<Post>(`https://townsquare-backend.azurewebsites.net/api/post/id/${postId}`);
  }

  addPost(post: Post): Observable<any> {
    return this.http.post<String>(`https://townsquare-backend.azurewebsites.net/api/post/add`, post);
  }

  updatePost(post: Post): Observable<String> {
    return this.http.put<String>(`https://townsquare-backend.azurewebsites.net/api/post/update`, post);
  }

  deletePost(postId: number): Observable<String> {
    return this.http.delete<String>(`https://townsquare-backend.azurewebsites.net/api/post/delete/${postId}`);
  }
  
}
