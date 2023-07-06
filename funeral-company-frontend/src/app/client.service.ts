import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getAllClients(){
    return this.http.get(`${this.uri}/clients`)
  }

  saveClient(name, city){
    const data = {
      name: name,
      city: city
    }
    return this.http.post(`${this.uri}/client`, data)
  }
}
