package com.example.amazonsystem.Service;
import com.example.amazonsystem.Model.*;
import com.example.amazonsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;

    public List<User> getUsers(){

        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);

    }

    public boolean updateUser(Integer id,User user){
        User user1 = userRepository.getById(id);
        if (user1 == null){
            return false;
        }
        user1.setUserName(user.getUserName());
        user1.setRole(user.getRole());
        user1.setBalance(user.getBalance());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return true;
    }


    public boolean deleteUser(Integer id){
        User user = userRepository.getById(id);
        if (user == null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    //End point 12
    public char userBuy(Integer id,Integer pId,Integer mId){
        char a,b,c,d,e;
        a=b=c=d=e=' ';
        double price =0;
        double balance =0;
        int count = 0;

        for (MerchantStock merchantStock:merchantStockService.getMerchantStocks()) {
            if (merchantStock.getProductId() == pId && merchantStock.getMerchantId()  == mId) {
                c ='C';
                break;
            }
        }

        for (Product product:productService.getProducts()) {
            if (product.getId() == pId) {
                b= 'B';
                price = product.getPrice()*count;
                break;
            }
        }

        for (Merchant merchant: merchantService.getMerchants()) {
            if (merchant.getId().equals(mId)) {
                a = 'D';
                break;
            }
        }
        if (c != 'C' && b !='B' && a != 'D'){
            return 'E';
        }

        if (b != 'B'){
            return 'D';
        }

        if (a != 'D' ){
            return 'F';
        }

        for (User user: userRepository.findAll()) {
            if (user.getId().equals(id)){
                balance =user.getBalance();
                d = 'U';
                break;
            }
        }

        if (d != 'U'){
            return 'U';
        }

        for (MerchantStock merchantStock:merchantStockService.getMerchantStocks()) {
            if (merchantStock.getProductId().equals(pId) && merchantStock.getMerchantId().equals(mId)) {
                if (merchantStock.getStock() >= count) {
                    e = 's';

                }
            }
        }
        if (e != 's'){
            return 'S';
        }

        if (balance < price){
            return 'N';
        }

        for (MerchantStock merchantStock:merchantStockService.getMerchantStocks()) {
            if (merchantStock.getProductId().equals(pId) && merchantStock.getMerchantId().equals(mId)) {
                merchantStock.setStock(merchantStock.getStock()-count);
            }
        }

        for (User user:userRepository.findAll()) {
            if (user.getId().equals(id)){
                user.setBalance(user.getBalance()-price);
            }
        }


        return 'A';
    }


}




