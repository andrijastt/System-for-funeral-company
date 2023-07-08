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
      dateOrder: new Date,
      materialSupplyList: materialSupply
    }
    return this.http.post(`${this.uri}/supply`, data)
  }
}
