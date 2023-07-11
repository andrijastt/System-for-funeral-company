import { MaterialSupplyPK } from "./MaterialSupplyPK"

export class MaterialSupply{

    materialSupplyPK: MaterialSupplyPK = new MaterialSupplyPK

    materialID: number
    supplyID: number
    amount: number
    price: number
}