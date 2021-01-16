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
  utility: CommunalUtility;
  submitted = false;
  status: string[] = [
    'Enabled', 'Disabled'
  ];
  dyr_type: string[] = [
    'Temporary', 'Constant'
  ];
  form: FormGroup;

  constructor(private route: ActivatedRoute,
              private service: CommunalUtilityService,
              private location: Location
  ) {
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required,
        Validators.minLength(2),
        Validators.maxLength(255)]),
      coefficient: new FormControl('', [Validators.required,
        Validators.min(0.00001),
        Validators.max(1000)])
    })
  }

  ngOnInit(): void {
    this.getUtility();
  }

  getUtility(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.service.getCommunalUtility(id)
        .subscribe(utility => this.utility = utility);
  }


  save(): void {
    this.service.updateCommunalUtility(this.utility)
        .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit() {
    this.submitted = true;
  }
}
