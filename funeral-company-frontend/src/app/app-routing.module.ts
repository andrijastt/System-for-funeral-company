import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { HomePageComponent } from './home-page/home-page.component';
import { CategoryComponent } from './category/category.component';
import { MaterialComponent } from './material/material.component';

const routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "registerUser", component: RegisterUserComponent},
  {path: "home", component: HomePageComponent},
  {path: "category", component: CategoryComponent},
  {path: "material", component: MaterialComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
