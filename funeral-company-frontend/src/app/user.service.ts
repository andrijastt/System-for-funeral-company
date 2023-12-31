import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  uri = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  register(usernameForm, passwordForm, firstnameForm, lastnameForm){

    const data = {
      username: usernameForm,
      password: passwordForm,
      firstname: firstnameForm,
      lastname: lastnameForm
    }    

    return this.http.post<User>(`${this.uri}/user`, data);

  }

  login(usernameForm, passwordForm){
            
    let queryParams = new HttpParams();        
    queryParams = queryParams.append("username", usernameForm)
    queryParams = queryParams.append("password", passwordForm)    

    return this.http.get<User>(`${this.uri}/login`, {params: queryParams})
  }

  updateUser(user: User){
    const data = {
      userID: user.userID,
      username: user.username,
      password: user.password,
      firstname: user.firstname,
      lastname: user.lastname
    }     
    
    return this.http.post(`${this.uri}/user/update`, data);
  }

  removeUser(userID){
    return this.http.delete(`${this.uri}/user/delete/${userID}`, );
  }
}
