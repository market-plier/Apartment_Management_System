import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CommunalUtilityService} from "../../services/communal-utility.service";
import {DatePipe, Location} from "@angular/common";
import {CommunalUtility} from "../../models/communal-utility";
import {ReportService} from "../../services/report.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TokenStorageService} from "../../services/token-storage.service";
import {Apartment} from "../../models/apartment";
import {ApartmentInfoService} from "../../services/apartment-info.service";

@Component({
  selector: 'app-report-create',
  templateUrl: './report-create.component.html',
  styleUrls: ['./report-create.component.scss']
})
export class ReportCreateComponent implements OnInit {
  utilities: CommunalUtility[];
  formGroup1: FormGroup;
  formGroup2: FormGroup;
  formGroup3: FormGroup;
  formGroup4: FormGroup;
  formGroup5: FormGroup;

  currentForm: string;
  accountId: string;
  apartments: Apartment[];
  datepipe: DatePipe = new DatePipe('en-US')
  private blob: Blob;

  constructor(private route: ActivatedRoute,
              private service: CommunalUtilityService,
              private location: Location,
              private reportService: ReportService,
              private tokenStorageService: TokenStorageService,
              private apartmentInfoService: ApartmentInfoService
  ) {
    this.formGroup1 = new FormGroup({
      "communalUtility": new FormControl('', Validators.required),
      "end": new FormControl('', Validators.required),
      "start": new FormControl('', Validators.required)
    })
    this.formGroup2 = new FormGroup({
      "end": new FormControl('', Validators.required),
      "start": new FormControl('', Validators.required)
    })
    this.formGroup3 = new FormGroup({
      "communalUtility": new FormControl('', Validators.required)
    })
    this.formGroup4 = new FormGroup({
      "communalUtility": new FormControl('', Validators.required)
    })
    this.formGroup5 = new FormGroup({
      "communalUtility": new FormControl('', Validators.required),
      "apartment": new FormControl('', Validators.required)
    })
    this.currentForm = '';
    this.accountId = this.getUserId();
  }

  ngOnInit(): void {
    this.getUtilities();
    if (this.getRole() !== 'OWNER')
      this.getApartments();
  }

  getApartments(): void {
    this.apartmentInfoService.getAllApartments().subscribe(apartments => this.apartments = apartments)
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
    let start = this.datepipe.transform(this.formGroup1.get('start').value, 'MM/dd/yyyy');
    let end = this.datepipe.transform(this.formGroup1.get('end').value, 'MM/dd/yyyy');
    this.reportService.makeManagerSpendingByDateCommName(
        start,
        end.toString(),
        this.formGroup1.get('communalUtility').value
    )
        .subscribe(blob => this.subscribeToGetPdf(blob),
            error => {
              console.log(error)
            });
  }

  makeManagerOperationSpendingReportByDate(): void {
    let start = this.datepipe.transform(this.formGroup2.get('start').value, 'MM/dd/yyyy');
    let end = this.datepipe.transform(this.formGroup2.get('end').value, 'MM/dd/yyyy');
    this.reportService.makeManagerSpendingByDate(
        start,
        end
    )
        .subscribe(blob => this.subscribeToGetPdf(blob),
            error => {
              console.log(error)
            });
  }

  makeManagerBillDeptReport(): void {
    this.reportService.makeDeptManagerBill(this.formGroup3.get('communalUtility').value)
        .subscribe(blob => this.subscribeToGetPdf(blob),
            error => {
              console.log(error)
            });
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
        this.formGroup4.get('communalUtility').value
    )
        .subscribe(blob => this.subscribeToGetPdf(blob),
            error => {
              console.log(error)
            });
  }

  goBack(): void {
    this.location.back();
  }

  makeManagerApartmentDeptReport() {
    this.reportService.makeApartmentDebtReport(
        this.formGroup5.get('apartment').value,
        this.formGroup5.get('communalUtility').value
    )
        .subscribe(blob => this.subscribeToGetPdf(blob),
            error => {
              console.log(error)
            });
  }
}
