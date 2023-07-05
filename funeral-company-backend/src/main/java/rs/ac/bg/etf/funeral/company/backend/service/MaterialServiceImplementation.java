package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Category;
import rs.ac.bg.etf.funeral.company.backend.entity.Material;
import rs.ac.bg.etf.funeral.company.backend.repository.CategoryRepository;
import rs.ac.bg.etf.funeral.company.backend.repository.MaterialRepository;

import java.util.List;

@Service
public class MaterialServiceImplementation implements MaterialService{

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material saveMaterial(Material material) {
        Category category = categoryRepository.findById(material.getCategory().getCategoryID()).get();
        material.setCategory(category);
        return materialRepository.save(material);
    }

    @Override
    public String deleteMaterial(Long materialID) {
        Material materialDB = materialRepository.findById(materialID).get();
        materialDB.getCategory().removeMaterial(materialDB);
        materialRepository.deleteById(materialID);
        return "Successfully deleted material!";
    }

    @Override
    public Material updateMaterial(Material material) {
        Material materialDB = materialRepository.findById(material.getMaterialID()).get();
        Category categoryDB = categoryRepository.findById(material.getCategory().getCategoryID()).get();
        materialDB.setCategory(categoryDB);
        if(!material.getName().equals(""))
            materialDB.setName(material.getName());
        if(!material.getUnit().equals(""))
            materialDB.setUnit(material.getUnit());
        if(material.getPrice() != null)
            materialDB.setPrice(material.getPrice());
        return materialRepository.save(materialDB);
    }

    @Override
    public List<Material> findByNameContainingAndCountGreaterThan(String name, boolean count) {
        if(count){
            return materialRepository.findByNameContainingAndCountGreaterThan(name, 0);
        } else {
            return materialRepository.findByNameContainingAndCountGreaterThan(name, -1);
        }
    }

    @Override
    public List<Material> findByNameContaining(String name) {
        return materialRepository.findByNameContaining(name);
    }
}
