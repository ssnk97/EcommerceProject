package com.cjss.accountservice.service;

import com.cjss.accountservice.entity.InventoryEntity;
import com.cjss.accountservice.entity.ProductSizeEntity;
import com.cjss.accountservice.model.InventoryModel;
import com.cjss.accountservice.repository.InventoryRepository;
import com.cjss.accountservice.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;
@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ProductSizeRepository productSizeRepository;

    public ProductSizeEntity addInventory(InventoryModel inventoryModel, String skuCode){
        Optional<ProductSizeEntity> productSizeEntity = Optional.ofNullable(productSizeRepository.findBySkuCode(skuCode));
        if(productSizeEntity.isPresent()){
            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());
            inventoryEntity.setSkuCode(skuCode);
            productSizeEntity.get().setInventoryEntity(inventoryEntity);
            return productSizeRepository.save(productSizeEntity.get());
        }
        else return null;

    }
}
