import { Component } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userService: UserService){}

  ngOnInit(): void{

  }

  username: string
  password: string

  login(){
    
  }

}
