package rs.ac.bg.etf.funeral.company.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
