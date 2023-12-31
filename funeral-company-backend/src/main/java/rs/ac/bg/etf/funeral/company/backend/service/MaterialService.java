package rs.ac.bg.etf.funeral.company.backend.service;

import rs.ac.bg.etf.funeral.company.backend.entity.Category;
import rs.ac.bg.etf.funeral.company.backend.entity.Material;

import java.util.List;

public interface MaterialService {

    public List<Material> getAllMaterials();
    public Material saveMaterial(Material material);
    public String deleteMaterial(Long materialID);
    public Material updateMaterial(Material material);
    public List<Material> findByNameContainingAndCountGreaterThanAndCategory(String name, boolean count, Long categoryID);
}
