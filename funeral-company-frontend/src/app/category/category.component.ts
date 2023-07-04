import { Component } from '@angular/core';
import { User } from '../model/User';
import { Category } from '../model/Category';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {

  user: User

  ngOnInit(){

    this.user = JSON.parse(localStorage.getItem('User'))

  }

  categories: Category[]

}
