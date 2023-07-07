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

  saveContract(clientID, dateSigned, paymentDate, validUntill, money, currency){
    const data = {
      dateSigned: dateSigned,
      paymentDate: paymentDate,
      validUntill: validUntill,
      money: money,
      currency: currency,
      clientID: clientID,
      valid: true
    }

    return this.http.post(`${this.uri}/contract`, data)
  }
}
