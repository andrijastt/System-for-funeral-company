import { Component } from '@angular/core';
import { ClientService } from '../client.service';
import { Client } from '../models/Client';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {

  constructor(private clientService: ClientService){}

  clients: Client[]

  ngOnInit(){
    this.clientService.getAllClients().subscribe((data: Client[])=>{
      this.clients = data
    })
  }

  getAllClients(){
    this.clientService.getAllClients().subscribe((data: Client[])=>{
      this.clients = data
    })
  }

  addClient: boolean = false
  name: string
  city: string
  dataNotFilled: boolean = false

  saveClient(){
    if(this.name == null || this.name == "" || this.city == null || this.city == ""){
      this.dataNotFilled = true
    } else{
      this.clientService.saveClient(this.name, this.city).subscribe((data: Client)=>{
        alert("Client successfully added!")
        this.clientService.getAllClients().subscribe((data: Client[])=>{
          this.clients = data
        })
        this.dataNotFilled = false
        this.name = null
        this.city = null
        this.addClient = false
      })
    }
  }

  updateClientButton: boolean = false
  updateClientID: number
  updateName: String = ""
  updateCity: String = ""

  updateClientButtonClick(client: Client){
    this.updateClientID = client.clientID
    this.updateName = client.name
    this.updateCity = client.city
    this.updateClientButton = true
  }

  updateClient(){    
    this.clientService.updateClient(this.updateClientID, this.updateName, this.updateCity).subscribe((Data: Client)=>{
      alert("Successfully updated client!")
      this.clientService.getAllClients().subscribe((data: Client[])=>{
        this.clients = data
      })
      this.updateClientID = null
      this.updateName = null
      this.updateCity = null
      this.updateClientButton = false
    })
  }

  removeClient(clientID){
    this.clientService.removeClient(clientID).subscribe((data: string)=>{
      alert(data)
      this.clientService.getAllClients().subscribe((data: Client[])=>{
        this.clients = data
      })
    },
    (error)=>{      
      this.clientService.getAllClients().subscribe((data: Client[])=>{        
        alert("Successfully deleted client")
        this.clients = data              
      })
    }
    )
  }

  nameSearch: string = ""
  citySearch: string = ""

  search(){
    this.clientService.search(this.nameSearch, this.citySearch).subscribe((data: Client[])=>{
      this.clients = data
    })
  }
}
