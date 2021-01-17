import {CommunalUtility} from "./communal-utility";
import {ManagerOperation} from "./manager-operation";
import {Manager} from "./manager";
import {DebtPaymentOperation} from "./debt-payment-operation";

export class ManagerSubBill {
    subBillId?: number;
    balance?: number;
    communalUtility?: CommunalUtility;
    manager: Manager;
    managerSpendingOperation: ManagerOperation[];
    debtPaymentOperations?: DebtPaymentOperation[];
}
