package mx.edu.tecdesoftware.market_backend_2026_3_b.web.controller;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> gellAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .filter(products -> !products.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int productId){
        if (productService.delete(productId)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}

