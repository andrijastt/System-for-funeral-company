import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getAllMaterials(){
    return this.http.get(`${this.uri}/materials`)
  }
}
