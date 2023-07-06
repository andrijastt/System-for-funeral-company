import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from './models/Client';

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

  updateClient(clientID, name, city){
    const data = {
      clientID: clientID,
      name: name,
      city: city
    }

    return this.http.post(`${this.uri}/client/update`, data)
  }

  removeClient(clientID){
    return this.http.delete(`${this.uri}/client/delete/${clientID}`)
  }

  search(nameSearch, citySearch){
    let queryParams: HttpParams = new HttpParams();
    queryParams = queryParams.append("name", nameSearch)            
    queryParams = queryParams.append("city", citySearch)            
    return this.http.get(`${this.uri}/client/search`, {params: queryParams})
  }
}
