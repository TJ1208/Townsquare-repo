import { User } from "./User";
import { FriendId } from "./FriendId";

export interface Friend {
    friendId: FriendId,
    user: User,
    friend: User,
    relationship: boolean

}