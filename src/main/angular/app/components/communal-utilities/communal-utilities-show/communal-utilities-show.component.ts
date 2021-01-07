import {Component, OnInit} from '@angular/core';
import {CommunalUtilityService} from "../../../services/communal-utility.service";
import {ActivatedRoute} from "@angular/router";
import {CommunalUtility} from "../../../models/communal-utility";
import {Location} from '@angular/common';
import {CalculationMethod} from "../../../models/calculation-method";

@Component({
  selector: 'app-communal-utilities-show',
  templateUrl: './communal-utilities-show.component.html',
  styleUrls: ['./communal-utilities-show.component.css']
})
export class CommunalUtilitiesShowComponent implements OnInit {
  calculationMethods: CalculationMethod[];
  utility: CommunalUtility;
  submitted = false;
  status: string[] = [
    'Enabled', 'Disabled'
  ];
  dyr_type: string[] = [
    'Temporary', 'Constant'
  ];

  constructor(private route: ActivatedRoute,
              private service: CommunalUtilityService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
    this.getUtility();
    this.getCalculationMethods();
  }

  getUtility(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.service.getCommunalUtility(id)
        .subscribe(utility => this.utility = utility);
  }

  getCalculationMethods(): void {
    this.service.getCalculationMethods()
        .subscribe(calculation => this.calculationMethods = calculation);
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
