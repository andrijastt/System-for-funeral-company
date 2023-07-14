import { Contract } from "./Contract"
import { Item } from "./Item"

export class Orders{
    orderID: number
    dateSend: Date
    dateArrived: Date
    status: string
    contract: Contract
    itemList: Item[]
}