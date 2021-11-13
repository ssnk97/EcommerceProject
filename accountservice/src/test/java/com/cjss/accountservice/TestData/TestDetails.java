package com.cjss.accountservice.TestData;

import com.cjss.accountservice.entity.AddressEntity;
import com.cjss.accountservice.entity.CustomerEntity;
import com.cjss.accountservice.model.AddressModel;
import com.cjss.accountservice.model.CustomerModel;
import org.apache.tomcat.jni.Address;

import java.util.ArrayList;
import java.util.List;

public class TestDetails {
    public static CustomerEntity getCustomerEntityData(){
        CustomerEntity customerEntity = new CustomerEntity();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCustomerEntity(customerEntity);
        addressEntity.setAddressId(1l);
        addressEntity.setLine1("abc");
        addressEntity.setLine2("xyz");
        addressEntity.setBillingAddress(false);
        addressEntity.setShippingAddress(true);
        addressEntity.setCity("KAKINADA");
        addressEntity.setState("ANDHRA PRADESH");
        addressEntity.setPostalCode("5333440");
        List<AddressEntity> addressEntityList = new ArrayList<>();
        addressEntityList.add(addressEntity);

        customerEntity.setFirstName("Satya");
        customerEntity.setLastName("Surisetty");
        customerEntity.setPassword("NU610Ix8L8eE3TzJicRrzTEd+6nUFFQt");
        customerEntity.setEmail("satya@cjss.com");
        customerEntity.setMobileNumber("8341130864");
        customerEntity.setAddressEntityList(addressEntityList);

        return customerEntity;
    }

    public static CustomerModel getCustomerModelData(){
        CustomerModel customerModel = new CustomerModel();

        AddressModel addressModel = getAddressModelData();
        List<AddressModel> addressModelList = new ArrayList<>();
        addressModelList.add(addressModel);

        customerModel.setFirstName("Satya");
        customerModel.setLastName("Surisetty");
        customerModel.setPassword(null);
        customerModel.setEmail("satya@cjss.com");
        customerModel.setMobileNumber("8341130864");
        customerModel.setAddressModelList(addressModelList);

        return customerModel;
    }

    public static AddressModel getAddressModelData(){
        AddressModel addressModel = new AddressModel();
        addressModel.setLine1("abc");
        addressModel.setLine2("xyz");
        addressModel.setAddressId(1L);
        addressModel.setCity("KAKINADA");
        addressModel.setState("ANDHRA PRADESH");
        addressModel.setPostalCode("533440");
        addressModel.setBillingAddress(false);
        addressModel.setShippingAddress(true);
        return addressModel;
    }
}
