package com.cjss.accountservice.service;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.stereotype.Service;

@Service
public class JasyptService {

    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    public void initConfig(){
        config.setPassword("cjss_encryption"); // encryptor's private key
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
    }

    public String encryptPassword(String password) {
        initConfig();
        return encryptor.encrypt(password);
    }

    public String decryptPassword(String password){
        initConfig();
        return encryptor.decrypt(password);
    }
}
