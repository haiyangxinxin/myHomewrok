package com.webank.blockchain.lagcredit.service;


import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webank.blockchain.lagcredit.constants.GasConstants;
import com.webank.blockchain.lagcredit.contracts.PetContract;

/**
 * PetContractService
 *
 * @Description: PetContractService
 * @author haiyangxinxin
 * @data 2019-07-18 15:43:44
 *
 */
@Service
//@Slf4j
public class PetContractService {
    @Autowired 
    private Web3j web3j;
    @Autowired 
    private Credentials credentials;
    
    private static Logger log = LoggerFactory.getLogger(PetContractService.class);

    public PetContract deploy() {
        PetContract PetContract = null;
        try {
            PetContract = PetContract.deploy(web3j, credentials, new StaticGasProvider(
                    GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT),new BigInteger("1000"),new BigInteger("20000")).send();
            log.info("PetContract address is {}", PetContract.getContractAddress());
            return PetContract;
        } catch (Exception e) {
          log.error("deploy lacg contract fail: {}", e.getMessage());
        }  
        return PetContract;
    }
    
    //根據地址取得合約對象
    public PetContract load(String creditAddress){
    	PetContract lagCredit = PetContract.load(creditAddress, web3j, credentials, new StaticGasProvider(
                    GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        return lagCredit;
    }
    
    //領養一個寵物
    public BigInteger pickApet(String fromAddr,String creditAddress,BigInteger i) {
    	
    	try {
    		TransactionReceipt send = this.load(creditAddress).adoptOnePet(fromAddr,i).send();
    		String output = send.getOutput();
    		log.info("pickApet output: "+output);
    		return i;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    	
    	return null;
    }
    //放棄一個寵物
    public void unpick(String fromAddr,String creditAddress,BigInteger i) {
    	try {
    		TransactionReceipt send = this.load(creditAddress).unAdoptOnePet(fromAddr,i).send();
    		String output = send.getOutput();
    		log.info("pickApet output: "+output);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    }
    
    public void getBalance(String addr,String creditAddress) {
    	try {
    		RemoteCall<TransactionReceipt> map = this.load(creditAddress).getMap(addr);
    		CompletableFuture<TransactionReceipt> sendAsync = map.sendAsync();
    		TransactionReceipt transactionReceipt = sendAsync.get();
    		String output2 = transactionReceipt.getOutput();
    		log.info("output2 :{}", output2);
    		TransactionReceipt send = this.load(creditAddress).getMap(addr).send();
    		String output = send.getOutput();
    		log.info("pickApet output: "+output);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    }

}
