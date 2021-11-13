package com.cjss.accountservice.service;

import com.cjss.accountservice.entity.ItemsOrderedEntity;
import com.cjss.accountservice.entity.OrderEntity;
import com.cjss.accountservice.repository.ItemsOrderedRepository;
import com.cjss.accountservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FulfilmentService {
    @Autowired
    ItemsOrderedRepository itemsOrderedRepository;
    public String updateItemStatus(Long itemId, String status){
        Optional<ItemsOrderedEntity> item = itemsOrderedRepository.findById(itemId);
        if(item.isPresent()) {
            item.get().setItemStatus(status);
            itemsOrderedRepository.save(item.get());
            return "STATUS UPDATED : "+item.get().getItemStatus();
        }
        else return "INVALID ID";
    }
}
