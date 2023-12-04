package com.example.amazonsystem.Service;
import com.example.amazonsystem.Model.Merchant;
import com.example.amazonsystem.Model.MerchantStock;
import com.example.amazonsystem.Model.Product;
import com.example.amazonsystem.Model.User;
import com.example.amazonsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
            if (merchantStock.getProductId().equals(pId) && merchantStock.getMerchantId().equals(mId)) {
                c ='C';
                break;
            }
        }


        for (Product product:productService.getProducts()) {
            if (product.getId().equals(pId)) {
                b= 'B';
                price = product.getPrice()*count;
                break;
            }}
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

    public int addToCart(Integer id,Integer pId,Integer stock){
        int a,b,c,d,e;
        a=b=c=d=e= 0;
        //find user
        for (User user:userRepository.findAll()) {
            if (user.getId().equals(id)){
                a =1;
                break;
            }
        }
        //check if user role is customer to add
        for (User user:userRepository.findAll()) {
            if (user.getId().equals(id)) {
                if (user.getRole().equalsIgnoreCase("Customer")) {
                    b = 2;
                    break;
                }
            }
        }   // find product
            for (Product product: productService.getProducts()) {
                if (product.getId().equals(pId)){
                    c= 3;
                    break;
                }
            }
            //find merchant stock
        for (MerchantStock merchantStock:merchantStockService.getMerchantStocks()) {
            if(merchantStock.getProductId().equals(pId)) {
                d = 4;
                break;
            }
        }
        //check if merchant stock  greater or equal to stock
        for (MerchantStock merchantStock:merchantStockService.getMerchantStocks()) {
            if(merchantStock.getProductId().equals(pId)) {
                if (merchantStock.getStock() >= stock) {
                    e = 5;
                    break;
                }
            }
        }
        //user doesn't exist
        if (a == 0){
            return 1;
        }
        //user role is not customer
        if (b == 0){
            return 2;
        }
        //product doesn't exist
        if (c == 0){
            return 3;
        }
        //no available sellers
        if (d == 0){
            return 4;
        }
        //product out of stock
        if (e == 0){
            return 5;
        }

        //add product and its amount to user cart
        for (User user:userRepository.findAll()) {
            if (user.getId().equals(id)){
                for (Product product: productService.getProducts()) {
                    if (product.getId().equals(pId)) {
                        for (int i = 0; i < stock; i++) {

                        }
                    }
                }
            }
        }
        //return 0 to indicate that all conditions are met to add a product
        return 0;
    }

    public List<Product> displayCart(Integer id){
        //find user and display user cart
        for (User user:userRepository.findAll()) {
            if (user.getId().equals(id)){
                return productService.getProducts();
            }
        }
        return null;
    }

//    public char checkOut(Integer id,Integer pId,Integer mId) {
//        //method buy
//        char check = userBuy(id, pId, mId);
//        //ArrayList that has user product objects.
//        ArrayList<Product> userCart = displayCart(id);
//        //update the array to avoid errors since it's a virtual database.
//        cart.clear();
//        //array updated
//        cart.addAll(userCart);
//        //remove purchased products from user cart
//        for (User user : users) {
//            if (user.getId().equals(id)) {
//                    if (!user.getCart().isEmpty()) {
//                        for (Product product : cart) {
//                        if (product.getId().equals(pId)) {
//                                user.getCart().remove(product);
//                        }
//                        }
//                        //return a message when the user cart is empty
//                    }else check = 'V'; break;
//                }
//            }
//            return check;
//        }

    }

