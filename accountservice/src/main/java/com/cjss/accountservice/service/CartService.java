package com.cjss.accountservice.service;

import com.cjss.accountservice.entity.*;
import com.cjss.accountservice.model.CartModel;

import com.cjss.accountservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductPriceRepository productPriceRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemsOrderedRepository itemsOrderedRepository;

    public String addToCart(CartModel cartModel, String email){
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findByEmail(email));
        Integer quantityAvl = Integer.parseInt(inventoryRepository.findBySkuCode(cartModel.getSkuCode()).getQuantityAvailable());
        Integer quantityRequired = Integer.parseInt(cartModel.getQuantity());
        if(customerEntity.isPresent()){
            if(quantityAvl-quantityRequired>=0){
            CartEntity cartEntity = new CartEntity();
            cartEntity.setProductCode(cartModel.getProductCode());
            cartEntity.setSkuCode(cartModel.getSkuCode());
            cartEntity.setQuantity(cartModel.getQuantity());
            cartEntity.setCustomerEntity(customerEntity.get());
            customerEntity.get().getCartEntityList().add(cartEntity);
            customerRepository.save(customerEntity.get());
            return "OK";
            }
            else{return "LIMITED STOCK! AVAILABLE: "+ quantityAvl;}

        }else {return "EMAIL NOT FOUND";}
    }

    public String viewCart(String email){
        AtomicReference<Double> total= new AtomicReference<>(0.0);
        AtomicReference<String> output= new AtomicReference<>("");
        List <CartEntity> cartEntityList = customerRepository.findByEmail(email).getCartEntityList();
        cartEntityList.forEach(x->{
            String skuCode = x.getSkuCode();
            Integer quantity = Integer.parseInt(x.getQuantity());
            Double price = Double.parseDouble(productPriceRepository.findBySkuCode(x.getSkuCode()).getPrice());
            Double subTotal=(quantity*price);
            output.set(output+"\nSKUCODE: "+skuCode+"\tPrice: "+price +"\tQuantity: "+quantity +"\tSubTotal: "+subTotal);
            total.updateAndGet(v -> v + subTotal);
        });
        output.set(output+"\nTotal: "+total);
        return output.toString();
    }



    public String placeOrder(Long addressId, String email) {
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findByEmail(email));
        Optional <AddressEntity> address = customerEntity.get().getAddressEntityList().stream().filter(x->x.getAddressId()==addressId).findFirst();
        if(!address.isPresent()){
            return "ADDRESS ID MISMATCH";
        }
        if (customerEntity.get().getCartEntityList().size() > 0) {
//            AtomicReference<Double> total= new AtomicReference<>(0.0);
//            AtomicReference<String> output= new AtomicReference<>("");
            List<Long> cartId= new ArrayList<>();

            customerEntity.get().getCartEntityList().forEach(x -> {
                Integer quantityAvl = Integer.parseInt(inventoryRepository.findBySkuCode(x.getSkuCode()).getQuantityAvailable());
                Integer quantityRequired = Integer.parseInt(x.getQuantity());

                if (quantityAvl - quantityRequired >= 0) {
                   inventoryRepository.findBySkuCode(x.getSkuCode()).setQuantityAvailable(Integer.toString(quantityAvl-quantityRequired));
                   cartId.add(x.getId()); // sort out ordered products from cart
                }
            });


            List<OrderEntity> orderEntityList = customerEntity.get().getOrderEntityList();
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setShippingAddressEntity(convertToShippingAddress(address.get(), orderEntity));
            orderEntity.setCustomerEntity(customerEntity.get());

            //add purchased items to itemsOrderedEntityList
            List<ItemsOrderedEntity> itemsOrderedEntityList = new ArrayList<>();
            cartId.forEach(x->{
                ItemsOrderedEntity itemsOrderedEntity = new ItemsOrderedEntity();
                CartEntity cartEntity = cartRepository.findById(x).get();
                ProductPriceEntity productPriceEntity = productPriceRepository.findBySkuCode(cartEntity.getSkuCode());
                itemsOrderedEntity.setItemStatus("RECEIVED");
                itemsOrderedEntity.setProductCode(cartEntity.getProductCode());
                itemsOrderedEntity.setSkuCode(cartEntity.getSkuCode());
                itemsOrderedEntity.setQuantity(cartEntity.getQuantity());
                itemsOrderedEntity.setPrice(productPriceEntity.getPrice());
                itemsOrderedEntity.setOrderEntity(orderEntity);

                itemsOrderedEntityList.add(itemsOrderedEntity);
                itemsOrderedRepository.save(itemsOrderedEntity);

            });

            orderEntity.setItemsOrderedEntityList(itemsOrderedEntityList);
            orderEntityList.add(orderEntity);
            customerEntity.get().setOrderEntityList(orderEntityList);

            // remove  cartEntity(products) which are ordered
            cartId.forEach(x-> {
               customerEntity.get().getCartEntityList().removeIf(c ->c.getId()==x);
                //customerEntity.get().getCartEntityList().remove(customerEntity.get().getCartEntityList().stream().filter(y-> y.getId() == x).findFirst().get());
            });
            customerRepository.save(customerEntity.get());

            Integer orderListSize = orderEntityList.size();
            AtomicReference<String> orderBill = new AtomicReference<>("");
            if(orderListSize>0) {
                Integer presentOrderIndex = orderEntityList.size() - 1;  //reference for returning order details and price

                // for returning order details
                AtomicReference<Double> total= new AtomicReference<>(0.0);

                OrderEntity order = customerEntity.get().getOrderEntityList().get(presentOrderIndex - 1);
                order.getItemsOrderedEntityList().stream().forEach(x->
                        {
                            String skuCode = x.getSkuCode();
                            Integer quantity = Integer.parseInt(x.getQuantity());
                            Double price = Double.parseDouble(x.getPrice());
                            Double subTotal=(quantity*price);
                            orderBill.set(orderBill+"\nItems ID: "+ x.getItemId()+"\tSKUCODE: "+skuCode+"\tPrice: "+price +"\tQuantity: "+quantity +"\tSubTotal: "+subTotal);
                            total.updateAndGet(v -> v + subTotal);
                        });
                orderBill.updateAndGet(v->v+"\n Total: "+total);
            }
            return "ORDER PLACED WITH ORDER ID :"+orderEntity.getOrderId()+"\n product details :\n"+orderBill.toString();
        }
    return "EMPTY CART";
    }

    public ShippingAddressEntity convertToShippingAddress(AddressEntity addressEntity, OrderEntity orderEntity){
        ShippingAddressEntity shippingAddressEntity = new ShippingAddressEntity();
        shippingAddressEntity.setLine1(addressEntity.getLine1());
        shippingAddressEntity.setLine2(addressEntity.getLine2());
        shippingAddressEntity.setCity(addressEntity.getCity());
        shippingAddressEntity.setPostalCode(addressEntity.getPostalCode());
        shippingAddressEntity.setState(addressEntity.getState());
        shippingAddressEntity.setOrderEntity(orderEntity);
        return shippingAddressEntity;
    }

    public String itemsStatus(Long itemId){
        ItemsOrderedEntity itemsOrderedEntity= itemsOrderedRepository.findById(itemId).get();
        if(itemsOrderedEntity!=null)
            return "ITEM STATUS : "+itemsOrderedEntity.getItemStatus();
        else return "NULL";

    }

}
