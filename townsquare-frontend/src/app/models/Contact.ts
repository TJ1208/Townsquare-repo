import { User } from "./User";

export interface Contact {
    contactId: number,
    businessNum: string,
    mobileNum: string,
    phoneNum: string,
    user: User
}