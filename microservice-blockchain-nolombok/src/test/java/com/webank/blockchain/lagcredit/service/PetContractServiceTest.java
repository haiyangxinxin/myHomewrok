package com.webank.blockchain.lagcredit.service;

import java.math.BigInteger;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.webank.blockchain.lagcredit.BaseTest;
import com.webank.blockchain.lagcredit.contracts.PetContract;

/**
 * PetContractServiceTest
 *
 * @Description: PetContractServiceTest
 * @author hyxx
 * @data 2019-07-18 15:44:27
 *
 */
//@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetContractServiceTest extends BaseTest {

	@Autowired
	private PetContractService petContractService;

//	private String owner = "0xb1f49311c909bb22fdaf366771a15a983e8c0148";
	
	private String owner = "0xb1f49311c909bb22fdaf366771a15a983e81022";

	private String creditAddr = "0xd6279ceba69d536e2ea87e8028db2a26b5bd38ad";//合約地址


	private static Logger log = LoggerFactory.getLogger(PetContractServiceTest.class);


	
	
	  @Test public void testA01deploy(){ 
//		  PetContract lagCredit = petContractService.deploy(); log.info("PetContract address : {}", lagCredit);
	  }
	 
	 


	@Test 
	public void testA02PickApet() { 
		petContractService.pickApet(owner,creditAddr, new BigInteger("0")); 
	}


	@Test
	public void testA03pet() {
//		petContractService.unpick(owner,creditAddr, new BigInteger("1"));
	}
	
	@Test
	public void testA04getMap() {
//		petContractService.unpick(owner,creditAddr, new BigInteger("1"));
		petContractService.getBalance(owner, creditAddr);
	}

}
