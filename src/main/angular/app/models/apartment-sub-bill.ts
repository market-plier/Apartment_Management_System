import {CommunalUtility} from "./communal-utility";
import {Apartment} from "./apartment";
import {ApartmentOperation} from "./apartment-operation";
import {DebtPaymentOperation} from "./debt-payment-operation";

export class ApartmentSubBill{
    subBillId?: number;
    balance?: number;
    communalUtility?: CommunalUtility;
    debt?: number;
    apartment: Apartment;
    apartmentOperations?: ApartmentOperation[];
    debtPaymentOperations?: DebtPaymentOperation[];
}