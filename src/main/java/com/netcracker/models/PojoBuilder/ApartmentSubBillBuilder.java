package com.netcracker.models.PojoBuilder;

import com.netcracker.models.*;

import java.math.BigInteger;
import java.util.List;

public class ApartmentSubBillBuilder {
    private BigInteger subBillId;
    private Double balance;
    private Double debt;
    private List<ApartmentOperation> apartmentOperation;
    private List<DebtPaymentOperation> debtPaymentOperation;
    private CommunalUtility communalUtility;
    private Apartment apartment;


    public ApartmentSubBillBuilder withSubBillId(BigInteger subBillId) {
        this.subBillId = subBillId;
        return this;
    }

    public ApartmentSubBillBuilder withBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public ApartmentSubBillBuilder withDept(Double debt) {
        this.debt = debt;
        return this;
    }

    public ApartmentSubBillBuilder withApartmentOperation(List<ApartmentOperation> apartmentOperation) {
        this.apartmentOperation = apartmentOperation;
        return this;
    }

    public ApartmentSubBillBuilder withDebtPaymentOperation(List<DebtPaymentOperation> debtPaymentOperation) {
        this.debtPaymentOperation = debtPaymentOperation;
        return this;
    }
    public ApartmentSubBillBuilder withCommunalUtility(CommunalUtility communalUtility)
    {
        this.communalUtility = communalUtility;
        return this;
    }

    public ApartmentSubBillBuilder withApartment(Apartment apartment)
    {
        this.apartment=apartment;
        return this;
    }
    public ApartmentSubBill build() {
        return new ApartmentSubBill(subBillId, balance, debt, apartmentOperation, debtPaymentOperation,communalUtility,apartment);
    }
}


