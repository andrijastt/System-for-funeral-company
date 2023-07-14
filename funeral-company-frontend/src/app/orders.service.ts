import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getAllOrders(){
    return this.http.get(`${this.http}/orders`)
  }
}
