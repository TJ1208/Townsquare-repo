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
    return this.http.get<Post[]>(`http://localhost:8181/api/post`);
  }

  getAllUserPosts(userId: number): Observable<Post[]> {
    return this.http.get<Post[]>(`http://localhost:8181/api/post/${userId}`);
  }

  getPostById(postId: number): Observable<Post> {
    return this.http.get<Post>(`http://localhost:8181/api/post/id/${postId}`);
  }

  addPost(post: Post): Observable<any> {
    return this.http.post<String>(`http://localhost:8181/api/post/add`, post);
  }

  updatePost(post: Post): Observable<String> {
    return this.http.put<String>(`http://localhost:8181/api/post/update`, post);
  }

  deletePost(postId: number): Observable<String> {
    return this.http.delete<String>(`http://localhost:8181/api/post/delete/${postId}`);
  }
  
}
