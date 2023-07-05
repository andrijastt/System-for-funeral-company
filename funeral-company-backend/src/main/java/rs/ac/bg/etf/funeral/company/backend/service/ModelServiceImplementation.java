package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Model;
import rs.ac.bg.etf.funeral.company.backend.repository.ModelRepository;

import java.util.List;

@Service
public class ModelServiceImplementation implements ModelService{

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public Model saveModel(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public Model updateModel(Long modelID, String name, String description) {

        Model modelDB = modelRepository.findById(modelID).get();

        if(!name.equals(""))
            modelDB.setName(name);

        if(!description.equals(""))
            modelDB.setDescription(description);

        return modelRepository.save(modelDB);
    }

    @Override
    public List<Model> getAllModelsByNameContaining(String name) {
        return modelRepository.findByNameContaining(name);
    }

    @Override
    public String deleteModel(Long modelID) {
        modelRepository.deleteById(modelID);
        return "Model successfully deleted!";
    }
}
