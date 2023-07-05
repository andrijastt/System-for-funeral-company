package rs.ac.bg.etf.funeral.company.backend.service;

import rs.ac.bg.etf.funeral.company.backend.entity.Model;

import java.util.List;

public interface ModelService {

    List<Model> getAllModels();
    Model saveModel(Model model);
    Model updateModel(Long modelID, String name, String description);
    List<Model> getAllModelsByNameContaining(String name);
    String deleteModel(Long modelID);
}
