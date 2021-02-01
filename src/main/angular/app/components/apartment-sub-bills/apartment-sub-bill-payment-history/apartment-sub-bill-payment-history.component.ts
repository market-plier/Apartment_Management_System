import {Component, OnInit, ViewChild} from '@angular/core';
import {Observable} from "rxjs";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ApartmentOperationService} from "../../../services/apartment-operation.service";
import {Apartment} from "../../../models/apartment";
import {ApartmentOperation} from "../../../models/apartment-operation";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-apartment-sub-bill-payment-history',
    templateUrl: './apartment-sub-bill-payment-history.component.html',
    styleUrls: ['./apartment-sub-bill-payment-history.component.scss']
})
export class ApartmentSubBillPaymentHistoryComponent implements OnInit {

    constructor(private apartmentOperationService:ApartmentOperationService,private _snackBar: MatSnackBar,
                private route: ActivatedRoute) { }

    apartments?: Apartment[] = [];
    searchValue?: Number;
    options: Number[] = [];
    filteredOptions: Observable<Number[]>;
    dataSource = new MatTableDataSource<ApartmentOperation>();

    displayedColumns: string[] = ['sum', 'createdAt'];

    @ViewChild(MatPaginator) paginator: MatPaginator;

    ngOnInit(): void {
        this.getAllOperationsByApartmentSubBillId(this.route.snapshot.params.id);
    }


    paginateHide()
    {
        return this.apartments.length>0;
    }


    getAllOperationsByApartmentSubBillId(id:number)
    {
        this.apartmentOperationService.getApartmentOperationsByApartmentSubBill(id).subscribe(
            data => {
                console.log(data);
                this.dataSource.data = data;
                this.dataSource.paginator = this.paginator;
            },
            error => {
                console.log(error);
                this.openErrorSnackBar("Error getting operation history",'')
            }
        )
    }

    openErrorSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            panelClass:['snackBar-fail'],
            duration: 100000,
        });
    }


}
