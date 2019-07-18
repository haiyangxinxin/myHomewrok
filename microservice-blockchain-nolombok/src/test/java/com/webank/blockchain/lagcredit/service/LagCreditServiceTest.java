package com.webank.blockchain.lagcredit.service;

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

import java.math.BigInteger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.webank.blockchain.lagcredit.BaseTest;
import com.webank.blockchain.lagcredit.Web3jApiTest;
import com.webank.blockchain.lagcredit.contracts.LAGCredit;

/**
 * LagCreditServiceTest
 *
 * @Description: LagCreditServiceTest
 * @author graysonzhang
 * @data 2019-05-08 17:48:14
 *
 */
//@Slf4j
public class LagCreditServiceTest extends BaseTest {
    
    @Autowired
    private LagCreditService lagCreditService;
    
    private String owner = "0xb1f49311c909bb22fdaf366771a15a983e8c0148";
    
    private String toAddr = "0x015e68c28690b3250b36319d7c04653e0fbc4f26";
    
    private String creditAddr = "0x485f6c67e62e17f3744379221bff6e92f36d7190";//UPDATE 2019-07-12
    
    
    private static Logger log = LoggerFactory.getLogger(LagCreditServiceTest.class);
    
    
    @Test
    public void testdeploy(){
        LAGCredit lagCredit = lagCreditService.deploy();
        log.info("LAGCredit address : {}", lagCredit);
    }
    
    @Test
    public void testLoad(){
        LAGCredit lagCredit = lagCreditService.load(creditAddr);
        log.info("LAGCredit address : {}", lagCredit.getContractAddress());
    }
    
    @Test
    public void testTransfer(){
        
        boolean flag = lagCreditService.transfer(creditAddr, toAddr, new BigInteger("1000"));
        if(flag){
            log.info("transfer success!");
        }else{
            log.info("transfer failed!");
        }
    }
    
    @Test
    public void testGetBalanceByOwner() throws Exception{
        long balance = lagCreditService.getBalanceByOwner(creditAddr, owner);
        log.info("the owner : {}, balance : {}", owner, balance);
        long tobalance = lagCreditService.getBalanceByOwner(creditAddr, toAddr);
        log.info("the to : {}, balance : {}", toAddr, tobalance);
    }
    
    @Test 
    public void testGetTotalSupply() throws Exception{
        long total = lagCreditService.getTotalSupply(creditAddr);
        log.info("total : {}", total);
    }

}
