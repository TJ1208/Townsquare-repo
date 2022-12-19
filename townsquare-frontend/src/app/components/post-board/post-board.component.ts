import { Component, HostListener, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { Comment } from 'src/app/models/Comment';
import { CommentService } from 'src/app/services/comment/comment.service';
import { PostService } from 'src/app/services/post/post.service';
import { BehaviorSubject, throwIfEmpty } from 'rxjs';
import { User } from 'src/app/models/User';
import { LoginService } from 'src/app/services/login/login.service';
import { UserService } from 'src/app/services/user/user.service';
import { Router } from '@angular/router';
import { Image } from 'src/app/models/Image';
import { ImageService } from 'src/app/services/image/image.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-post-board',
  templateUrl: './post-board.component.html',
  styleUrls: ['./post-board.component.scss']
})
export class PostBoardComponent implements OnInit {
  posts: Post[] = [];
  comments$ = new BehaviorSubject<Comment[]>([]);
  cast = this.comments$.asObservable();
  posts$ = new BehaviorSubject<Post[]>([]);
  castPost = this.posts$.asObservable();
  userId: any = localStorage.getItem("userId");
  currentUser: any;
  comments: Comment[] = [];
  comment: string = "";
  imageUrl?: string;
  showComments: boolean = false;
  showCommentBox: boolean = false;
  sendIcon: string = "send";
  scrollCount: number = 10;
  post: Post;
  url: string = this.router.url;
  showDeleteButton: boolean = this.url == '/profile';
  constructor(private postService: PostService, private commentService: CommentService,
    private userService: UserService, private router: Router, private imageService: ImageService,
    private modalService: NgbModal) {
    this.post = {
      postId: 0,
      title: "",
      description: "",
      likes: 0,
      dislikes: 0,
      shares: 0,
      imageUrl: "",
      date: new Date(new Date().getTime() + 8.64e+7),
      user: this.currentUser
    };
  }

  ngOnInit(): void {
    this.getCurrentUser();
    this.getPosts();
    this.getComments();
    this.cast.subscribe((comments) => {
      this.comments = comments;
    })
    this.castPost.subscribe((posts) => {
      this.posts = posts;
    })
  }

  getCurrentUser(): void {
    this.userService.getUserById(parseInt(this.userId)).subscribe((user: User) => {
      this.post.user = user;
      this.currentUser = user;
    })
  }

  viewUser(user: any): void {
    if (user.user.userId != this.userId) {
      localStorage.setItem('visitedUser', user.user.userId.toString());
      this.router.navigate(['/user']);
    } else {
      this.router.navigate(['/profile']);
    }

  }

  retrieveComments(comments: Comment[]) {
    this.comments$.next(comments);
  }

  retrievePosts(posts: Post[]) {
    this.posts$.next(posts);
  }

  changeIcon(): void {
    this.sendIcon = "check";
    setTimeout(() => {
      this.sendIcon = "send";
    }, 2500);
  }

  getPosts(): void {
    this.postService.getAllPosts().subscribe((posts: Post[]) => {
      if (this.url == "/profile") {
        posts = posts.filter((post: any) => post.user.userId == this.userId);
      } else if (this.url == "/user") {
        posts = posts.filter((post: any) => post.user.userId == localStorage.getItem("visitedUser"));
      }
      this.castPost = this.shuffle(posts);
      this.retrievePosts(posts);
    })
  }

  deletePost(post: Post): void {
    this.postService.deletePost(post.postId).subscribe(() => {
      this.getPosts();
    })
  }

  showModal(item: any) {
    this.modalService.open(item, { size: 'sm', centered: true, scrollable: true });
  }

  shuffle(array: any) {
    let currentIndex = array.length, randomIndex;

    // While there remain elements to shuffle.
    while (currentIndex != 0) {

      // Pick a remaining element.
      randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex--;

      // And swap it with the current element.
      [array[currentIndex], array[randomIndex]] = [
        array[randomIndex], array[currentIndex]];
    }

    return array;
  }

  @HostListener("window:scroll", [])
  onScroll(): void {
    if ((window.innerHeight + window.scrollY + 2000) >= document.body.offsetHeight) {
      this.scrollCount += 10;
    }
  }

  getComments(): void {
    this.commentService.getAllComments().subscribe((comments: any) => {
      this.cast = comments;
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
      user: this.post.user
    }
    this.commentService.addComment(commentData).subscribe(() => {
      this.getComments();
      this.getCommentsByPost(post.postId);
      this.imageUrl = "";
    });
  }

  addPost(): void {
    this.post.imageUrl = (<HTMLInputElement>document.getElementById("imageuri3")).value;
    this.postService.addPost(this.post).subscribe(() => {
      this.getPosts();
    })
    if (this.post.imageUrl.trim() != '') {
      let image: Image = {
        imageId: 0,
        imageUrl: this.post.imageUrl,
        imageDate: new Date(new Date().getTime() + 8.64e+7),
        user: this.currentUser
      }
      this.imageService.addImage(image).subscribe(() => {
        this.post.description = "";
        this.post.imageUrl = "";
        (<HTMLInputElement>document.getElementById("imageuri3")).value = '';
      });
    }
    console.log(this.post);
    this.post.description = "";
    this.post.imageUrl = "";
    (<HTMLInputElement>document.getElementById("imageuri3")).value = '';
  }


  updatePost(post: Post, e: Event): void {
    if (post.isLiked) {
      post.likes += 1;
      this.postService.updatePost(post).subscribe();
    } else if (!post.isLiked) {
      post.likes -= 1;
      this.postService.updatePost(post).subscribe();
    }
  }

  updateComment(comment: Comment, e: Event): void {
    if (comment.isLiked) {
      comment.likes += 1;
      this.commentService.updateComment(comment).subscribe();
    } else if (!comment.isLiked) {
      comment.likes -= 1;
      this.commentService.updateComment(comment).subscribe();
    }
  }
}
