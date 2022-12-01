import { RequestId } from "./RequestId";
import { User } from "./User";

export interface Request {
    requestId: RequestId,
    receiver: User,
    requester: User
}