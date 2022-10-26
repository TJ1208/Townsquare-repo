import { User } from "./User";

export interface Address {
    addressId: number,
    city: string,
    state: string,
    street: string,
    zipcode: string,
    country: string,
    apartmentNum: string,
    user: User
}