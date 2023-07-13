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
}
