package com.netcracker.models.PojoBuilder;


import com.netcracker.models.Manager;
import com.netcracker.models.ManagerBill;

import java.math.BigInteger;

public class ManagerBillBuilder {
    private BigInteger managerBillId;
    private Manager manager;
    private String cardNumber;

    public ManagerBillBuilder withManagerBillId(BigInteger managerBillId)
    {
        this.managerBillId = managerBillId;
        return this;
    }
    public ManagerBillBuilder withManager(Manager manager)
    {
        this.manager = manager;
        return this;
    }
    public ManagerBillBuilder withCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
        return this;
    }

    public ManagerBill build(){
        return new ManagerBill(managerBillId,manager,cardNumber);
    };
}
