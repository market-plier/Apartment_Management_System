import {Component, OnInit} from '@angular/core';
import {CommunalUtilityService} from "../../../services/communal-utility.service";
import {ActivatedRoute} from "@angular/router";
import {CommunalUtility} from "../../../models/communal-utility";
import {Location} from '@angular/common';

import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-communal-utilities-show',
  templateUrl: './communal-utilities-show.component.html',
  styleUrls: ['./communal-utilities-show.component.css']
})
export class CommunalUtilitiesShowComponent implements OnInit {
  calculationMethods: string[] = [
    'SquareMeters', 'PeopleCount', 'Floor'
  ];
  calc: string = 'PeopleCount';
  utility: CommunalUtility;
  status: string[] = [
    'Enabled', 'Disabled'
  ];
  dyr_type: string[] = [
    'Temporary', 'Constant'
  ];
  form: FormGroup;
  id: number;
  minDate: Date;

  constructor(private route: ActivatedRoute,
              private service: CommunalUtilityService,
              private location: Location
  ) {
    this.minDate = new Date();
    this.initForm();
  }

  ngOnInit(): void {
    this.getUtility();
  }

  initForm(): void {
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required,
        Validators.minLength(2),
        Validators.maxLength(255)]),
      coefficient: new FormControl('', [Validators.required,
        Validators.min(0.00001),
        Validators.max(1000)]),
      status: new FormControl(''),
      calculationMethod: new FormControl(''),
      durationType: new FormControl(''),
      deadline: new FormControl('', Validators.required)
    })
  }

  getUtility(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.service.getCommunalUtility(this.id)
        .subscribe(utility => {
          this.utility = utility;
          console.log(this.utility);
          this.form.get('status').setValue(this.utility.status);
          this.form.get('durationType').setValue(this.utility.durationType);
          this.form.get('calculationMethod').setValue(this.utility.calculationMethod);
          this.form.get('name').setValue(this.utility.name);
          this.form.get('coefficient').setValue(this.utility.coefficient);
          this.form.get('deadline').setValue(this.utility.deadline);
        });
  }
  save(): void {
    const communalUtility = {
      ...this.form.value,
      communalUtilityId: this.id,
    };

    this.service.updateCommunalUtility(communalUtility)
        .subscribe(() => this.goBack(),
            (error => {
              console.error('error caught in component')

              throw error;
            }));
  }

  goBack(): void {
    this.location.back();
  }

}
