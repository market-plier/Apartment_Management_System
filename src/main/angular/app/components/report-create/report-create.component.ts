import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CommunalUtilityService} from "../../services/communal-utility.service";
import {DatePipe, Location} from "@angular/common";
import {CommunalUtility} from "../../models/communal-utility";
import {ReportService} from "../../services/report.service";
import {FormControl, FormGroup} from "@angular/forms";
import {TokenStorageService} from "../../services/token-storage.service";

@Component({
  selector: 'app-report-create',
  templateUrl: './report-create.component.html',
  styleUrls: ['./report-create.component.css']
})
export class ReportCreateComponent implements OnInit {
  utilities: CommunalUtility[];
  formGroup: FormGroup;
  currentForm: string;
  accountId: string;
  datepipe: DatePipe = new DatePipe('en-US')
  private blob: Blob;

  constructor(private route: ActivatedRoute,
              private service: CommunalUtilityService,
              private location: Location,
              private reportService: ReportService,
              private tokenStorageService: TokenStorageService
  ) {
    this.formGroup = new FormGroup({
      "communalUtility": new FormControl(),
      "end": new FormControl(),
      "start": new FormControl()
    })
    this.currentForm = '';
    this.accountId = this.getUserId();
  }

  ngOnInit(): void {
    this.getUtilities();
  }

  getUtilities(): void {
    this.service.getCommunalUtilities()
        .subscribe(utilities => this.utilities = utilities);
  }

  changeCurrentForm(form: string): void {
    this.currentForm = form;
  }

  subscribeToGetPdf(data: Blob) {
    this.blob = new Blob([data], {type: 'application/pdf'});
    var downloadURL = window.URL.createObjectURL(data);
    var link = document.createElement('a');
    link.href = downloadURL;
    link.download = "report.pdf";
    link.click();
  }

  makeManagerOperationSpendingReportByDateAndCommunalUtility(): void {


    let start = this.datepipe.transform(this.formGroup.get('start').value, 'MM/dd/yyyy');
    let end = this.datepipe.transform(this.formGroup.get('end').value, 'MM/dd/yyyy');
    this.reportService.makeManagerSpendingByDateCommName(
        start,
        end.toString(),
        this.formGroup.get('communalUtility').value
    )
        .subscribe(blob => this.subscribeToGetPdf(blob));
  }

  makeManagerOperationSpendingReportByDate(): void {
    let start = this.datepipe.transform(this.formGroup.get('start').value, 'MM/dd/yyyy');
    let end = this.datepipe.transform(this.formGroup.get('end').value, 'MM/dd/yyyy');
    this.reportService.makeManagerSpendingByDate(
        start,
        end
    )
        .subscribe(blob => this.subscribeToGetPdf(blob));
  }

  makeManagerBillDeptReport(): void {
    this.reportService.makeDeptManagerBill(this.formGroup.get('communalUtility').value)
        .subscribe(blob => this.subscribeToGetPdf(blob));
  }

  getRole(): any {
    return this.tokenStorageService.getRole();
  }

  getUserId(): any {
    return this.tokenStorageService.getAccountId();
  }

  makeApartmentDeptReport(): void {
    this.reportService.makeApartmentDebtReport(
        this.accountId,
        this.formGroup.get('communalUtility').value
    )
        .subscribe(blob => this.subscribeToGetPdf(blob));
  }

  goBack(): void {
    this.location.back();
  }
}
