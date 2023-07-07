import { Client } from "./Client";

export class Contract{
    contractID: number
    client: Client
    dateSigned: Date
    paymentDate: Date
    validUntil: Date
    money: number
    currency: string
    valid: boolean
}