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
  contracts: Contract[]

  ngOnInit(){
    this.clientService.getAllClients().subscribe((data: Client[])=>{
      this.clients = data
    })
    this.contractService.getAllValidContracts().subscribe((data:Contract[])=>{
      this.contracts = data
    })
  }

  addContract: boolean = false
  dataNotFilled: boolean = false  

  dateSignedData: Date
  clientID: Client
  paymentDateData: Date
  validUntilData: Date
  money: number
  currency: string

  saveContract(){
    if(this.clientID == null || this.dateSignedData == null || this.paymentDateData == null ||this.validUntilData == null || 
      this.money == null || this.currency == null){
      this.dataNotFilled = true
    } else {

      this.contractService.saveContract(this.clientID, this.dateSignedData, this.paymentDateData, this.validUntilData, this.money,this.currency)
      .subscribe((data: Contract)=>{
        alert("Successfully added Contract!")
        this.contractService.getAllValidContracts().subscribe((data:Contract[])=>{
          this.contracts = data
        })

      })

    }
  }

}
