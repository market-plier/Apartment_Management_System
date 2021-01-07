import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../../services/token-storage.service";
import {CommunalUtility} from "../../../models/communal-utility";
import {CommunalUtilityService} from "../../../services/communal-utility.service";

@Component({
  selector: 'app-communal-utilities-list',
  templateUrl: './communal-utilities-list.component.html',
  styleUrls: ['./communal-utilities-list.component.css']
})
export class CommunalUtilitiesListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'status', 'durationType', 'deadline', 'calculation'];
  utilities: CommunalUtility[]

  constructor(private tokenStorageService: TokenStorageService,
              private comUtilService: CommunalUtilityService) {
  }

  ngOnInit(): void {
    this.getUtilities();
  }

  getUser(): any {
    return this.tokenStorageService.getUser();
  }

  getUtilities(): void {
    this.comUtilService.getCommunalUtilities()
        .subscribe(utilities => this.utilities = utilities);
  }


}
