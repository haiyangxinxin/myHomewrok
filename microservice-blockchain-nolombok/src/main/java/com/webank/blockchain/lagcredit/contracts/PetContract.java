package com.webank.blockchain.lagcredit.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class PetContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50604051604080610bd08339810180604052810190808051906020019092919080519060200190929190505050816011819055508060128190555073015e68c28690b3250b36319d7c04653e00000033601360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050610b1f806100b16000396000f300608060405260043610610099576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631947bd2f1461009e578063213d9084146100f557806327e235e31461015657806353ed5143146101ad57806366993bd514610200578063818305931461022b578063a3d7af4414610282578063c74cb0f8146102ad578063cfb869bf1461030e575b600080fd5b3480156100aa57600080fd5b506100df600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061037b565b6040518082815260200191505060405180910390f35b34801561010157600080fd5b50610140600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506103c4565b6040518082815260200191505060405180910390f35b34801561016257600080fd5b50610197600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506104dd565b6040518082815260200191505060405180910390f35b3480156101b957600080fd5b506101c26104f5565b6040518082601060200280838360005b838110156101ed5780820151818401526020810190506101d2565b5050505090500191505060405180910390f35b34801561020c57600080fd5b50610215610576565b6040518082815260200191505060405180910390f35b34801561023757600080fd5b5061024061057c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561028e57600080fd5b506102976105a2565b6040518082815260200191505060405180910390f35b3480156102b957600080fd5b506102f8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506105a8565b6040518082815260200191505060405180910390f35b34801561031a57600080fd5b50610339600480360381019080803590602001909291905050506106b7565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6000601060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60008082101580156103d75750600f8211155b15156103e257600080fd5b600073ffffffffffffffffffffffffffffffffffffffff1660008360108110151561040957fe5b0160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561044d57600080fd5b610456836106ec565b8260008360108110151561046657fe5b0160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506104d483601360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660115461077f565b81905092915050565b60106020528060005260406000206000915090505481565b6104fd610acf565b600060108060200260405190810160405280929190826010801561056c576020028201915b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610522575b5050505050905090565b60115481565b601360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60125481565b60008082101580156105bb5750600f8211155b15156105c657600080fd5b8273ffffffffffffffffffffffffffffffffffffffff166000836010811015156105ec57fe5b0160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561063057600080fd5b6000808360108110151561064057fe5b0160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506106ae601360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168460115461077f565b81905092915050565b6000816010811015156106c657fe5b016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000601060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205411151561077c57601254601060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505b50565b6000808373ffffffffffffffffffffffffffffffffffffffff16141515156107a657600080fd5b81601060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054101515156107f457600080fd5b601060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205482601060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020540111151561088257600080fd5b601060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054601060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205401905081601060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254039250508190555081601060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055507f5d439cf3a1f6215b41908e5b0b300f39679a3ff1cc010691c1d4ec4d0e2a4ebb848484604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390a180601060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054601060008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205401141515610ac957fe5b50505050565b610200604051908101604052806010906020820280388339808201915050905050905600a165627a7a7230582060c2b216e06a365b74217796fbfe1d24d5c14a9edf7c0101bfb1f85df82e59dd0029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"getMap\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"adoptAddress\",\"type\":\"address\"},{\"name\":\"_petId\",\"type\":\"uint256\"}],\"name\":\"adoptOnePet\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"balances\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"getAll\",\"outputs\":[{\"name\":\"\",\"type\":\"address[16]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"petAmt\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"adminAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"personnalAmt\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"adoptAddress\",\"type\":\"address\"},{\"name\":\"_petId\",\"type\":\"uint256\"}],\"name\":\"unAdoptOnePet\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"pets\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"amt\",\"type\":\"uint256\"},{\"name\":\"pAmt\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"transferEvent\",\"type\":\"event\"}]";

    public static final String FUNC_GETMAP = "getMap";

    public static final String FUNC_ADOPTONEPET = "adoptOnePet";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_GETALL = "getAll";

    public static final String FUNC_PETAMT = "petAmt";

    public static final String FUNC_ADMINADDR = "adminAddr";

    public static final String FUNC_PERSONNALAMT = "personnalAmt";

    public static final String FUNC_UNADOPTONEPET = "unAdoptOnePet";

    public static final String FUNC_PETS = "pets";

    public static final Event TRANSFEREVENT_EVENT = new Event("transferEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected PetContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PetContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PetContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PetContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> getMap(String owner) {
        final Function function = new Function(
                FUNC_GETMAP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getMap(String owner, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETMAP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(owner)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getMapSeq(String owner) {
        final Function function = new Function(
                FUNC_GETMAP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(owner)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> adoptOnePet(String adoptAddress, BigInteger _petId) {
        final Function function = new Function(
                FUNC_ADOPTONEPET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(adoptAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_petId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void adoptOnePet(String adoptAddress, BigInteger _petId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADOPTONEPET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(adoptAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_petId)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String adoptOnePetSeq(String adoptAddress, BigInteger _petId) {
        final Function function = new Function(
                FUNC_ADOPTONEPET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(adoptAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_petId)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> balances(String param0) {
        final Function function = new Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> getAll() {
        final Function function = new Function(
                FUNC_GETALL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getAll(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETALL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getAllSeq() {
        final Function function = new Function(
                FUNC_GETALL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> petAmt() {
        final Function function = new Function(FUNC_PETAMT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> adminAddr() {
        final Function function = new Function(FUNC_ADMINADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> personnalAmt() {
        final Function function = new Function(FUNC_PERSONNALAMT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> unAdoptOnePet(String adoptAddress, BigInteger _petId) {
        final Function function = new Function(
                FUNC_UNADOPTONEPET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(adoptAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_petId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void unAdoptOnePet(String adoptAddress, BigInteger _petId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_UNADOPTONEPET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(adoptAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_petId)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String unAdoptOnePetSeq(String adoptAddress, BigInteger _petId) {
        final Function function = new Function(
                FUNC_UNADOPTONEPET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(adoptAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_petId)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> pets(BigInteger param0) {
        final Function function = new Function(FUNC_PETS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public List<TransferEventEventResponse> getTransferEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEREVENT_EVENT, transactionReceipt);
        ArrayList<TransferEventEventResponse> responses = new ArrayList<TransferEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventEventResponse typedResponse = new TransferEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventEventResponse> transferEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventEventResponse>() {
            @Override
            public TransferEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFEREVENT_EVENT, log);
                TransferEventEventResponse typedResponse = new TransferEventEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventEventResponse> transferEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFEREVENT_EVENT));
        return transferEventEventFlowable(filter);
    }

    @Deprecated
    public static PetContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PetContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PetContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PetContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PetContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PetContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PetContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PetContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PetContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger amt, BigInteger pAmt) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amt), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(pAmt)));
        return deployRemoteCall(PetContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PetContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger amt, BigInteger pAmt) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amt), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(pAmt)));
        return deployRemoteCall(PetContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PetContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger amt, BigInteger pAmt) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amt), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(pAmt)));
        return deployRemoteCall(PetContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PetContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger amt, BigInteger pAmt) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amt), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(pAmt)));
        return deployRemoteCall(PetContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class TransferEventEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger value;
    }
}
