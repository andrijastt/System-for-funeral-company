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
    
  }

}
