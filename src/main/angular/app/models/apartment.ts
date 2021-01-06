import {Account} from "./account";

export class Apartment extends Account {
    apartmentNumber?: number;
    squareMetres?: number;
    floor?:number;
    peopleCount?: number;
}
