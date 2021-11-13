package com.cjss.accountservice.service;

import com.cjss.accountservice.entity.ProductEntity;
import com.cjss.accountservice.entity.ProductPriceEntity;
import com.cjss.accountservice.entity.ProductSizeEntity;
import com.cjss.accountservice.model.ProductModel;
import com.cjss.accountservice.model.ProductPriceModel;
import com.cjss.accountservice.model.ProductSizeModel;
import com.cjss.accountservice.repository.ProductPriceRepository;
import com.cjss.accountservice.repository.ProductRepository;
import com.cjss.accountservice.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductSizeRepository productSizeRepository;
    @Autowired
    ProductPriceRepository productPriceRepository;

    public ProductEntity addProduct(ProductModel productModel){
        ProductEntity productEntity = new ProductEntity();
        List<ProductSizeEntity> productSizeList = new ArrayList<>();

        productEntity.setProductCode(productModel.getProductCode());
        productEntity.setProductName(productModel.getProductName());
        productEntity.setDescription(productModel.getDescription());

        productEntity.setProductSizeList(productSizeList);
        return productRepository.save(productEntity);
    }
    public ProductEntity addSkusSize(ProductSizeModel productSizeModel){  //SKUS == SIZE
        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findByProductCode(productSizeModel.getProductCode()));
       if(productEntity.isPresent()){
           ProductSizeEntity productSizeEntity = new ProductSizeEntity();
           productSizeEntity.setSkuCode(productSizeModel.getSkuCode());
           productSizeEntity.setSize(productSizeModel.getSize());
           productSizeEntity.setProductEntity(productEntity.get());
           productEntity.get().getProductSizeList().add(productSizeEntity);
       }
        return productRepository.save(productEntity.get());
    }

    public ProductSizeEntity addPrice(ProductPriceModel productPriceModel){
        Optional<ProductSizeEntity> productSizeEntity = Optional.ofNullable(productSizeRepository.findBySkuCode(productPriceModel.getSkuCode()));
        if(productSizeEntity.isPresent()) {
            ProductPriceEntity productPriceEntity = new ProductPriceEntity();
            productPriceEntity.setPrice(productPriceModel.getPrice());
            productPriceEntity.setCurrency(productPriceModel.getCurrency());
            productPriceEntity.setSkuCode(productPriceModel.getSkuCode());
            productSizeEntity.get().setProductPriceEntity(productPriceEntity);
            return productSizeRepository.save(productSizeEntity.get());
        }
      else return null;
    }

}