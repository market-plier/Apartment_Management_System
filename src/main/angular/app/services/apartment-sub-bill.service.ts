import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApartmentSubBill} from "../models/apartment-sub-bill";
import {ApartmentOperation} from "../models/apartment-operation";

@Injectable({
    providedIn: 'root'
})
export class ApartmentSubBillService{
    private baseUrl = "http://localhost:8888/apartment-sub-bills";
    private transferUrl = "http://localhost:8888/apartment-sub-bill-transfer";
    private paymentUrl = "http://localhost:8888/apartment-sub-bill-payment";

    constructor(private http: HttpClient) {
    }

    getApartmentSubBillList(): Observable<ApartmentSubBill[]>{
        return this.http.get<ApartmentSubBill[]>(this.baseUrl);
    }

    getApartmentSubBill(id: number): Observable<ApartmentSubBill>{
        return this.http.get<ApartmentSubBill>(`${this.baseUrl}/${id}`);
    }

    createApartmentSubBillTransfer(params: string[]): Observable<any>{
        return this.http.post(this.transferUrl, params);
    }

    updateApartmentSubBillsByApartmentOperation(apartmentOperations: ApartmentOperation[]): Observable<any>{
        return this.http.post(this.paymentUrl, apartmentOperations);
    }
}