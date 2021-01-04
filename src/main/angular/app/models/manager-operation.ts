import {ManagerSubBill} from "./manager-sub-bill";


export class ManagerOperation {
    operationId?: number;
    description?: String;
    createdAt: Date;
    sum:number
    managerSubBill: ManagerSubBill;

}
