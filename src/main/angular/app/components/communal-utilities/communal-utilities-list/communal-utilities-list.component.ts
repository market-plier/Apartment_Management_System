import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../../services/token-storage.service";
import {CommunalUtility} from "../../../models/communal-utility";
import {CommunalUtilityService} from "../../../services/communal-utility.service";
import {MatTableDataSource} from "@angular/material/table";
import {Router} from "@angular/router";

@Component({
  selector: 'app-communal-utilities-list',
  templateUrl: './communal-utilities-list.component.html',
  styleUrls: ['./communal-utilities-list.component.css']
})
export class CommunalUtilitiesListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'status', 'durationType', 'deadline', 'calculation', 'coefficient'];
  utilities: CommunalUtility[];
  datasource: MatTableDataSource<CommunalUtility>
  enabledUtilities: CommunalUtility[];
  disabledUtilities: CommunalUtility[];
  myColor: 'white';

  constructor(private tokenStorageService: TokenStorageService,
              private comUtilService: CommunalUtilityService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getUtilities();

  }

  getUser(): any {
    return this.tokenStorageService.getUser();
  }

  applyFilterDisabled() {
    this.datasource.filter = 'Enabled';
  }

  applyFilterAll() {
    this.datasource.filter = '';
  }

  getRole(): any {
    return this.tokenStorageService.getRole();
  }

  getUtilities() {
    this.comUtilService.getCommunalUtilities()
        .subscribe(utilities => {
          this.utilities = utilities
          this.enabledUtilities = utilities.filter(utility => utility.status === 'Enabled');
          this.disabledUtilities = utilities.filter(utility => utility.status === 'Disabled');
          this.datasource = new MatTableDataSource(utilities)
        });
  }

  route(id: number) {
    this.router.navigate([`/communal-utilities/${id}`]);
  }
}
