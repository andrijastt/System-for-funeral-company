import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SupplyService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getAllSupplies(){
    return this.http.get(`${this.uri}/supplies`)
  }

  saveSupplies(name, materialSupply){
    const data = {
      name: name,      
      materialSupplyList: materialSupply
    }            
    return this.http.post(`${this.uri}/supply`, data)
  }

  arrivedSupply(supplyID){
    const data = { }    
    return this.http.post(`${this.uri}/supply/arrived/${supplyID}`, data)
  }

  deleteSupply(supplyID){
    return this.http.delete(`${this.uri}/supply/delete/${supplyID}`)
  }

  updateSupply(supplyID, name, dateOrdered){
    const data = {
      name: name,      
      supplyID: supplyID,
      dateOrdered: dateOrdered
    }            
    return this.http.post(`${this.uri}/supply/update`, data)
  }

  updateMaterialSupply(materialSupply){
    const data = {      
      materialSupplyList: materialSupply
    }            
    return this.http.post(`${this.uri}/supply/material/update`, data)
  }
}
