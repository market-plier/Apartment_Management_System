package com.netcracker.models.PojoBuilder;

import com.netcracker.models.*;

import java.math.BigInteger;
import java.util.List;

public class ApartmentSubBillBuilder {
    private BigInteger subBillId;
    private Double balance;
    private Double debt;
    private List<ApartmentOperation> apartmentOperations;
    private List<DebtPaymentOperation> debtPaymentOperations;
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

    public ApartmentSubBillBuilder withApartmentOperation(List<ApartmentOperation> apartmentOperations) {
        this.apartmentOperations = apartmentOperations;
        return this;
    }

    public ApartmentSubBillBuilder withDebtPaymentOperation(List<DebtPaymentOperation> debtPaymentOperations) {
        this.debtPaymentOperations = debtPaymentOperations;
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
        return new ApartmentSubBill(subBillId, balance, debt, apartmentOperations, debtPaymentOperations,communalUtility,apartment);
    }
}


