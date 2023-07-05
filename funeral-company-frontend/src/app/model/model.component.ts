import { Component } from '@angular/core';
import { ModelService } from '../model.service';
import { Model } from '../models/Model';

@Component({
  selector: 'app-model',
  templateUrl: './model.component.html',
  styleUrls: ['./model.component.css']
})
export class ModelComponent {

  constructor(private modelService: ModelService){}

  models: Model[]

  ngOnInit(){
    this.modelService.getAllModels().subscribe((data : Model[])=>{
      this.models = data;
    })
  }

  getAllModels(){
    this.modelService.getAllModels().subscribe((data : Model[])=>{
      this.models = data;
    })
  }

  addModel: boolean = false  

  name: string
  description: string
  dataNotFilled: boolean = false

  saveModel(){

    if(this.name == null || this.description == null){
      this.dataNotFilled = true
    } else {
      this.modelService.saveModel(this.name, this.description).subscribe((data: Model)=>{

        alert("Model successfuly added")
        this.modelService.getAllModels().subscribe((data : Model[])=>{
          this.models = data;
        })
        this.dataNotFilled = false
        this.addModel = false
        this.name = null
        this.description = null
      },
      (error)=>{
        alert("Model with that name already exists!")
      })
    }

  }

  updateModelButton: boolean = false
  updateModelID: number
  updateName: string
  updateDescription: string

  updateModelButtonClick(model: Model){
    this.updateModelButton = true
    this.updateModelID = model.modelID
    this.updateName = model.name
    this.updateDescription = model.description
  }

  updateModel(){
    this.modelService.updateModel(this.updateModelID, this.updateName, this.updateDescription).subscribe((data: Model)=>{
      alert("Model successfuly updated")
        this.modelService.getAllModels().subscribe((data : Model[])=>{
          this.models = data;
        })
        this.updateModelButton = false
        this.updateModelID = null
        this.updateName = null
        this.updateDescription = null
    })
  }

  removeMaterial(modelID){
    this.modelService.removeModel(modelID).subscribe((data: string)=>{
      alert(data)
      this.modelService.getAllModels().subscribe((data: Model[])=>{
        this.models = data
      })
    },
    (error)=>{
      alert("Successfully deleted material")
      this.modelService.getAllModels().subscribe((data: Model[])=>{
        this.models = data
      })
    }
    )
  }

  nameSearch: string = ""
  search(){
    this.modelService.search(this.nameSearch).subscribe((data: Model[])=>{      
      this.models = data
    })
  }

}
