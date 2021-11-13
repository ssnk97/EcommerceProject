package com.cjss.accountservice.service;

import com.cjss.accountservice.entity.CustomerEntity;
import com.cjss.accountservice.entity.InventoryEntity;
import com.cjss.accountservice.entity.ItemsOrderedEntity;
import com.cjss.accountservice.entity.OrderEntity;
import com.cjss.accountservice.repository.CustomerRepository;
import com.cjss.accountservice.repository.InventoryRepository;
import com.cjss.accountservice.repository.ItemsOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturnService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemsOrderedRepository itemsOrderedRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    public String returnItem(Long itemId, String email){
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findByEmail(email));
        if(customerEntity.isPresent()){
          Optional<ItemsOrderedEntity> item =itemsOrderedRepository.findById(itemId);
          if(item.isPresent()){
                InventoryEntity inventoryEntity = inventoryRepository.findBySkuCode(item.get().getSkuCode());
                inventoryEntity.setQuantityAvailable(inventoryEntity.getQuantityAvailable()+item.get().getQuantity());
                item.get().setItemStatus("RETURN");
                inventoryRepository.save(inventoryEntity);
            return "RETURN ACCEPTED\n ITEM ID: "+ item.get().getItemId() + "\tREFUND AMOUNT: "+ (Double.parseDouble(item.get().getPrice()) * Integer.parseInt(item.get().getQuantity()));
          }else return "INVALID ITEM ID";
        }
        else return "EMAIL NOT FOUND";
    }
}
