import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";

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
        {title: 'Комунальні Послуги', cols: 8, rows: 2},
        {title: 'Оголошення', cols: 3, rows: 2},
        {title: 'Борги Керівника', cols: 2, rows: 2},
        {title: 'Оплатити Борги', cols: 5, rows: 2},
    ];


    constructor(public tokenService: TokenStorageService) {

    }

    ngOnInit(): void {
        if (this.tokenService.getRole() == 'MANAGER') {
            this.tiles[2].cols = 5;
        }
    }

}