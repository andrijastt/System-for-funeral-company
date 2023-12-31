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
  minMaxDate: Date = new Date()

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

    if(this.materialSupply.length == this.materialSelect.length){
      alert("Added all fields for materials")
    } else {
      this.materialSupply.push(new MaterialSupply())    
      this.materialSelected.push(0)
    }    
  }

  printName(id: number): string{
    for(let i = 0; i < this.materialSelect.length; i++){
      if(this.materialSelect[i].materialID == id){
        return this.materialSelect[i].name
      }
    }
    return ""
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
    if(temp == -1){
      this.materialSelected[i] = e.value
    } else {
      alert("Material already selected")            
      this.materialSupply[i].materialID = null      
      this.materialSelected[i] = 0
    }    
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
      } else {        
        material.materialSupplyPK.materialID = material.materialID
      }
    }    

    if(this.name == null){
      this.dataNotFilled = false
    }


    if(!this.dataNotFilled)
      this.supplyService.saveSupplies(this.name, this.materialSupply).subscribe((data: Supply)=>{        
        alert("Successfully added Supply")
        this.supplyService.getAllSupplies().subscribe((data: Supply[])=>{
          this.supplies = data
        })
        this.materialSelected = []
        this.dataNotFilled = false
        this.name = null
        this.materialSupply = []
        this.addSupplyButton = false

      })
  }

  supplyArrived(supplyID){
    this.supplyService.arrivedSupply(supplyID).subscribe((data: Supply)=>{
      alert("Package arrived")
      this.supplyService.getAllSupplies().subscribe((data: Supply[])=>{
        this.supplies = data
      })
    })
  }

  deleteSupply(supplyID){

    this.supplyService.deleteSupply(supplyID).subscribe((data: Supply)=>{

    },
    (error)=>{
      alert("Successfully deleted!")
      this.supplyService.getAllSupplies().subscribe((data: Supply[])=>{
        this.supplies = data
      })
    })

  }

  updateSupply: boolean = false
  updateName: string
  updateDateOrdered: Date
  updateSupplyID: number

  updateSupplyButton(supply: Supply){
    this.updateSupply = true
    this.updateName = supply.name
    this.updateDateOrdered = supply.dateOrdered
    this.updateSupplyID = supply.supplyID
  }

  updateSupplyButtonClick(){

    this.supplyService.updateSupply(this.updateSupplyID, this.updateName, this.updateDateOrdered).subscribe((data: Supply)=>{
      alert("Successfully updatedSupply")
      this.supplyService.getAllSupplies().subscribe((data: Supply[])=>{
        this.supplies = data
      })
      this.updateSupply = false
      this.updateName = null
      this.updateDateOrdered = null
      this.updateSupplyID = null
    })

  }

  updateMaterialSupplyBool: boolean = false
  updateMaterialSelected: number[] = []
  updateMaterialSupply: MaterialSupply[] = []  
  updateMaterialSupplySupplyID: number

  updateMaterialSupplyClick(materialSupplyList){
    this.updateMaterialSupplySupplyID = materialSupplyList[0].materialSupplyPK.supplyID
    this.updateMaterialSupplyBool = true        
    this.updateMaterialSupply = materialSupplyList            
    this.updateMaterialSelected = []
    this.updateMaterialSupply.forEach(element => {
      this.updateMaterialSelected.push(element.materialSupplyPK.materialID)
    });    
  }

  onChangeMaterialUpdate(e: MatSelectChange, i: number){    
    let temp: number = this.updateMaterialSelected.indexOf(e.value)        
    if(temp == -1){
      this.updateMaterialSelected[i] = e.value
    } else {
      alert("Material already selected")            
      this.updateMaterialSupply[i].materialSupplyPK.materialID = null      
      this.updateMaterialSelected[i] = 0
    }    
  }

  addMoreUpdateMaterial(){                    

    if(this.updateMaterialSupply.length == this.materialSelect.length){
      alert("Added all fields for materials")
    } else {
      this.updateMaterialSupply.push(new MaterialSupply())    
      this.updateMaterialSelected.push(0)
    }    
  }

  removeUpdateMaterial(i: number){   
    if(this.updateMaterialSupply.length == 1){
      alert("Can't remove material, needs to be one")
    } else {      
      this.updateMaterialSupply.splice(i, 1)      
      this.updateMaterialSelected.splice(i, 1)      
    }
  }

  updateMaterialSupplyClickFinal(){        
    this.supplyService.updateMaterialSupply(this.updateMaterialSupply, this.updateMaterialSupplySupplyID).subscribe((data: Supply)=>{
      alert("Successfully updated Material Supply")
      this.supplyService.getAllSupplies().subscribe((data: Supply[])=>{
        this.supplies = data
      })
      this.updateMaterialSupplyBool = false
      this.updateMaterialSelected = []
      this.updateMaterialSupply = []
    })
  }

  searchName: string = ""

  getAllSupplies(){
    this.supplyService.getAllSupplies().subscribe((data: Supply[])=>{
      this.supplies = data
    })
  }

  search(){
    this.supplyService.searchSupply(this.searchName).subscribe((data: Supply[])=>{
      this.supplies = data
    })
  }
}

