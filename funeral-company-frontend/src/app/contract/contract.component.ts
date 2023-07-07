import { Component } from '@angular/core';
import { ContractService } from '../contract.service';
import { ClientService } from '../client.service';
import { Client } from '../models/Client';
import { Contract } from '../models/Contract';

@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
  styleUrls: ['./contract.component.css']
})
export class ContractComponent {

  minMaxDate: Date  

  constructor(private contractService: ContractService, private clientService: ClientService){
    this.minMaxDate = new Date()
  }

  clients: Client[]

  ngOnInit(){
    this.clientService.getAllClients().subscribe((data: Client[])=>{
      this.clients = data
    })
  }

  addContract: boolean = false
  dataNotFilled: boolean = false  

  dateSigned: Date
  clientID: number
  paymentDate: Date
  validUntill: Date
  money: number
  currency: string

  saveContract(){
    if(this.clientID == null || this.dateSigned == null || this.paymentDate == null ||this.validUntill == null || 
      this.money == null || this.currency == null){
      this.dataNotFilled = true
    } else {

      this.contractService.saveContract(this.clientID, this.dateSigned, this.paymentDate, this.validUntill, this.money,this.currency)
      .subscribe((data: Contract)=>{

      })

    }
  }

}
