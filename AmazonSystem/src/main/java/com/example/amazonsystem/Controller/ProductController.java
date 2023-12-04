package com.example.amazonsystem.Controller;

import com.example.amazonsystem.Model.Product;
import com.example.amazonsystem.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
private final ProductService productService;

@GetMapping("/get")
public ResponseEntity getProducts(){
    return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
}

@PostMapping("/add/{category_id}")
public ResponseEntity addProduct(@PathVariable Integer category_id,@Valid @RequestBody Product product, Errors errors ){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
        productService.addProduct(category_id,product);
        return ResponseEntity.status(HttpStatus.OK).body("Product Added");
}

@PutMapping("/update/{id}")
public ResponseEntity updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product, Errors errors){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    boolean isValid = productService.updateProduct(id,product);
    if (isValid){
        return ResponseEntity.status(HttpStatus.OK).body("Product Updated");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID or product doesn't exist. (ID cannot be updated)");
}
@DeleteMapping("/delete/{id}")
public ResponseEntity deleteProduct(@PathVariable Integer id){
    boolean isValid = productService.deleteProduct(id);
    if(isValid){
        return ResponseEntity.status(HttpStatus.OK).body("Product Deleted");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID or product doesn't exist");
}
}
