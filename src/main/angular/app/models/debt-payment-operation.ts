import {ApartmentSubBill} from "./apartment-sub-bill";
import {ManagerSubBill} from "./manager-sub-bill";

export class DebtPaymentOperation{
    operationId?: number;
    sum?: number;
    createdAt?: Date;
    apartmentSubBill?: ApartmentSubBill;
    managerSubBill?: ManagerSubBill;
}