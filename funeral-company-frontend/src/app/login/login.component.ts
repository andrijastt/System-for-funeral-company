import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userService: UserService, private router: Router){}

  ngOnInit(): void{

  }

  username: string
  password: string
  message: string
  login(){
    
    if(this.username == null || this.password == null){
      this.message = "Fill all data"
    } else{
      this.message = ""
      this.userService.login(this.username, this.password).subscribe((data: User) =>{

        if(data == null){
          this.message = "Wrong password or username"
        } else {
          this.router.navigate(['home'])
        }        
        
      })
    }

  }

}
