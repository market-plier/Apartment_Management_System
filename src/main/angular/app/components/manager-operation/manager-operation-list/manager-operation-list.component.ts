import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Subscription} from "rxjs";
import {ManagerOperation} from "../../../models/manager-operation";
import {ManagerOperationService} from "../../../services/manager-operation.service";;
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {ManagerSubBill} from "../../../models/manager-sub-bill";


@Component({
  selector: 'app-manager-operation-list',
  templateUrl: './manager-operation-list.component.html',
  styleUrls: ['./manager-operation-list.component.css'],
})
export class ManagerOperationListComponent implements OnInit {

  operations: ManagerOperation[] = []
  oSub: Subscription
  hidden: boolean = false;
  id:number;
  form: FormGroup;
  subBills: ManagerSubBill[] = []
  pSub: Subscription
  constructor(private managerService: ManagerOperationService) {
  }

  ngOnInit(): void {
    this.range = new FormGroup({
     start: new FormControl('',Validators.required),
      end: new FormControl('',Validators.required)
    })

    this.form = new FormGroup({
      sum:new FormControl('',[Validators.required]),
      description:new FormControl('',[Validators.required,Validators.minLength(2)]),
      operationId: new FormControl('')
    })

    this.pSub = this.managerService.getAllManagerSubBill().subscribe(subBills => {
      this.subBills = subBills
    })

    }

  dateRangeStart: HTMLInputElement;
  dateRangeEnd: HTMLInputElement;
  dataSource = new MatTableDataSource<ManagerOperation>(this.operations);



  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });


  dateRangeChange(dateRangeStart: HTMLInputElement, dateRangeEnd: HTMLInputElement) {
    this.dateRangeStart = dateRangeStart;
    this.dateRangeEnd = dateRangeEnd;
    if (!this.range.invalid) {
      this.oSub = this.managerService.getAllByDate(this.dateRangeStart.value, this.dateRangeEnd.value).subscribe(operations => {
        this.operations = operations
        this.dataSource = new MatTableDataSource<ManagerOperation>(this.operations);
        this.dataSource.paginator = this.paginator;
      })
    }
  }


  displayedColumns: string[] = ['position', 'description', 'sum', 'communalUtility', 'createdAt', 'operation'];


  @ViewChild(MatPaginator) paginator: MatPaginator;





  update(operationId:number)
  {
     this.form.reset();
      this.id = operationId;
      this.hidden = true;
  }

  isHidden(operationId:number): boolean {
    return operationId == this.id && this.hidden == true;
  }

  cancel()
  {
    this.id = 0;
    this.hidden = false;
    this.form.reset();
  }

  onSubmit():void {
    this.form.controls['operationId'].setValue(this.id);
    this.managerService.updateSpending(this.form.value)
    this.cancel()
    this.dateRangeChange(this.dateRangeStart, this.dateRangeEnd);
  }

  filterManagerOperation()
  {

  }






}
