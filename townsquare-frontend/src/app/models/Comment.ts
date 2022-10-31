import { User } from "./User";
import { Post } from "./Post";

export interface Comment {
    commentId: number,
    comment: string,
    commentDate: Date,
    likes: number,
    dislikes: number,
    post: Post,
    user: User
}