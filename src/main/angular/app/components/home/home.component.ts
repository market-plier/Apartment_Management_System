import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";
import {transferArrayItem} from "@angular/cdk/drag-drop";

export interface Tile {
    cols: number;
    rows: number;
    title: string;
}

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    tiles: Tile[] = [
        {title: 'Subbills', cols: 3, rows: 1},
        {title: 'Announcements', cols: 1, rows: 2},
        {title: 'Debt of Manager Sub Bill', cols: 1, rows: 1},
        {title: 'Reports or request or something', cols: 1, rows: 1},
    ];


    constructor(public tokenService: TokenStorageService) {

    }

    ngOnInit(): void {
        if (this.tokenService.getRole() == 'MANAGER') {
            this.tiles[2].cols = 3;
        }
    }

}

