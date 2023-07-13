import { Material } from "./Material";
import { MaterialUsed } from "./MaterialUsed";
import { Model } from "./Model";

export class Product{
    productID: number
    model: Model
    height: number
    width: number
    depth: number
    count: number
    price: number
    materialUsedList: MaterialUsed[]
}