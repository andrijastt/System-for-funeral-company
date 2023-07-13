package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialUsed;
import rs.ac.bg.etf.funeral.company.backend.entity.Product;
import rs.ac.bg.etf.funeral.company.backend.repository.MaterialUsedRepository;
import rs.ac.bg.etf.funeral.company.backend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MaterialUsedRepository materialUsedRepository;

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
}
