package com.example.amazonsystem.Service;

import com.example.amazonsystem.Model.Merchant;
import com.example.amazonsystem.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchants;
    public List<Merchant> getMerchants(){
        return merchants.findAll();
    }

    public void addMerchant(Merchant merchant){
        merchants.save(merchant);
    }

    public boolean updateMerchant(Integer id, Merchant merchant){
        Merchant merchant1 = merchants.getById(id);
        if (merchant1 == null)
        return false;

        merchant1.setName(merchant.getName());
        merchants.save(merchant);
        return true;
    }

    public boolean deleteMerchant(Integer id){
      Merchant merchant = merchants.getById(id);
      if (merchant == null)
        return false;

      merchants.delete(merchant);
      return true;
    }




}
