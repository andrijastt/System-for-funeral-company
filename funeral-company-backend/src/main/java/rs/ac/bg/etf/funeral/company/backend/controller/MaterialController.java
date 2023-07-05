package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.funeral.company.backend.entity.Category;
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
    @PostMapping(path = "/material/update")
    public Material updateMaterial(@Valid @RequestBody Material material){
        return materialService.updateMaterial(material);
    }

    @DeleteMapping(path = "/material/{materialID}")
    public String deleteMaterial(@Valid @PathVariable("materialID") Long materialID){
        return materialService.deleteMaterial(materialID);
    }

    @GetMapping(path = "/material/search")
    public List<Material> searchMaterials(@Valid @RequestParam("name") String name, @Valid @RequestParam("countFlag") boolean countFlag){
        System.out.println("name = " + name);
        System.out.println("countFlag = " + countFlag);
//        System.out.println("category = " + category);
        return materialService.findByNameContainingAndCountGreaterThan(name, countFlag);
    }

    @GetMapping(path = "/material/name")
    public List<Material> findByNameContaining(@Valid @RequestParam("name") String name){
        return materialService.findByNameContaining(name);
    }
}
