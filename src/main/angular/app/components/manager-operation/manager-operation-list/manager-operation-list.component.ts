import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Subject, Subscription} from "rxjs";
import {ManagerOperation} from "../../../models/manager-operation";
import {ManagerOperationService} from "../../../services/manager-operation.service";;
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {CommunalUtility} from "../../../models/communal-utility";


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
  utility:FormGroup;


  communals: CommunalUtility[] = []
  pSub: Subscription
  constructor(private managerService: ManagerOperationService) {
  }


  ngOnInit(): void {


    this.dataSource = new MatTableDataSource<ManagerOperation>(this.operations);

    this.range = new FormGroup({
     start: new FormControl('',Validators.required),
      end: new FormControl('',Validators.required)
    })

    this.form = new FormGroup({
      sum:new FormControl('',[Validators.required]),
      description:new FormControl('',[Validators.required,Validators.minLength(2)]),
      operationId: new FormControl('')
    })

    this.utility = new FormGroup({
      communalUtilityId: new FormControl('',[Validators.required])
    })



    this.pSub = this.managerService.getAllCommunalUtility().subscribe(communal => {
      this.communals = communal
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
    if (this.range.valid && this.utility.invalid) {
      this.oSub = this.managerService.getAllByDate(this.dateRangeStart.value, this.dateRangeEnd.value).subscribe(operations => {
        this.dataSource.data = operations;
        this.dataSource.paginator = this.paginator;
        console.log(this.dataSource)
      })
    }

    this.filterManagerOperation()
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
    if (this.utility.invalid && this.range.valid)
    {
      this.dateRangeChange(this.dateRangeStart, this.dateRangeEnd);
    }

    this.filterManagerOperation()
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

      this.oSub = this.managerService.filterByDateAndCommunalUtility(this.utility.value, this.dateRangeStart.value, this.dateRangeEnd.value).subscribe(operations => {
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
