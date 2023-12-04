package com.example.amazonsystem.Controller;

import com.example.amazonsystem.Model.MerchantStock;
import com.example.amazonsystem.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add/{mID}/{pID}")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Character isValid = merchantStockService.addMerchantStock(merchantStock);
        if (isValid == 'A') {
            return ResponseEntity.status(HttpStatus.OK).body("Merchant Stock added");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Category id or it doesn't exist");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @Valid @RequestBody MerchantStock merchantStock,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isValid = merchantStockService.updateMerchantStock(id,merchantStock);
        if (isValid){
            return ResponseEntity.status(HttpStatus.OK).body("Merchant Stock Updated");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid MerchantStock or doesn't exist(Product and merchant IDs must exist)");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id){
        boolean isValid = merchantStockService.deleteMerchantStock(id);
        if (isValid){
            return ResponseEntity.status(HttpStatus.OK).body("Merchant Stock deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID or doesn't exist");
    }

    @PutMapping("/addstock/{pId}/{mId}/{amount}")
    public ResponseEntity addToStock(@PathVariable Integer pId ,@PathVariable Integer mId,@PathVariable Integer amount) {
        char isValid = merchantStockService.addToStock(pId, mId, amount);
        if (isValid == 'A') {
            return ResponseEntity.status(HttpStatus.OK).body("Amount added to Stock");
        }
        if (isValid == 'F') {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid merchant ID");
        }
        if (isValid == 'D'){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Product ID");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Merchant stock doesn't exist");
    }


}
