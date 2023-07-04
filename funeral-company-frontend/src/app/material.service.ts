import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getAllMaterials(){
    return this.http.get(`${this.uri}/materials`)
  }

  saveMaterial(name, unit, categoryID, price){

    const data = {
      name: name,
      unit: unit,
      count: 0,
      category: categoryID,
      price: price
    }    
    return this.http.post(`${this.uri}/material`, data)

  }

  removeMaterial(materialID){        
    return this.http.delete(`${this.uri}/material/${materialID}`)
  }
}
