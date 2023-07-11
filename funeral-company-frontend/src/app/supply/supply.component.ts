import { Component } from '@angular/core';
import { SupplyService } from '../supply.service';
import { Material } from '../models/Material';
import { Supply } from '../models/Supply';
import { MaterialService } from '../material.service';
import { MaterialSupply } from '../models/MaterialSupply';
import { MatSelectChange } from '@angular/material/select';

@Component({
  selector: 'app-supply',
  templateUrl: './supply.component.html',
  styleUrls: ['./supply.component.css']
})
export class SupplyComponent {

  constructor(private supplyService: SupplyService, private materialService: MaterialService){}
  
  supplies: Supply[]
  materialSelect: Material[]  
  materialSelected: number[] = []

  ngOnInit(){
    this.supplyService.getAllSupplies().subscribe((data: Supply[])=>{
      this.supplies = data
    })
    this.materialService.getAllMaterials().subscribe((data: Material[])=>{
      this.materialSelect = data
    })
  }

  addSupplyButton: boolean = false
  dataNotFilled: boolean = false  
  name: string

  materialSupply: MaterialSupply[] = []

  addMoreMaterial(){                    
    this.materialSupply.push(new MaterialSupply())    
    this.materialSelected.push(0)
  }

  removeMaterial(i: number){   
    if(this.materialSupply.length == 1){
      alert("Can't remove material, needs to be one")
    } else {      
      this.materialSupply.splice(i, 1)      
      this.materialSelected.splice(i, 1)      
    }
  }

  onChangeMaterial(e: MatSelectChange, i: number){
    
    let temp: number = this.materialSelected.indexOf(e.value)
    console.log(temp)
    console.log(this.materialSelected)

    if(temp == -1){
      this.materialSelected[i] = e.value
    } else {
      alert("Material already selected")            
      this.materialSupply[i].materialID = null
      this.materialSelected[i] = 0
    }
    console.log(this.materialSelected)
  }

  saveSupply(){

    this.dataNotFilled = false

    if(this.materialSupply.length == 0){
      this.dataNotFilled = true
    }

    for(let material of this.materialSupply){      
      if(material.materialID == null || material.price == null || material.amount == null){
        this.dataNotFilled = true
        break
      }
    }    

    if(this.name == null){
      this.dataNotFilled = false
    }


    if(!this.dataNotFilled)
      this.supplyService.saveSupplies(this.name, this.materialSupply).subscribe((data: Supply)=>{
        console.log(data)      
      })

  }

}
