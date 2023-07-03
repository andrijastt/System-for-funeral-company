import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './model/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  register(usernameForm, passwordFrom, firstnameForm, lastnameForm){

    const data = {
      username: usernameForm,
      password: passwordFrom,
      firstname: firstnameForm,
      lastname: lastnameForm
    }    

    return this.http.post<User>(`${this.uri}/user`, data);

  }
}
