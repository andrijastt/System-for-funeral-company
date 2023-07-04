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
      this.categories = data
    })
    this.materialService.getAllMaterials().subscribe((data: Material[])=>{
      this.materials = data
      console.log(data)
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
      this.materialService.saveMaterial(this.name, this.unit, this.categoryID, this.price).subscribe((data: Material) =>{
        alert("Material successfully added!")
        this.materialService.getAllMaterials().subscribe((data: Material[])=>{
          this.materials = data
        })
      },
      (error)=>{
        alert("Material with that name already exists!")
      })
    }

  }

}
