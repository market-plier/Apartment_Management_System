import {Component, OnInit} from '@angular/core';
import {CommunalUtilityService} from "../../../services/communal-utility.service";
import {Location} from '@angular/common';
import {ActivatedRoute} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'app-communal-utilities-create',
    templateUrl: './communal-utilities-create.component.html',
    styleUrls: ['./communal-utilities-create.component.css']
})
export class CommunalUtilitiesCreateComponent implements OnInit {
    calculationMethods: string[] = [
        'SquareMeters', 'PeopleCount', 'Floor'
    ];
    form: FormGroup;
    minDate: Date;
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
        this.initForm();
    }

    ngOnInit(): void {
    }

    initForm(): void {
        this.form = new FormGroup({
            name: new FormControl('', [Validators.required,
                Validators.minLength(2),
                Validators.maxLength(255)]),
            coefficient: new FormControl('', [Validators.required,
                Validators.min(0.00001),
                Validators.max(1000)]),
            status: new FormControl('', Validators.required),
            calculationMethod: new FormControl('', Validators.required),
            durationType: new FormControl('', Validators.required),
            deadline: new FormControl('', Validators.required)
        })
    }

    add(): void {
        const communalUtility = {
            ...this.form.value,
        };
        this.service.addCommunalUtility(communalUtility)
            .subscribe(() => this.goBack());
    }

    goBack(): void {
    this.location.back();
  }
}
