import { Component, OnInit, ViewChild} from '@angular/core';
import {Subscription} from "rxjs";
import {ManagerOperation} from "../../../models/manager-operation";
import {ManagerOperationService} from "../../../services/manager-operation.service";;
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {CommunalUtility} from "../../../models/communal-utility";
import {DatePipe} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {ManagerOperationCreateComponent} from "../manager-operation-create/manager-operation-create.component";



@Component({
  selector: 'app-manager-operation-list',
  templateUrl: './manager-operation-list.component.html',
  styleUrls: ['./manager-operation-list.component.scss'],
})
export class ManagerOperationListComponent implements OnInit {

  operations: ManagerOperation[] = []
  oSub: Subscription
  hidden: boolean = false;
  id:number;
  managerSubBillId:number;
  form: FormGroup;
  utility:FormGroup;
  displayedColumns: string[] = ['position', 'description', 'sum', 'communalUtility', 'balance','createdAt', 'operation'];
  dateStart: String;
  dateEnd: String;
  dataSource = new MatTableDataSource<ManagerOperation>(this.operations);
  communals: CommunalUtility[] = []
  pSub: Subscription
  description: String;
  sum: number;
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private managerService: ManagerOperationService,private datePipe: DatePipe,private _snackBar: MatSnackBar,private dialog: MatDialog) {
  }



  ngOnInit(): void {


    this.dataSource = new MatTableDataSource<ManagerOperation>(this.operations);


    this.form = new FormGroup({
      sum:new FormControl('',[Validators.required]),
      description:new FormControl('',[Validators.required,Validators.minLength(2), Validators.maxLength(1000)]),
      operationId: new FormControl(''),
      managerSubBillId: new FormControl('')
    })

    this.utility = new FormGroup({
      communalUtilityId: new FormControl('',[Validators.required])
    })


    this.pSub = this.managerService.getAllCommunalUtility().subscribe(communal => {
      this.communals = communal
    })

    this.dateEnd =  this.datePipe.transform(Date.now()+ ( 3600 * 1000 * 24),'MM/dd/yyyy');
    this.dateStart =  this.datePipe.transform(Date.now() - ( 3600 * 1000 * 24),'MM/dd/yyyy');


    this.geDataByDateRange(this.dateStart, this.dateEnd)
    }






  dateRangeChange(dateRangeStart, dateRangeEnd) {
    this.dateStart = dateRangeStart;
    this.dateEnd = dateRangeEnd;
    if (this.utility.invalid && (this.range.valid || (this.dateStart !=null && this.dateEnd !=null))) {
      this.geDataByDateRange(this.dateStart, this.dateEnd)
    }
    this.filterManagerOperation()
  }


  openCreateSpending()
  {
   this.dialog.open(ManagerOperationCreateComponent);
  }

    geDataByDateRange(start, end)
  {
    console.log(start)
    this.oSub = this.managerService.getAllByDate(start, end).subscribe(operations => {
      this.dataSource.data = operations;
      console.log(operations)
      this.dataSource.paginator = this.paginator;
    },
    error => {
      console.log(error);
    }
    )
  }

  updateButton(operationId:number, managerSubBill:number)
  {
      this.managerSubBillId = managerSubBill;
      this.id = operationId;
      this.hidden = true;
  }

  setSum(sum)
  {
    this.sum = sum;
    this.form.get('sum').setValue(sum);

  }
  setDescription(description)
  {
    this.description = description;
    this.form.get('description').setValue(description);
  }



  isHidden(operationId:number): boolean {
    return operationId == this.id && this.hidden == true;
  }

  cancel()
  {
    this.id = 0;
    this.hidden = false;

  }

  onSubmit():void {
    this.form.controls['operationId'].setValue(this.id);
    this.form.controls['managerSubBillId'].setValue(this.managerSubBillId);

    console.log(this.form.value)
    console.log(this.range.valid)
    console.log(this.utility.invalid && (this.range.valid || (this.dateStart !=null && this.dateEnd !=null)))
    if (this.description!=this.form.get('description').value || this.sum != this.form.get('sum').value)
    {
      this.managerService.updateSpending(this.form.value).subscribe(
          res => {
            this.dateRangeChange(this.dateStart, this.dateEnd);
          },
          error => {
            if (error.error.errorCode===8092)
            {
              this.openErrorSnackBar('Insufficient funds on the balance sheet, cant update', 'OK')
            }else if (error.error.errorCode===143)
            {
              this.openErrorSnackBar('Not Found Sub Bill', 'OK')
            }
            else
            {
              this.openErrorSnackBar('Cant update spending!', 'OK')
            }
          }

      )
      this.cancel()
    }
  }


  openErrorSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      panelClass:['snackBar-fail'],
      duration: 100000,
    });
  }




  filterManagerOperation()
  {

    if (this.range.invalid && this.utility.valid)
    {
      this.oSub = this.managerService.filterByCommunalUtility(this.utility.value).subscribe(operations => {
        console.log(operations)
        this.operations = operations
        this.dataSource = new MatTableDataSource<ManagerOperation>(this.operations);
        this.dataSource.paginator = this.paginator;
      })
    }
    if (this.range.valid && this.utility.valid) {
      console.log(this.utility.value)
      console.log(this.range.value)

      this.oSub = this.managerService.filterByDateAndCommunalUtility(this.utility.value, this.dateStart, this.dateEnd).subscribe(operations => {
        console.log(operations)
        this.operations = operations
        this.dataSource = new MatTableDataSource<ManagerOperation>(this.operations);
        this.dataSource.paginator = this.paginator;
      })
    }
  }


  paginateHide()
  {
    return this.dataSource.data.length>0
  }




}
