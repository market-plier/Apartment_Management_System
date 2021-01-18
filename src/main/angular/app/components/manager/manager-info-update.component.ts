import {Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {TokenStorageService} from "../../services/token-storage.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Manager} from "../../models/manager";
import {ManagerService} from "../../services/manager.service";
import {ManagerBill} from "../../models/manager-bill";

@Component({
  selector: 'app-manager-info-update',
  templateUrl: './manager-info-update.component.html',
  styleUrls: ['./manager-info-update.component.scss']
})
export class ManagerInfoUpdateComponent implements OnInit {
  constructor(private service: ManagerService, private route: ActivatedRoute, private router: Router,
              private _snackBar: MatSnackBar, public tokenStorage: TokenStorageService) {
  }

  manager: Manager = {
    managerBill: new ManagerBill(),
    accountId: 0,
    firstName: '',
    lastName: '',
    email: '',
    password: null,
    phoneNumber: '',
    role: 'OWNER'
  };
  managerToSave?: Manager;
  hide = true;

  firstFormGroup: FormGroup = new FormBuilder().group({
    'emailCtrl': ['', [Validators.required, Validators.email]],
    'firstNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
    'lastNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
    'phoneNumberCtrl': ['', [Validators.required]],
    'passwordCtrl': ['', [Validators.minLength(8), Validators.maxLength(256)]],
    'cardNumberCtrl': ['', [Validators.minLength(16), Validators.maxLength(16)]],
  });

  public phoneMask = ['+', /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/];
  public cardMask = [/\d/, /\d/, /\d/, /\d/,'-',/\d/, /\d/, /\d/, /\d/,'-',/\d/, /\d/, /\d/, /\d/,'-',/\d/, /\d/, /\d/, /\d/];


  updateManager() {
    this.managerToSave = Object.assign({}, this.manager)
    this.service.updateManager(this.managerToSave).subscribe(
        data => {
          this.openSnackBar('Manager is updated', '');
          this.goToManagerInfo();
        });
    if (this.manager.password != null) {
      this.service.updateManagerPassword(this.managerToSave);
    }

    if (this.manager.managerBill.cardNumber != null) {
      this.service.updateManagerBill(this.managerToSave.managerBill);
    }

  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 10000,
    });
  }

  goToManagerInfo() {
    this.router.navigate(['/manager-info']);
  }


  ngOnInit(): void {
    this.service.getManagerInfo().subscribe(data => {
      this.manager = data;
    }, error => console.log(error));
  };

}
