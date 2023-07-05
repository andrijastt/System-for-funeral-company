package rs.ac.bg.etf.funeral.company.backend.service;

import rs.ac.bg.etf.funeral.company.backend.entity.Model;

import java.util.List;

public interface ModelService {

    List<Model> getAllModels();
    Model saveModel(Model model);
    Model updateModel(Model model);
    List<Model> getAllModelsByNameContaining(String name);
    String deleteModel(Long modelID);
}
