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
}
