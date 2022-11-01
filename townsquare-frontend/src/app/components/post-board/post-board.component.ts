import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { Comment } from 'src/app/models/Comment';
import { CommentService } from 'src/app/services/comment/comment.service';
import { PostService } from 'src/app/services/post/post.service';

@Component({
  selector: 'app-post-board',
  templateUrl: './post-board.component.html',
  styleUrls: ['./post-board.component.scss']
})
export class PostBoardComponent implements OnInit {
  posts: Post[] = [];
  comments: Comment[] = [];
  showComments: boolean = false;
  constructor(private postService: PostService, private commentService: CommentService) { }

  ngOnInit(): void {
    this.getPosts();
    this.getComments();
  }

  getPosts(): void {
    this.postService.getAllPosts().subscribe((posts: Post[]) => {
      this.posts = posts;
      console.log(posts);
    })
  }

  getComments(): void {
    this.commentService.getAllComments().subscribe((comments: Comment[]) => {
      this.comments = comments;
      console.log(comments);
    })
  }

  getCommentsByPost(postId: number): Comment[] {
    return this.comments.filter((comment: Comment) => comment.post.postId == postId);
  }

  // getCommentAmount(post: Post): number {
  //  console.log(post.comments.length);
  //   return post.comments.length;
  // }

}
