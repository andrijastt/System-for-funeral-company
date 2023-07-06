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
}
