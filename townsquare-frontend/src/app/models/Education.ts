import { User } from "./User";

export interface Education {
    educationId: number,
    educationType: boolean,
    graduated: boolean,
    startDate: Date,
    endDate: Date,
    description: string,
    school: string,
    degree: string,
    user: User
}