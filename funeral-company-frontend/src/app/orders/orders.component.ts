import { Component } from '@angular/core';
import { OrdersService } from '../orders.service';
import { ProductService } from '../product.service';
import { ContractService } from '../contract.service';
import { Product } from '../models/Product';
import { Contract } from '../models/Contract';
import { Orders } from '../models/Orders';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {

  constructor(private ordersService: OrdersService, private productService: ProductService, private contractService: ContractService){}

  productsSelect: Product[]
  contractSelect: Contract[]
  orders: Orders[]

  ngOnInit(){
    this.contractService.getAllContracts().subscribe((data: Contract[])=>[
      this.contractSelect = data
    ])
    this.productService.getAllProducts().subscribe((data: Product[])=>{
      this.productsSelect = data
    })
  }

}
