import { MaterialSupply } from "./MaterialSupply"

export class Supply{
    supplyID: number
    name: string
    dateOrdered: Date
    dateArrived: Date
    materialSupplyList: MaterialSupply[]
}