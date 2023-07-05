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

    @PutMapping(path = "/model/update")
    public Model updateModel(@Valid @PathVariable("materialID") Long materialID, @Valid @PathVariable("name") String name,
                             @Valid @PathVariable("description") String description){
        return modelService.updateModel(materialID, name, description);
    }

    @GetMapping(path = "/material/search")
    public List<Model> getAllModelsByNameContaining(@Valid @PathVariable("name") String name){
        return modelService.getAllModelsByNameContaining(name);
    }

    @DeleteMapping(path = "/material/delete")
    public String deleteModel(@Valid @PathVariable("materialID") Long materialID){
        return modelService.deleteModel(materialID);
    }
}