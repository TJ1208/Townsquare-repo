import { User } from "./User";
import { Comment } from "./Comment";

export interface Post {
    postId: number,
    title: string,
    description: string,
    likes: number,
    dislikes: number,
    shares: number,
    imageUrl: string,
    user: User,
    comments: Comment
}