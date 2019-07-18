pragma solidity ^0.4.24;

/**
haiyangxinxin

寵物合約 PetContract :
1, 使用地址類型的數組來保存寵物持有關係, 
    數組下標表示寵物的id, 對應的值表示持有人的地址(使用公鑰獲取到的地址)
2, 這個合約處理以下事情, a: 領養寵物的時候, 新增記錄
                        b: 返回所有領養寵物的關係
                        c: 放棄領養
 */
contract PetContract {

    // 寵物id 和 領養者的關係
    address[16] public pets;

    //地址對應餘額
    mapping(address => uint256) public balances;

    // 用来通知客户端积分交易发生
    event transferEvent(address from, address to, uint256 value);

    // 寵物的領養金額
    uint256 public petAmt;

    // 每個用戶初始化的金額
    uint256 public personnalAmt;

    //管理員地址, 用於中轉等
    address public adminAddr;

    //構造函數
    constructor(uint256 amt, uint256 pAmt) public{
        petAmt = amt;
        personnalAmt = pAmt;
        adminAddr = 0x015e68c28690b3250b36319d7c04653e00000033;
    }

    //領養一個寵物, 
    function adoptOnePet(address adoptAddress,uint256 _petId) public returns (uint256){
        require(_petId>=0 && _petId<=15);//數組不能超

        require(pets[_petId] == address(0));// 不能重複領養
        _initPersonnalAmt(adoptAddress);
        pets[_petId] = adoptAddress;
        _transfer(adoptAddress, adminAddr, petAmt);
        return _petId;
    }

    function _initPersonnalAmt(address perAddr) internal{
        if(balances[perAddr] <= 0){
            balances[perAddr] = personnalAmt;
        }
    }

    //放棄領養一個寵物, 改為重新上架狀態, 其他人可以領養
    function unAdoptOnePet(address adoptAddress,uint256 _petId) public returns (uint256){
        require(_petId>=0 && _petId<=15);//數組不能超

        require(pets[_petId] == adoptAddress);// 判斷是否是領養者

        pets[_petId] = address(0);
        _transfer(adminAddr, adoptAddress, petAmt);
        return _petId;
    }

    //返回所有寵物
    function getAll() public returns (address[16]){
        return pets;
    }

    //返回錢包
    function getMap(address owner) public returns (uint256){
        return balances[owner];
    }

    // 金額的发送函数，内部函数
    function _transfer(address _from, address _to, uint256 _value) internal {

        require(_to != 0x0); 
        require(balances[_from] >= _value); 
        require(balances[_to] + _value > balances[_to]); //_value不能为负值

        uint256 previousBalances = balances[_from] + balances[_to]; 

        balances[_from] -= _value; 
        balances[_to] += _value;

        transferEvent(_from, _to, _value);   // 记录转账并通知客户端发生积分交易
        assert(balances[_from] + balances[_to] == previousBalances);  
    }

    // 客户端调用的金額发送函数
    /* function transfer(address _to, uint256 _value) public {
        _transfer(msg.sender, _to, _value); 
    } */

}