import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Model } from './models/Model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getAllProducts(){
    return this.http.get(`${this.uri}/products`)
  }

  saveProduct(height, width, depth, price, materialUsedList, model){

    const data = {
      height: height,
      width: width,
      depth: depth,
      price: price,
      count: 0,
      materialUsedList: materialUsedList,
      model: model
    }
    
    return this.http.post(`${this.uri}/product`, data)
  }

  updateProduct(productID, height, width, depth, price, model){

    const data = {
      productID: productID,
      height: height,
      width: width,
      depth: depth,
      price: price,
      model: model
    }
    
    return this.http.post(`${this.uri}/product/update`, data)
  }

  deleteProduct(productID){
    return this.http.delete(`${this.uri}/product/delete/${productID}`)
  }

  updateMaterialUsed(materialUsed){
    const data = {      
      materialUsedList: materialUsed
    }            
    return this.http.post(`${this.uri}/product/material/update`, data)
  }

  searchProduct(model, count){

    let queryParams: HttpParams = new HttpParams()
    queryParams = queryParams.append("count", count)
    queryParams = queryParams.append("modelID", model)        

    return this.http.get(`${this.uri}/product/search`, {params: queryParams})
  }
}
