import { Address } from "./Address"
import { Comment } from "./Comment"
import { Post } from "./Post"
import { Contact } from "./Contact"
import { Education } from "./Education"
import { Image } from "./Image"
import { Friend } from "./Friend"
import { Work } from "./Work"

export interface User {
    userId: number,
    firstName: string,
    lastName: string,
    username: string,
    password: string,
    profileImg: string,
    email: string,
    profileBio: string,
    backgroundImg: string,
    birthDate: Date,
    birthplace: string,
    homeTown: string
}