package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialUsed;
import rs.ac.bg.etf.funeral.company.backend.entity.Model;
import rs.ac.bg.etf.funeral.company.backend.entity.Product;
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
}
