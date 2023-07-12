import { Component } from '@angular/core';
import { User } from '../models/User';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  user: User

  constructor(private userService: UserService, private router: Router){}

  ngOnInit(){
    this.user = JSON.parse(localStorage.getItem("User"))    
  }

  updateUser(){
    this.userService.updateUser(this.user).subscribe((data: User)=>{
      alert("Successfully updated user!")
      this.user = data
      localStorage.removeItem("User")
      localStorage.setItem("User", JSON.stringify(this.user))
      location.reload()
    })
  }

  removeUser(){
    this.userService.removeUser(this.user.userID).subscribe((data: string)=>{

    },
    (error)=>{
      alert("Successfully deleted user")
      localStorage.removeItem("User")
      this.router.navigate([''])
    })
  }

}
