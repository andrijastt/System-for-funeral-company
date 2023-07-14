import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button'
import { MatDividerModule } from '@angular/material/divider'
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatSidenavModule } from '@angular/material/sidenav'
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatIconModule } from '@angular/material/icon'
import { MatListModule } from '@angular/material/list'
import { MatTableModule } from '@angular/material/table'
import { MatSelectModule } from '@angular/material/select'
import { MatCheckboxModule } from '@angular/material/checkbox'
import { MatDatepickerModule } from '@angular/material/datepicker'
import { MatNativeDateModule } from '@angular/material/core'
import { MatExpansionModule } from '@angular/material/expansion'

import { RegisterUserComponent } from './register-user/register-user.component';
import { HomePageComponent } from './home-page/home-page.component';
import { CategoryComponent } from './category/category.component';
import { MainNavComponent } from './main-nav/main-nav.component';
import { MaterialComponent } from './material/material.component';
import { ModelComponent } from './model/model.component';
import { ClientComponent } from './client/client.component';
import { ContractComponent } from './contract/contract.component';
import { SupplyComponent } from './supply/supply.component';
import { ProfileComponent } from './profile/profile.component';
import { ProductComponent } from './product/product.component';
import { OrdersComponent } from './orders/orders.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterUserComponent,
    HomePageComponent,
    CategoryComponent,
    MainNavComponent,
    MaterialComponent,
    ModelComponent,
    ClientComponent,
    ContractComponent,
    SupplyComponent,
    ProfileComponent,
    ProductComponent,
    OrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatFormFieldModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,     
    MatListModule,
    MatTableModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatExpansionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

