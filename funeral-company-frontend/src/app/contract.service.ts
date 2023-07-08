import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getAllContracts(){
    return this.http.get(`${this.uri}/contracts`)
  }

  getAllValidContracts(){
    return this.http.get(`${this.uri}/contracts/valid`)
  }

  getAllContractsByClient(clientID){
    return this.http.get(`${this.uri}/contracts/client/${clientID}`)
  }

  getSumOfAllContractsByClient(clientID){
    return this.http.get(`${this.uri}/contracts/client/sum/${clientID}`)
  }  

  getNewestContractByClient(clientID){
    return this.http.get(`${this.uri}/contract/client/newest/${clientID}`)
  }  

  saveContract(clientID, dateSigned, paymentDate, validUntil, money, currency){
    const data = {
      dateSigned: dateSigned,
      paymentDate: paymentDate,
      validUntil: validUntil,
      money: money,
      currency: currency,
      client: clientID,
      valid: true
    }

    return this.http.post(`${this.uri}/contract`, data)
  }

  changeValid(contractID){
    const data = {
      
    }
    return this.http.post(`${this.uri}/contract/valid/${contractID}`, data)
  }

  removeContract(contractID){    
    return this.http.delete(`${this.uri}/contract/${contractID}`)
  }
}
