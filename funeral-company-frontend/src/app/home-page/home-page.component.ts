import { Component } from '@angular/core';
import { User } from '../models/User';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {

  user: User
  opened: boolean = false

  ngOnInit(){

    this.user = JSON.parse(localStorage.getItem('User'))

  }  

}
