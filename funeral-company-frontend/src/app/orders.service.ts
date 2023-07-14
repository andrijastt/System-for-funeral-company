import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getAllOrders(){
    return this.http.get(`${this.uri}/orders`)
  }

  getAllContractOrders(contractID){
    return this.http.get(`${this.uri}/orders/${contractID}`)
  }

  saveOrder(contract, itemList){

    const data = {
      contract: contract,
      status: 'sent',
      itemList: itemList
    }

    return this.http.post(`${this.uri}/order`, data)
  }
}
