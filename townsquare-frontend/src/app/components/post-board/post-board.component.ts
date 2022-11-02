import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { Comment } from 'src/app/models/Comment';
import { CommentService } from 'src/app/services/comment/comment.service';
import { PostService } from 'src/app/services/post/post.service';
import { BehaviorSubject, Observable } from 'rxjs';

@Component({
  selector: 'app-post-board',
  templateUrl: './post-board.component.html',
  styleUrls: ['./post-board.component.scss']
})
export class PostBoardComponent implements OnInit {
  posts: Post[] = [];
  comments$ = new BehaviorSubject<Comment[]>([]);
  cast = this.comments$.asObservable();
  comments: Comment[] = [];
  comment?: Comment;
  showComments: boolean = false;
  showCommentBox: boolean = false;
  imageUrl?: string = "";
  constructor(private postService: PostService, private commentService: CommentService) { }

  ngOnInit(): void {
    this.getPosts();
    this.getComments();
    this.cast.subscribe((comments) => {
      this.comments = comments;
    })
  }

  retrieveComments(comments: Comment[]) {
    this.comments$.next(comments);
  }

  getPosts(): void {
    this.postService.getAllPosts().subscribe((posts: Post[]) => {
      this.posts = posts;
      console.log(posts);
    })
  }

  getComments(): void {
    this.commentService.getAllComments().subscribe((comments: any) => {
      this.cast = comments;
      this.comments = comments;
      console.log(comments);
      this.retrieveComments(comments);
    })
  }

  getCommentsByPost(postId: number): Comment[] {
    return this.comments.filter((comment: Comment) => comment.post.postId == postId);
  }

  displayComments(post: any): void {
    post.showComments = !post.showComments;
  }

  displayCommentBox(post: any): void {
    post.showCommentBox = !post.showCommentBox;
  }

  getCommentsCount(postId: number): number {
    return this.comments.filter((comment) => comment.post.postId == postId).length;
  }

  formatDate(date: Date): string {
    const monthNames: String[] = ["January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
    ];
    let newDate: Date = new Date(date);
    let dateString: string = monthNames[newDate.getMonth()] + " " + newDate.getDate() + `, ` + newDate.getFullYear();
    return dateString;
  }

  postComment(post: Post, comment: any): void {
    let commentData: Comment = {
      commentId: 0,
      commentDate: new Date(new Date().getTime() + 8.64e+7),
      comment: comment,
      likes: 0,
      dislikes: 0,
      post: post,
      user: post.user
    }
    this.commentService.addComment(commentData).subscribe(() => {
      this.getComments();
      this.getCommentsByPost(post.postId);
    });
  }

}
