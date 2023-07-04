import { Component } from '@angular/core';
import { User } from '../model/User';
import { Category } from '../model/Category';
import { CategoryService } from '../category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {

  user: User
  categories: Category[]  

  constructor(private categoryService: CategoryService){}

  ngOnInit(){
    this.user = JSON.parse(localStorage.getItem('User'))
    this.categoryService.getAllCategories().subscribe((data: Category[])=>{
      this.categories = data
    })       
  }
  
  addCategory: boolean = false;

  addCategoryButton(){
    this.addCategory = true
  }

  categoryName: string

  saveCategory(){

    if(this.categoryName == ""){
      alert("No name entered")
    } else {
      this.categoryService.saveCategory(this.categoryName).subscribe((data: Category)=>{
        alert("Category successfully added!")
        this.categoryService.getAllCategories().subscribe((data: Category[])=>{
          this.categories = data
        })
      },
      (error)=>{
        alert("Category with that name already exists!")      
      })
  
      this.addCategory = false
      this.categoryName = ""
    }    

  }  

  updateCategoryBool: boolean = false
  updateCategoryName: string
  updateCategoryID: number  

  setUpdateCategory(category: Category){
    this.updateCategoryBool = true
    this.updateCategoryName = category.name
    this.updateCategoryID = category.categoryID
  }

  updateCategory(){

    this.categoryService.updateCategory(this.updateCategoryID, this.updateCategoryName).subscribe((data: Category) =>{
      alert("Successfully updated name!")
      this.categoryService.getAllCategories().subscribe((data: Category[])=>{
        this.categories = data
      })
    })

  }

}
