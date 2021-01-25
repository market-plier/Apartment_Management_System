import {Component, OnInit} from '@angular/core';
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {ManagerSubBillService} from "../../../services/manager-sub-bill.service";

class single {
    name?: String;
    value?: String;
    deadLine?: number;
    progress?: number;
}

@Component({
    selector: 'app-manager-sub-bills-dashboard',
    templateUrl: './manager-sub-bills-dashboard.component.html',
    styleUrls: ['./manager-sub-bills-dashboard.component.scss']
})

export class ManagerSubBillsDashboardComponent implements OnInit {

    subbills: ManagerSubBill[] = [];
    billMap: Map<ManagerSubBill, number> = new Map<ManagerSubBill, number>();

    circleOnPage = 6;
    single: single[][] = [][this.circleOnPage];

    loading: boolean = false;

    constructor(private service: ManagerSubBillService) {
    }

    ngOnInit(): void {
        this.loading = true;

        this.service.getAllManagerSubBills().subscribe(
            data => {
                this.subbills = data;

                this.service.getManagerSubBillsDebt().subscribe(
                    response => {
                        Object.keys(response).forEach(key => {
                            this.billMap.set(JSON.parse(key), response[key]);
                        });

                        this.setCirlesData();
                        this.loading = false;
                    },

                    error => {
                        console.log(error);
                        this.loading = false;
                    });
            });
    }

    setCirlesData() {
        var m = this.subbills.length;
        var s = [];
        var n = 0, i = 0;
        var single: single[][] = [[]];

        for (var j = 0; j < m; j++) {

            s[i] = {
                name: this.subbills[j].communalUtility.name,
                deadLine: this.countTotal(this.subbills[j].communalUtility.deadline),
                value: this.subbills[j].balance.toLocaleString() + '$',
                progress: this.subbills[j].balance * 100 / this.fillProgress(this.subbills[j])
            };

            i++;

            if (i == this.circleOnPage) {
                single[n] = s;
                n++;
                i = 0;
                s = [];
            }
        }

        if (s.length > 0) {
            single[n] = s;
        }

        Object.assign(this, {single});
        console.log(single);
    }

    fillProgress(sub: ManagerSubBill): number {
        var val = 1;

        this.billMap.forEach(function (value, key) {
            if (key.communalUtility.name == sub.communalUtility.name) {
                if (value == 0) {
                    val = 1;
                }else {
                    val = value;
                }
            }
        });

        return val;
    }

    add(accumulator, a) {
        return accumulator + a;
    }

    countTotal(deadLine: Date): number {
        var date1 = new Date(deadLine);
        var date2 = new Date(new Date());

        return Math.ceil(Math.abs(date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
    }

    onResize(event) {
        if (event.target.innerWidth > 1200) {
            this.circleOnPage = 6;
        } else if (event.target.innerWidth < 1200 && event.target.innerWidth > 1000) {
            this.circleOnPage = 4;
        } else if (event.target.innerWidth < 1000 && event.target.innerWidth > 800) {
            this.circleOnPage = 2;
        } else {
            this.circleOnPage = 1;
        }

        this.setCirlesData();
    }
}

