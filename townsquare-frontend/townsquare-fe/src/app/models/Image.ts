import { User } from "./User";

export interface Image {
    imageId: number,
    imageUrl: string,
    imageDate: Date,
    user: User
}