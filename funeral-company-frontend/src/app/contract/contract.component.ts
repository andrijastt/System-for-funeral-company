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

  getAllContracts(){
    this.contractService.getAllContracts().subscribe((data:Contract[])=>{
      this.contracts = data
    })
  }

  getAllValidContracts(){
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

      this.clientID = null
      this.dateSignedData = null
      this.paymentDateData = null
      this.validUntilData = null
      this.money = null
      this.currency = null
      this.dataNotFilled = false
      this.addContract = false
    }
  }

  changeValid(contractID){
    this.contractService.changeValid(contractID).subscribe((data: Contract)=>{
      alert("Successfully updated contract's status!")
      this.contractService.getAllValidContracts().subscribe((data:Contract[])=>{
        this.contracts = data
      })
    })
  }

  clientSearch: Client = null
  clientTableName: string = ""
  sumTable: Object[][]  
  showTable: boolean = false

  getSumOfAllContractsByClient(client: Client){
    if(client != null){
      this.clientTableName = client.name
      this.contractService.getSumOfAllContractsByClient(client.clientID).subscribe((data: Object[][])=>{               
        this.sumTable = data
      })      
      this.showTable = true
    } else{
      alert("Select client")
    }    
  }

  getNewestContractByClient(client: Client){
    if(client != null){      
      this.contractService.getNewestContractByClient(client.clientID).subscribe((data: Contract[])=>{               
        this.contracts = data
      })            
    } else{
      alert("Select client")
    }
  }

  getAllContractsByClient(client: Client){
    if(client != null){      
      this.contractService.getAllContractsByClient(client.clientID).subscribe((data: Contract[])=>{               
        this.contracts = data
      })            
    } else{
      alert("Select client")
    }
  }

  removeContract(contractID){

    this.contractService.removeContract(contractID).subscribe((data: string)=>{
      alert(data)
      this.contractService.getAllValidContracts().subscribe((data:Contract[])=>{
        this.contracts = data
      })
    },
    (error)=>{
      this.contractService.getAllValidContracts().subscribe((data:Contract[])=>{
        if(this.contracts.length == data.length){
          alert("Couldn't delete contract")
        }else{
          alert("Successfully deleted contract")
          this.contracts = data
        }        
      })
    })

  }

  updateContract: boolean = false
  updateContractID: number
  updateClient: Client
  updateDateSignedData: Date
  updatePaymentDateData: Date
  updateValidUntilData: Date
  updateMoney: number
  updateCurrency: string

  updateContractButton(contract: Contract){    
    this.updateContract = true
    this.updateContractID = contract.contractID
    this.updateClient = contract.client
    this.updateDateSignedData = contract.dateSigned
    this.updatePaymentDateData = contract.paymentDate
    this.updateValidUntilData = contract.validUntil
    this.updateMoney = contract.money
    this.updateCurrency = contract.currency    
  }

  updateContractClick(){
    this.contractService.updateContract(this.updateContractID, this.updateClient, this.updateDateSignedData, this.updatePaymentDateData, 
      this.updateValidUntilData, this.updateMoney, this.updateCurrency).subscribe((data: Contract)=>{
        alert("Successfully updated contract!")
        this.contractService.getAllValidContracts().subscribe((data:Contract[])=>{
          this.contracts = data
        })
      })
  }
}
