import {Component, OnInit} from '@angular/core';
import {map} from 'rxjs/operators';
import {Breakpoints, BreakpointObserver} from '@angular/cdk/layout';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent {
    cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
        map(({matches}) => {
            if (matches) {
                return [
                    {title: 'Subbills', cols: 1, rows: 1},
                    {title: 'Debts', cols: 1, rows: 1},
                    {title: 'Announcements', cols: 1, rows: 1},
                    {title: 'Request', cols: 1, rows: 1}
                ];
            }

            return [
                {title: 'Subbills', cols: 2, rows: 1},
                {title: 'Debts', cols: 1, rows: 1},
                {title: 'Announcements', cols: 1, rows: 2},
                {title: 'Request', cols: 1, rows: 1}
            ];
        })
    );


    constructor(private breakpointObserver: BreakpointObserver) {

    }
}
