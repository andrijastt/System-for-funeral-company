import { Client } from "./Client";

export class Contract{
    client: Client
    dateSigned: Date
    paymentDate: Date
    validUntill: Date
    money: number
    currency: string
    valid: boolean
}