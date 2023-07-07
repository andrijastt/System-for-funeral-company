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
        category.addMaterials(material);
        material.setCategory(category);
        categoryRepository.save(category);
        return materialRepository.save(material);
    }

    @Override
    public String deleteMaterial(Long materialID) {
        Material materialDB = materialRepository.findById(materialID).get();
        materialDB.getCategory().removeMaterial(materialDB);
        materialDB.setCategory(null);
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
    public List<Material> findByNameContainingAndCountGreaterThanAndCategory(String name, boolean count, Long categoryID) {
        if(categoryID == 0){
            if(count){
                return materialRepository.findByNameContainingAndCountGreaterThan(name, 0);
            } else {
                return materialRepository.findByNameContainingAndCountGreaterThan(name, -1);
            }
        } else {
            Category category1 = categoryRepository.findById(categoryID).get();
            if(count){
                return materialRepository.findByNameContainingAndCountGreaterThanAndCategory(name, 0, category1);
            } else {
                return materialRepository.findByNameContainingAndCountGreaterThanAndCategory(name, -1, category1);
            }
        }

    }
}
