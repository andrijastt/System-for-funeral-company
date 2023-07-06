import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from './models/Category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  uri = 'http://localhost:8080'

  saveCategory(categoryName: string){

    const data = {
      name: categoryName
    }

    return this.http.post<Category>(`${this.uri}/category`, data)
  }  

  getAllCategories(){
    return this.http.get(`${this.uri}/categories`)
  }

  updateCategory(categoryID, name){
    const data = {
      categoryID: categoryID,
      name: name
    }

    return this.http.put<Category>(`${this.uri}/category/update`, data)
  }

  removeCategory(categoryID){        
    return this.http.delete(`${this.uri}/category/delete/${categoryID}`)
  }

  search(name){
    let queryParams: HttpParams = new HttpParams();
    queryParams = queryParams.append("name", name)
    return this.http.get(`${this.uri}/category/search`, {params: queryParams})
  }

}
