package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.funeral.company.backend.entity.Material;
import rs.ac.bg.etf.funeral.company.backend.service.MaterialService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping(path = "/materials")
    public List<Material> getAllMaterials(){
        return materialService.getAllMaterials();
    }

    @PostMapping(path = "/material")
    public Material saveMaterial(@Valid @RequestBody Material material){
        return materialService.saveMaterial(material);
    }

    @DeleteMapping(path = "/material/{materialID}")
    public String deleteMaterial(@Valid @PathVariable("materialID") Long materialID){
        return materialService.deleteMaterial(materialID);
    }
}
