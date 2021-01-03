import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {Subscription} from "rxjs";
import {ManagerOperation} from "../../../models/manager-operation";
import {ManagerOperationService} from "../../../services/manager-operation.service";;
import {FormControl, FormGroup} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";


@Component({
  selector: 'app-manager-operation-list',
  templateUrl: './manager-operation-list.component.html',
  styleUrls: ['./manager-operation-list.component.css'],
})
export class ManagerOperationListComponent {

  operations: ManagerOperation[] = []
  oSub: Subscription

  constructor(private managerService: ManagerOperationService) {
  }


  dataSource = new MatTableDataSource<ManagerOperation>(this.operations);



  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });


  dateRangeChange(dateRangeStart: HTMLInputElement, dateRangeEnd: HTMLInputElement) {
    console.log(dateRangeStart.value);
    console.log(dateRangeEnd.value);
    this.oSub = this.managerService.getAllByDate(dateRangeStart.value, dateRangeEnd.value).subscribe(operations => {
      this.operations = operations
      this.dataSource = new MatTableDataSource<ManagerOperation>(this.operations);
      this.dataSource.paginator = this.paginator;
      console.log(operations)
    })
  }


  displayedColumns: string[] = ['position', 'description', 'sum', 'createdAt', 'operation'];


  @ViewChild(MatPaginator) paginator: MatPaginator;


  remove(operationId:number)
  {
    console.log(operationId)
  }

  update(operationId:number)
  {

  }


}
