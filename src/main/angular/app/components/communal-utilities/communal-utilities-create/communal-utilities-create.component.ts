import {Component, OnInit} from '@angular/core';
import {CommunalUtilityService} from "../../../services/communal-utility.service";
import {CommunalUtility} from "../../../models/communal-utility";
import {Location} from '@angular/common';
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-communal-utilities-create',
    templateUrl: './communal-utilities-create.component.html',
    styleUrls: ['./communal-utilities-create.component.css']
})
export class CommunalUtilitiesCreateComponent implements OnInit {
    calculationMethods: string[] = [
        'SquareMeters', 'PeopleCount', 'Floor'
    ];
    utility: CommunalUtility = {
        name: null,
        status: null,
        durationType: null,
        calculationMethod: null,
        coefficient: 0,
        deadline: null
    };
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
    }


  add(): void {
    this.service.addCommunalUtility(this.utility)
        .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }
}
