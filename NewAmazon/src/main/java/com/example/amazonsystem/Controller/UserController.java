package com.example.amazonsystem.Controller;
import com.example.amazonsystem.Model.User;
import com.example.amazonsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User Added");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("User is updated");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        boolean isValid = userService.deleteUser(id);
        if (isValid) {
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID or user doesn't exist");
    }


    @GetMapping("/buy/{id}/{pId}/{mId}")
    public ResponseEntity userBuy(@PathVariable Integer id, @PathVariable Integer pId, @PathVariable Integer mId) {
        char check = userService.userBuy(id, pId, mId);
        if (check == 'E') {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Merchant Stock doesn't exist");
        }
        if (check == 'D') {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID or Product may not exist ");
        }
        if (check == 'F') {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID or Merchant doesn't exist");
        }
        if (check == 'U') {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID or user doesn't exist");
        }
        if (check == 'S') {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product out of stock");
        }
        if (check == 'N') {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Product Purchased");
    }


}