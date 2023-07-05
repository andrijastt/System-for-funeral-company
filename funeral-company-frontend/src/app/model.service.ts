import { HttpClient, HttpParams } from '@angular/common/http';
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

  updateModel(modelID, name, description){    

    const data = {
      modelID: modelID,
      name: name,
      description: description
    }        

    return this.http.post(`${this.uri}/model/update`, data)
  }

  removeModel(modelID){
    return this.http.delete(`${this.uri}/model/delete/${modelID}`)
  }

  search(nameSearch){

    let queryParams: HttpParams = new HttpParams();
    queryParams = queryParams.append("name", nameSearch)            

    return this.http.get(`${this.uri}/model/search`, {params: queryParams})
  }
}
