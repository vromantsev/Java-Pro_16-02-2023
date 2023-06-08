package ua.hillel.web.webservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.jdbc.entity.Product;
import ua.hillel.testing.ProductService;

@RequestMapping("/api/hello")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") final Long productId) {
        var product = this.productService.findById(productId);
        return ResponseEntity.ok(product);
    }
}
