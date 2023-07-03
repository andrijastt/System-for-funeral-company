import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../model/User';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent {

  constructor(private userService: UserService){}

  username: string
  password: string
  firstname: string
  lastname: string

  message: string

  register(){    
    if(this.username == null || this.password == null || this.firstname == null || this.lastname == null){
      this.message = "Fill all data"
    } else {
      this.message = ""
    }

    this.userService.register(this.username, this.password, this.firstname, this.lastname).subscribe((data: User)=>{      
      console.log(data)      
    })

  }

}
