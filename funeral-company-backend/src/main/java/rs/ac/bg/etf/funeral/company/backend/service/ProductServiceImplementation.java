package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.*;
import rs.ac.bg.etf.funeral.company.backend.repository.MaterialUsedRepository;
import rs.ac.bg.etf.funeral.company.backend.repository.ModelRepository;
import rs.ac.bg.etf.funeral.company.backend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MaterialUsedRepository materialUsedRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {

        List<MaterialUsed> listTemp = product.getMaterialUsedList();
        product.setMaterialUsedList(null);
        productRepository.saveAndFlush(product);

        for(MaterialUsed materialUsed: listTemp){
            materialUsed.getMaterialUsedPK().setProductID(product.getProductID());
            materialUsedRepository.save(materialUsed);
        }

        product.setMaterialUsedList(listTemp);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        Product productDB = productRepository.findById(product.getProductID()).get();

        if(product.getPrice() != null){
            productDB.setPrice(product.getPrice());
        }
        if(product.getHeight() != null){
            productDB.setHeight(product.getHeight());
        }
        if(product.getWidth() != null){
            productDB.setWidth(product.getWidth());
        }
        if(product.getDepth() != null){
            productDB.setDepth(product.getDepth());
        }
        if(product.getModel() != null){
            Model model = modelRepository.findById(product.getModel().getModelID()).get();
            productDB.setModel(model);
        }

        return productRepository.save(productDB);
    }

    @Override
    public String deleteProduct(Long productID) {

        Product product = productRepository.findById(productID).get();
        List<MaterialUsed> muList = product.getMaterialUsedList();
        product.setMaterialUsedList(null);

        for(MaterialUsed mu: muList){
            materialUsedRepository.delete(mu);
        }
        productRepository.delete(product);
        return "Successfully deleted product";
    }

    @Override
    public Product updateMaterialUsed(List<MaterialUsed> materialUsedList) {
        List<MaterialUsed> muListDB = materialUsedRepository.findByMaterialUsedPK_ProductID(
                materialUsedList.get(0).getMaterialUsedPK().getProductID());

        Product productDB = productRepository.findById(materialUsedList.get(0).getMaterialUsedPK().getProductID()).get();
        productDB.setMaterialUsedList(null);
        productRepository.saveAndFlush(productDB);

        for(MaterialUsed mu: muListDB){
            materialUsedRepository.delete(mu);
        }

        for(MaterialUsed mu: materialUsedList){
            mu.getMaterialUsedPK().setProductID(productDB.getProductID());
            materialUsedRepository.saveAndFlush(mu);
        }
        productDB.setMaterialUsedList(materialUsedList);
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> searchProduct(boolean count, Long modelID) {

        if(modelID == 0){
            if(count){
                return productRepository.findByCountGreaterThan(0L);
            } else {
                return productRepository.findByCountGreaterThan(-1L);
            }
        } else {
            if(count){
                return productRepository.findByCountGreaterThanAndAndModel_ModelID(0L, modelID);
            } else {
                return productRepository.findByCountGreaterThanAndAndModel_ModelID(-1L, modelID);
            }
        }
    }
}
