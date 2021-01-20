import {Component, OnInit} from '@angular/core';
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {ManagerSubBillService} from "../../../services/manager-sub-bill.service";
import {ApartmentInfoService} from "../../../services/apartment-info.service";

class single {
    name?: String;
    value?: Number;
}

@Component({
    selector: 'app-manager-sub-bills-dashboard',
    templateUrl: './manager-sub-bills-dashboard.component.html',
    styleUrls: ['./manager-sub-bills-dashboard.component.scss']
})

export class ManagerSubBillsDashboardComponent implements OnInit {
    constructor(private service: ManagerSubBillService) {
    }

    subbills: ManagerSubBill[] = [];
    balance = 0;
    view: any[] = [700, 280];
    single: single[][] = [][6];
    // options
    label = "Days left"
    now = new Date();
    total = new Date(this.now.getFullYear(), this.now.getMonth(), 0).getDate(); //number of days in this month
    showLegend: boolean = true;
    showLabels: boolean = true;
    circleOnPage = 6;

    onSelect(event) {
        console.log(event);
    }

    ngOnInit(): void {
        this.service.getAllManagerSubBills().subscribe(
            data => {
                this.subbills = data;
                this.setCirlesData();
            }
        );
    }

    setCirlesData() {
        var m = this.subbills.length;
        var s = [];
        var n = 0, i = 0;
        var single: single[][] = [[]];
        for (var j = 0; j < m; j++) {
            s[i] = {
                name: this.subbills[j].communalUtility.name,
                value: this.countTotal(this.subbills[j].communalUtility.deadline),
            };
            if (s[i].value == 0) s[i].value = 0.0000001
            i++;
            if (i == this.circleOnPage) {
                single[n] = s;
                n++;
                i = 0;
                s = [];
            }
        }
        if (s.length > 0) single[n] = s;
        Object.assign(this, {single});
    }

    countTotal(deadLine: Date): Number {
        var date1 = new Date(deadLine);
        var date2 = new Date(new Date());
        return Math.ceil(Math.abs(date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
    }

    onResize(event) {
        if (event.target.innerWidth > 1200) {
            this.view = [700, 280];
            this.circleOnPage = 6;
        } else if (event.target.innerWidth < 1200 && event.target.innerWidth > 1000) {
            this.view = [600, 280];
            this.circleOnPage = 3;
        } else if (event.target.innerWidth < 1000 && event.target.innerWidth > 700) {
            this.view = [400, 280];
            this.circleOnPage = 2;
        } else {
            this.view = [200, 280];
            this.circleOnPage = 1;
        }
        this.setCirlesData();
    }
}

