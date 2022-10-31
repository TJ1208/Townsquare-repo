import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getAllComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(`http://localhost:8181/api/comment`);
  }

  getAllUserComments(userId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`http://localhost:8181/api/comment/${userId}`);
  }

  getCommentById(commentId: number): Observable<Comment> {
    return this.http.get<Comment>(`http://localhost:8181/api/comment/id/${commentId}`);
  }

  addComment(comment: Comment): Observable<String> {
    return this.http.post<String>(`http://localhost:8181/api/comment/add`, comment);
  }

  updateComment(comment: Comment): Observable<String> {
    return this.http.put<String>(`http://localhost:8181/api/comment/update`, comment);
  }

  deleteComment(commentId: number): Observable<String> {
    return this.http.delete<String>(`http://localhost:8181/api/comment/delete/${commentId}`);
  }
  
}
