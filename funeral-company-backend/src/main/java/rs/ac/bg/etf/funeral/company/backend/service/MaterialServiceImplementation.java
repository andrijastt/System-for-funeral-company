package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Material;
import rs.ac.bg.etf.funeral.company.backend.repository.MaterialRepository;

import java.util.List;

@Service
public class MaterialServiceImplementation implements MaterialService{

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
}
