import { User } from "./User";

export interface Work {
    workplaceId: number,
    company: string,
    position: string,
    city: string,
    description: string,
    startDate: Date,
    endDate: Date,
    user: User

}