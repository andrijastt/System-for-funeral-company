import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getAllModels(){
    return this.http.get(`${this.uri}/models`)
  }

  saveModel(name, description){
    const data = {
      name: name,
      description: description
    }
    return this.http.post(`${this.uri}/model`, data)
  }
}
