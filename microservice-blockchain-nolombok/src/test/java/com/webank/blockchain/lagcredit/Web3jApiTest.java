package com.webank.blockchain.lagcredit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.webank.blockchain.lagcredit.config.ServiceConfig;

//@Slf4j
public class Web3jApiTest extends BaseTest {

    @Autowired Web3j web3j;
    
    private static Logger log = LoggerFactory.getLogger(Web3jApiTest.class);
    
    @Test
    public void getBlockNumber() throws IOException {
        BigInteger blockNumber = web3j.getBlockNumber().send().getBlockNumber();
        log.info("blockNumber is {}", blockNumber);
        assertTrue(blockNumber.compareTo(new BigInteger("0")) >= 0);
        ServiceConfig sc = new ServiceConfig();
    }
}
