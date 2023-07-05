package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.funeral.company.backend.entity.Model;
import rs.ac.bg.etf.funeral.company.backend.service.ModelService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @GetMapping(path = "/models")
    public List<Model> getAllModels(){
        return modelService.getAllModels();
    }

    @PostMapping(path = "/model")
    public Model saveModel(@Valid @RequestBody Model model){
        return modelService.saveModel(model);
    }

    @PostMapping(path = "/model/update")
    public Model updateModel(@Valid @RequestBody Model model){
        return modelService.updateModel(model);
    }

    @GetMapping(path = "/model/search")
    public List<Model> getAllModelsByNameContaining(@Valid @PathVariable("name") String name){
        return modelService.getAllModelsByNameContaining(name);
    }

    @DeleteMapping(path = "/model/delete/{modelID}")
    public String deleteModel(@Valid @PathVariable("modelID") Long modelID){
        return modelService.deleteModel(modelID);
    }
}
