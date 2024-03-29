/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.blockchain.lagcredit.tools;

import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.utils.Numeric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UserKeyUtils
 *
 * @Description: UserKeyUtils
 * @author graysonzhang
 * @data 2019-05-08 15:19:50
 *
 */
//@Slf4j
public class UserKeyUtils{
    
    static final int PUBLIC_KEY_SIZE = 64;
    static final int PUBLIC_KEY_LENGTH_IN_HEX = PUBLIC_KEY_SIZE << 1;

    private static Logger log = LoggerFactory.getLogger(UserKeyUtils.class);
    
    public static void createUserKey() {
        try {
            ECKeyPair keyPair = Keys.createEcKeyPair();
            String publicKey = Numeric.toHexStringWithPrefixZeroPadded(keyPair.getPublicKey(), PUBLIC_KEY_LENGTH_IN_HEX);
            String privateKey = Numeric.toHexStringNoPrefix(keyPair.getPrivateKey());
            String address = "0x" + Keys.getAddress(publicKey);
            
            log.info("public key : {}", publicKey);
            log.info("private key: {}", privateKey);
            log.info("address : {}", address);
            
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    public static String getAdrressbyPublicKey(String publicKey) {
    	String address = "0x" + Keys.getAddress(publicKey);
    	return address;
    }
    
    public static void main(String[] args) {
        createUserKey();
    	String publicKey = "bd365f2a5f28c946804340c9fc92cc88105e695e92f035d7bb286024c1268a5d";
    	String adrressbyPublicKey = getAdrressbyPublicKey(publicKey);
    	log.info("public key : {}", publicKey);
    	log.info("address: {}",adrressbyPublicKey);
    }

}
