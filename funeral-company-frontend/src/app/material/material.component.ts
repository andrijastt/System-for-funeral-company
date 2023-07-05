import { Component } from '@angular/core';
import { MaterialService } from '../material.service';
import { Category } from '../model/Category';
import { CategoryService } from '../category.service';
import { Material } from '../model/Material';

@Component({
  selector: 'app-material',
  templateUrl: './material.component.html',
  styleUrls: ['./material.component.css']
})
export class MaterialComponent {

  constructor(private materialService: MaterialService, private categoryService: CategoryService){}

  categories: Category[]
  materials: Material[]

  ngOnInit(){

    this.categoryService.getAllCategories().subscribe((data: Category[])=>{
      console.log(data);      
      this.categories = data
    })
    this.materialService.getAllMaterials().subscribe((data: Material[])=>{
      console.log(data);
      this.materials = data      
    })

  }

  addMaterial: boolean = false

  addMaterialButton(){
    this.addMaterial = true
  }

  dataNotFilled: boolean = false  
  name: string
  unit: string
  categoryID: Category
  price: number

  saveMaterial(){    
    if(this.name == null || this.unit == null || this.categoryID == null || this.price == null){
      this.dataNotFilled = true
    } else {
      this.materialService.saveMaterial(this.name, this.unit, this.categoryID, this.price).subscribe(
        (data: Material) =>{
        alert("Material successfully added!")
        this.materialService.getAllMaterials().subscribe((data: Material[])=>{
          this.materials = data
        })
        this.name = null
        this.unit = null
        this.categoryID = null
        this.price = null
        this.dataNotFilled = false
        this.addMaterial = false
      },
      (error)=>{
        alert("Material with that name already exists!")            
      })      
    }    

  }

  removeMaterial(materialID){
    this.materialService.removeMaterial(materialID).subscribe((data: string)=>{
      alert(data)
      this.materialService.getAllMaterials().subscribe((data: Material[])=>{
        this.materials = data
      })
    },
    (error)=>{
      alert("Successfully deleted material")
      this.materialService.getAllMaterials().subscribe((data: Material[])=>{
        this.materials = data
      })
    }
    )
  }

}
