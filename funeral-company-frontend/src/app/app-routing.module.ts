import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { HomePageComponent } from './home-page/home-page.component';
import { CategoryComponent } from './category/category.component';
import { MaterialComponent } from './material/material.component';
import { ModelComponent } from './model/model.component';
import { ClientComponent } from './client/client.component';
import { ContractComponent } from './contract/contract.component';
import { SupplyComponent } from './supply/supply.component';
import { ProfileComponent } from './profile/profile.component';
import { ProductComponent } from './product/product.component';
import { OrdersComponent } from './orders/orders.component';

const routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "registerUser", component: RegisterUserComponent},
  {path: "home", component: HomePageComponent},
  {path: "category", component: CategoryComponent},
  {path: "material", component: MaterialComponent},
  {path: "model", component: ModelComponent},
  {path: "client", component: ClientComponent},
  {path: "contract", component: ContractComponent},
  {path: "supply", component: SupplyComponent},
  {path: "profile", component: ProfileComponent},
  {path: "product", component: ProductComponent},
  {path: "orders", component: OrdersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
