import { Component } from '@angular/core';
import { Product } from '../models/Product';
import { ProductService } from '../product.service';
import { MaterialService } from '../material.service';
import { Material } from '../models/Material';
import { Model } from '../models/Model';
import { ModelService } from '../model.service';
import { MaterialUsed } from '../models/MaterialUsed';
import { MatSelectChange } from '@angular/material/select';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  constructor(private productService: ProductService, private materialService: MaterialService, 
    private modelService: ModelService){}


  products: Product[]
  materialSelect: Material[]
  modelSelect: Model[]

  ngOnInit(){
    this.materialService.getAllMaterials().subscribe((data: Material[])=>{
      this.materialSelect = data
    })
    this.modelService.getAllModels().subscribe((data: Model[])=>{
      this.modelSelect = data
    })
    this.productService.getAllProducts().subscribe((data: Product[])=>{
      this.products = data
    })
  }

  printNameMaterial(id: number): string{
    for(let i = 0; i < this.materialSelect.length; i++){
      if(this.materialSelect[i].materialID == id){
        return this.materialSelect[i].name
      }
    }
    return ""
  }

  printUnitMaterial(id: number): string{
    for(let i = 0; i < this.materialSelect.length; i++){
      if(this.materialSelect[i].materialID == id){
        return this.materialSelect[i].unit
      }
    }
    return ""
  }

  addProductButton: boolean = false

  addMaterialUsed: MaterialUsed[] = []

  addHeight: number
  addWidth: number
  addDepth: number
  addPrice: number
  addModel: Model

  addMaterialSelected: number[] = []
  dataNotFilled: boolean = false

  onChangeMaterial(e: MatSelectChange, i: number){        
    let temp: number = this.addMaterialSelected.indexOf(e.value)        
    if(temp == -1){
      this.addMaterialSelected[i] = e.value
      this.addMaterialUsed[i].materialUsedPK.materialID = e.value
    } else {
      alert("Material already selected")            
      this.addMaterialUsed[i].materialUsedPK.materialID = null      
      this.addMaterialSelected[i] = 0
    }    
  }

  addMoreMaterial(){                    

    if(this.addMaterialUsed.length == this.materialSelect.length){
      alert("Added all fields for materials")
    } else {
      this.addMaterialUsed.push(new MaterialUsed())    
      this.addMaterialSelected.push(0)
    }    
  }

  removeMaterial(i: number){   
    if(this.addMaterialSelected.length == 1){
      alert("Can't remove material, needs to be one")
    } else {      
      this.addMaterialUsed.splice(i, 1)      
      this.addMaterialSelected.splice(i, 1)      
    }
  }

  saveProduct(){

    this.dataNotFilled = false

    if(this.addMaterialUsed.length == 0){            
      this.dataNotFilled = true
    }

    for(let i = 0; i < this.addMaterialUsed.length; i++){

      if(this.addMaterialUsed[i].amount == null || this.addMaterialUsed[i].materialUsedPK.materialID == null){
        this.dataNotFilled = true
        break
      }

    }

    if(this.addHeight == null || this.addDepth == null || this.addWidth == null || this.addPrice == null){
      this.dataNotFilled = true
    }

    if(this.addModel == null){
      this.dataNotFilled = true
    }

    if(!this.dataNotFilled){
      this.productService.saveProduct(this.addHeight, this.addWidth, this.addDepth, this.addPrice, this.addMaterialUsed, this.addModel)
      .subscribe(
        (data: Product)=>{

          alert("Successfully added product!")
          this.productService.getAllProducts().subscribe((data: Product[])=>{
            this.products = data
          })

          this.dataNotFilled = false
          this.addProductButton = false
          this.addHeight = null 
          this.addDepth = null
          this.addWidth = null
          this.addPrice = null
          this.addModel = null
          this.addMaterialSelected = []
          this.addMaterialUsed = []
        }
      )
    }
  }


  updateProduct: boolean = false
  updateProductID: number
  updatePrice: number
  updateHeight: number
  updateWidth: number
  updateDepth: number
  updateModel: Model

  updateProductButton(product: Product){
    this.updateProduct = true
    
    this.updateProductID = product.productID
    this.updateHeight = product.height
    this.updateWidth = product.width
    this.updateDepth = product.depth
    this.updatePrice = product.price
    this.updateModel = product.model
  }

  updateProductButtonClick(){

    this.productService.updateProduct(this.updateProductID, this.updateHeight, this.updateWidth, this.updateDepth, 
      this.updatePrice, this.updateModel).subscribe((data: Product)=>{


        alert("Successfully updated product!")
        this.productService.getAllProducts().subscribe((data: Product[])=>{
          this.products = data
        })

        this.updateProduct = false
    
        this.updateProductID = null
        this.updateHeight = null
        this.updateWidth = null
        this.updateDepth = null
        this.updatePrice = null
        this.updateModel = null
      })

  }

  deleteProduct(productID){

    this.productService.deleteProduct(productID).subscribe((data: string)=>{

    },
    (error)=>{
      alert('Successfully deleted product')
      this.productService.getAllProducts().subscribe((data: Product[])=>{
        this.products = data
      })
    })

  }

  updateMaterialUsedBool: boolean = false

  updateMaterialUsed: MaterialUsed[]
  updateMaterialSelected: number[]

  updateMaterialUsedClick(materialUsedList){

    this.updateMaterialUsedBool = true        
    this.updateMaterialUsed = materialUsedList            
    this.updateMaterialSelected = []
    this.updateMaterialUsed.forEach(element => {
      this.updateMaterialSelected.push(element.materialUsedPK.materialID)
    });

  }

  onChangeMaterialUpdate(e: MatSelectChange, i: number){        
    let temp: number = this.addMaterialSelected.indexOf(e.value)        
    if(temp == -1){
      this.updateMaterialSelected[i] = e.value
      this.updateMaterialUsed[i].materialUsedPK.materialID = e.value
    } else {
      alert("Material already selected")            
      this.updateMaterialUsed[i].materialUsedPK.materialID = null      
      this.updateMaterialSelected[i] = 0
    }    
  }

  addMoreUpdateMaterial(){

    if(this.updateMaterialUsed.length == this.materialSelect.length){
      alert("Added all fields for materials")
    } else {
      this.updateMaterialUsed.push(new MaterialUsed())    
      this.updateMaterialSelected.push(0)
    }

  }

  removeMaterialUpdate(i: number){   
    if(this.updateMaterialUsed.length == 1){
      alert("Can't remove material, needs to be one")
    } else {      
      this.updateMaterialUsed.splice(i, 1)      
      this.updateMaterialSelected.splice(i, 1)      
    }
  }

  updateMaterialUsedClickFinal(){

    this.productService.updateMaterialUsed(this.updateMaterialUsed).subscribe((data: Product)=>{

      alert('Successfully updated Materials used!')
      
      this.productService.getAllProducts().subscribe((data: Product[])=>{
        this.products = data
      })
      this.updateMaterialUsedBool = false
      this.updateMaterialUsed = null
      this.updateMaterialSelected = null

    })

  }

}
