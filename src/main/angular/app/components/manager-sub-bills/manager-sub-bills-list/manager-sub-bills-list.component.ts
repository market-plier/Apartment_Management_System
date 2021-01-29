import {Component, OnInit} from '@angular/core';
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {Router} from "@angular/router";
import {ManagerSubBillService} from "../../../services/manager-sub-bill.service";
import {MatTableDataSource} from "@angular/material/table";
import {TokenStorageService} from "../../../services/token-storage.service";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
    selector: 'app-manager-sub-bills-list',
    templateUrl: './manager-sub-bills-list.component.html',
    styleUrls: ['./manager-sub-bills-list.component.scss']
})


export class ManagerSubBillListComponent implements OnInit {

    managerSubBills?: ManagerSubBill[] = [];
    status?: string[];
    datasource: MatTableDataSource<ManagerSubBill>

    constructor(private managerSubBillService: ManagerSubBillService,
                private tokenStorageService: TokenStorageService,
                private router: Router,
                private _snackBar: MatSnackBar) {
    }

    ngOnInit(): void {
        this.getAllManagerSubBills();
    }

    getAllManagerSubBills() {
        this.managerSubBillService.getAllManagerSubBills()
            .subscribe(data => {
                    console.log(data)
                    this.managerSubBills = data;
                    this.datasource = new MatTableDataSource(data)
                },
                error => {
                    console.log(error)
                },
            );
    }

    redirectToManagerSubBillShow(id: number): void {
        this.router.navigate([`/manager-sub-bill/${id}`])
    }

    getRole(): any {
        return this.tokenStorageService.getRole();
    }


    getEnabled() {
        this.managerSubBillService.getAllManagerSubBillsByStatus(1)
            .subscribe(
                data => {
                    this.managerSubBills = data;
                },
                error => {
                    this.openSnackBar("No enabled sub bills", "Select status");
                    console.log(error)
                }
            );
    }

    getDisabled() {
        this.managerSubBillService.getAllManagerSubBillsByStatus(2)
            .subscribe(data => {
                    console.log(data)
                    this.managerSubBills = data;
                },
                error => {
                    this.openSnackBar("No disabled sub bills", "Select status");
                    console.log(error)
                },
            );
    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }
}
