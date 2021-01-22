import {Component, OnInit, ViewChild} from '@angular/core';
import {ApartmentOperationService} from "../../services/apartment-operation.service";
import {ApartmentInfoService} from "../../services/apartment-info.service";
import {Apartment} from "../../models/apartment";
import {FormControl} from "@angular/forms";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {ApartmentOperation} from "../../models/apartment-operation";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-apartment-operation-list',
  templateUrl: './apartment-operation-list.component.html',
  styleUrls: ['./apartment-operation-list.component.scss']
})
export class ApartmentOperationListComponent implements OnInit {

  constructor(private apartmentOperation:ApartmentOperationService,private _snackBar: MatSnackBar, private apartmentService: ApartmentInfoService) { }

    apartments?: Apartment[] = [];
    searchValue?: Number;
    floors?;
    myControl = new FormControl();
    options: Number[] = [];
    filteredOptions: Observable<Number[]>;
    dataSource = new MatTableDataSource<ApartmentOperation>();

    displayedColumns: string[] = ['operation id', 'sum', 'communalUtility','createdAt',];

    @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.apartmentService.getAllApartments().subscribe(
        data => {
          console.log(data);

        }
    )

    this.getAllApartments();
      this.filteredOptions = this.myControl.valueChanges
          .pipe(
              startWith(''),
              map(value => this._filter(value))
          );
  }


    private _filter(value: Number): Number[] {
        if (this.options != null && value != null) {
            return this.options.filter(option => option.toString().includes(value.toString()));
        }
    }

    getAllApartments() {
        this.apartmentService.getAllApartments().subscribe(
            data => {
                this.apartments = data;
                this.floors = this.uniqueArray(this.apartments.map(item => item.floor));
                this.options = this.apartments.map(item => item.apartmentNumber);
            },
        );
    }


    paginateHide()
    {
        return this.apartments.length>0
    }

    uniqueArray(ar) {
        var j = {};

        ar.forEach(function (v) {
            j[v + '::' + typeof v] = v;
        });

        return Object.keys(j).map(function (v) {
            return j[v];
        });
    }



    getAllOperations(id:Number)
  {
    this.apartmentOperation.getAllByAccountId(id).subscribe(
        data => {
          console.log(data);
            this.dataSource.data = data;
            this.dataSource.paginator = this.paginator;
        },
        error => {
          console.log(error.error.errors[0]);
          this.openErrorSnackBar(error.error.errors[0],"OK")
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
