import { Component } from '@angular/core';
import { OrdersService } from '../orders.service';
import { ProductService } from '../product.service';
import { ContractService } from '../contract.service';
import { Product } from '../models/Product';
import { Contract } from '../models/Contract';
import { Orders } from '../models/Orders';
import { Item } from '../models/Item';
import { MatSelectChange } from '@angular/material/select';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {

  constructor(private ordersService: OrdersService, private productService: ProductService, private contractService: ContractService){}

  productSelect: Product[]
  contractSelect: Contract[]
  orders: Orders[]

  ngOnInit(){
    this.contractService.getAllValidContracts().subscribe((data: Contract[])=>[
      this.contractSelect = data
    ])
    this.productService.getAllProducts().subscribe((data: Product[])=>{
      this.productSelect = data
    })
    this.ordersService.getAllOrders().subscribe((data: Orders[])=>{
      this.orders = data
    })
  }

  addOrdersButton: boolean = false
  addContract: Contract

  addItems: Item[] = []
  addItemsSelected: number[] = []

  dataNotFilled: boolean = false

  onChangeProduct(e: MatSelectChange, i: number){        
    let temp: number = this.addItemsSelected.indexOf(e.value)        
    if(temp == -1){
      this.addItemsSelected[i] = e.value
      this.addItems[i].itemPK.productID = e.value
    } else {
      alert("Material already selected")            
      this.addItems[i].itemPK.productID  = null      
      this.addItemsSelected[i] = 0
    }    
  }

  addMoreProduct(){                    

    if(this.addItems.length == this.productSelect.length){
      alert("Added all fields for materials")
    } else {
      this.addItems.push(new Item())    
      this.addItemsSelected.push(0)
    }    
  }

  removeProduct(i: number){   
    if(this.addItemsSelected.length == 1){
      alert("Can't remove product, needs to be one")
    } else {      
      this.addItems.splice(i, 1)      
      this.addItemsSelected.splice(i, 1)      
    }
  }

  saveOrder(){

    this.dataNotFilled = false

    if(this.addContract == null) this.dataNotFilled = true

    for(let i = 0 ; i < this.addItems.length; i++){

      if(this.addItems[i].itemPK.productID == null || this.addItems[i].amount == null){
        this.dataNotFilled = true
        break
      }

    }

    if(!this.dataNotFilled){
      this.ordersService.saveOrder(this.addContract, this.addItems).subscribe((data: Orders)=>{

        
        if(data == null){
          alert("Not enough products to send!")
        } else {
          alert("Created new order!")
        }

        this.ordersService.getAllOrders().subscribe((data: Orders[])=>{
          this.orders = data
        })

        this.dataNotFilled = false
        this.addOrdersButton = false
        this.addContract = null
        this.addItems = []
        this.addItemsSelected = []

      })
    }

  }

}
