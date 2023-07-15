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

  updateOrder(orderID, contract, dateSend){
    const data = {
      contract: contract,
      orderID: orderID,
      dateSend: dateSend
    }

    return this.http.post(`${this.uri}/order/update`, data)
  }

  updateItems(itemList, orderID){
    const data = {      
      itemList: itemList,
      orderID: orderID
    }            
    return this.http.post(`${this.uri}/order/product/update`, data)
  }

  orderArrived(orderID){
    const data = {

    }
    return this.http.post(`${this.uri}/order/arrived/${orderID}`, data)
  }

  removeOrder(orderID){
    return this.http.delete(`${this.uri}/order/delete/${orderID}`)
  }
}
