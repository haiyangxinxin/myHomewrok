package com.webank.blockchain.lagcredit.config;

import org.fisco.bcos.web3j.crypto.EncryptType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "encrypt-type")
public class EncryptTypeConfig {
    private int encryptType;

    @Bean
    public EncryptType getEncryptType() {
        return new EncryptType(encryptType);
    }

    public void setEncryptType(int encryptType) {
        this.encryptType = encryptType;
    }
}
