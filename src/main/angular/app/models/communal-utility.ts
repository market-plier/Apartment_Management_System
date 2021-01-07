import {CalculationMethod} from "./calculation-method";

export interface CommunalUtility {
    communalUtilityId?: number;
    name?: string;
    deadline?: Date;
    calculationMethod?: CalculationMethod;
    status?: string;
    durationType?: string;
}
