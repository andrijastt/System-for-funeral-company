import { Component } from '@angular/core';
import { MaterialService } from '../material.service';
import { Category } from '../models/Category';
import { CategoryService } from '../category.service';
import { Material } from '../models/Material';

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
    })

  }

  getAllMaterials(){
    this.materialService.getAllMaterials().subscribe((data: Material[])=>{      
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

  updateMaterialButton: boolean = false
  updateMaterialID: number
  updateName: string
  updateUnit: string
  updateCategoryID: Category
  updatePrice: number

  updateMaterialButtonClick(material: Material){
    this.updateMaterialButton = true
    this.updateMaterialID = material.materialID
    this.updateName = material.name
    this.updateUnit = material.unit
    this.updateCategoryID = material.category
    this.updatePrice = material.price        
  }

  updateMaterial(){
    
    this.materialService.updateMaterial(this.updateMaterialID, this.updateName, 
      this.updateUnit, this.updateCategoryID, this.updatePrice).subscribe((data: Material)=>{
        alert("Successfully updated Material!")
        this.materialService.getAllMaterials().subscribe((data: Material[])=>{
          this.materials = data
        })
        this.updateMaterialButton = false
        this.updateMaterialID = null
        this.updateName = null
        this.updateUnit = null
        this.updateCategoryID = null
        this.updatePrice = null
      },
      (error)=>{
        alert("Material with that name already exists!")
      })

  }

  removeMaterial(materialID){
    this.materialService.removeMaterial(materialID).subscribe((data: string)=>{
      alert(data)
      this.materialService.getAllMaterials().subscribe((data: Material[])=>{
        this.materials = data
      })
    },
    (error)=>{
      this.materialService.getAllMaterials().subscribe((data: Material[])=>{

        if(this.materials.length == data.length)
          alert("Can't delete material")
        else
          alert("Successfully deleted material")
        this.materials = data
      })
    }
    )
  }

  nameSearch: string = ""
  categorySearch: number = 0
  countFlag: boolean = false

  search(){
    this.materialService.search(this.nameSearch, this.countFlag, this.categorySearch).subscribe((data: Material[])=>{      
      this.materials = data
    })
  }

}
