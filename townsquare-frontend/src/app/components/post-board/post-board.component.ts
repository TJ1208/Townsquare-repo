import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/post/post.service';

@Component({
  selector: 'app-post-board',
  templateUrl: './post-board.component.html',
  styleUrls: ['./post-board.component.scss']
})
export class PostBoardComponent implements OnInit {
  posts: Post[] = [];
  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(): void {
    this.postService.getAllPosts().subscribe((posts: Post[]) => {
      this.posts = posts;
      console.log(posts);
    })
  }

}
