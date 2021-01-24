import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApartmentSubBill} from "../models/apartment-sub-bill";
import {ApartmentOperation} from "../models/apartment-operation";
import {environment} from "../../environments/environment.prod";

@Injectable({
    providedIn: 'root'
})
export class ApartmentSubBillService{
    private url = environment.url;
    private baseUrl = this.url + "apartment-sub-bills";
    private transferUrl = this.url + "apartment-sub-bill-transfer";
    private paymentUrl = this.url +"apartment-sub-bill-payment";

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