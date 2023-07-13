import { Material } from "./Material";
import { Model } from "./Model";

export class Product{
    productID: number
    material: Material
    model: Model
    height: number
    width: number
    depth: number
    count: number
    price: number
}