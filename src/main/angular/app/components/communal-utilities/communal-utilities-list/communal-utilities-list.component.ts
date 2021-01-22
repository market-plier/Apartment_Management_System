import {Component, OnInit, ViewChild} from '@angular/core';
import {TokenStorageService} from "../../../services/token-storage.service";
import {CommunalUtility} from "../../../models/communal-utility";
import {CommunalUtilityService} from "../../../services/communal-utility.service";
import {MatTableDataSource} from "@angular/material/table";
import {Router} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
    selector: 'app-communal-utilities-list',
    templateUrl: './communal-utilities-list.component.html',
    styleUrls: ['./communal-utilities-list.component.scss']
})
export class CommunalUtilitiesListComponent implements OnInit {
    displayedColumns: string[] = ['name', 'status', 'durationType', 'deadline', 'calculation', 'coefficient'];
    utilities: CommunalUtility[];
    datasource: MatTableDataSource<CommunalUtility>
    searchUtilities: CommunalUtility[];
    myColor: 'white';
    loading: boolean = false;
    searchText: string = '';
    enabled: boolean;
    @ViewChild(MatPaginator) paginator: MatPaginator;

    constructor(private tokenStorageService: TokenStorageService,
                private comUtilService: CommunalUtilityService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getUtilities();

    }

    @ViewChild(MatSort) sort: MatSort;
    private errorMessage: any;

    getUser(): any {
        return this.tokenStorageService.getUser();
    }

    getRole(): any {
        return this.tokenStorageService.getRole();
    }

    getUtilities() {
        this.loading = true;
        this.comUtilService.getCommunalUtilities()
            .subscribe((utilities) => {
                    this.utilities = utilities;
                    console.log(this.utilities);
                    this.datasource = new MatTableDataSource(utilities);
                    this.datasource.paginator = this.paginator;
                    this.datasource.sort = this.sort;
                    this.loading = false;
                },
                (error => {
                    console.error('error caught in component')
                    this.errorMessage = error;
                    this.loading = false;

                    throw error;
                }));
    }

    route(id: number) {
        this.router.navigate([`/communal-utilities/${id}`]);
    }

    filterUtilities() {
        this.searchUtilities = this.utilities;
        if (this.enabled)
            this.searchUtilities = this.utilities.filter(utility => utility.status === 'Enabled');
        if (this.searchText != null || this.searchText !== ' ')
            console.log(this.searchText);
        this.searchUtilities = this.searchUtilities.filter(utility => utility.name.includes(this.searchText))
        this.datasource = new MatTableDataSource(this.searchUtilities);
        this.datasource.paginator = this.paginator;
        this.datasource.sort = this.sort;
    }
}
