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

  }

  addMaterial: boolean = false

  addMaterialButton(){
    this.addMaterial = true
  }

  categoryID: number

}
