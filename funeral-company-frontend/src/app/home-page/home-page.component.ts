import { Component } from '@angular/core';
import { User } from '../model/User';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {

  user: User

  ngOnInit(){

    this.user = JSON.parse(localStorage.getItem('User'))

  }

}
