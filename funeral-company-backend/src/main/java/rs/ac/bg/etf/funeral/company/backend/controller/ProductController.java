package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.funeral.company.backend.entity.MaterialUsed;
import rs.ac.bg.etf.funeral.company.backend.entity.Product;
import rs.ac.bg.etf.funeral.company.backend.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping(path = "/product")
    public Product saveProduct(@Valid @RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PostMapping(path = "/product/update")
    public Product updateProduct(@Valid @RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping(path = "/product/delete/{productID}")
    public String updateProduct(@Valid @PathVariable("productID") Long productID){
        return productService.deleteProduct(productID);
    }

    @PostMapping(path = "/product/material/update")
    public Product updateMaterialUsed(@Valid @RequestBody Product product){
        return productService.updateMaterialUsed(product.getMaterialUsedList());
    }

    @GetMapping(path = "/product/search")
    public List<Product> searchProduct(@Valid @RequestParam("count") Boolean count, @Valid @RequestParam("modelID") Long modelID){
        return productService.searchProduct(count, modelID);
    }

    @PostMapping(path = "/product/add/{productID}/{amount}")
    public String addProduct(@Valid @PathVariable("productID") Long productID, @Valid @PathVariable("amount") Long amount){
        return productService.addProduct(productID, amount);
    }
}
