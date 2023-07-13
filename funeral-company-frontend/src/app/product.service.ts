import { HttpClient } from '@angular/common/http';
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
  console.log(data);
  
  return this.http.post(`${this.uri}/product`, data)
  }
}
