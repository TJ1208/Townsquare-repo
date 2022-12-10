import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from 'src/app/models/Comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getAllComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(`https://townsquare-frontend.azurewebsites.net/api/comment`);
  }

  getAllUserComments(userId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`https://townsquare-frontend.azurewebsites.net/api/comment/${userId}`);
  }

  getCommentById(commentId: number): Observable<Comment> {
    return this.http.get<Comment>(`https://townsquare-frontend.azurewebsites.net/api/comment/id/${commentId}`);
  }

  addComment(comment: Comment): Observable<String> {
    return this.http.post<String>(`https://townsquare-frontend.azurewebsites.net/api/comment/add`, comment);
  }

  updateComment(comment: Comment): Observable<String> {
    return this.http.put<String>(`https://townsquare-frontend.azurewebsites.net/api/comment/update`, comment);
  }

  deleteComment(commentId: number): Observable<String> {
    return this.http.delete<String>(`https://townsquare-frontend.azurewebsites.net/api/comment/delete/${commentId}`);
  }
  
}
