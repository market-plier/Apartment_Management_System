import {Component, OnInit} from '@angular/core';
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {ManagerSubBillService} from "../../../services/manager-sub-bill.service";

class single {
    name?: String;
    value?: String;
    daysLeft?: number;
    progress?: number;
    deadLine?: Date;
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
                this.setSubBillsDebt();
            },
            error => {
                console.log(error);
                this.loading = false;
            });
    }

    setSubBillsDebt() {
        this.service.getManagerSubBillsDebt().subscribe(
            response => {
                Object.keys(response).forEach(key => {
                    this.billMap.set(JSON.parse(key), response[key]);
                });
                this.setCirclesData();
                this.loading = false;
            },
            error => {
                console.log(error);
            });
    }

    setCirclesData() {
        var numberOfSubBills = this.subbills.length;
        var SubBillsOnOneSlide = [];
        var numberOfSlides = 0, i = 0;
        var single: single[][] = [[]];

        for (var j = 0; j < numberOfSubBills; j++) {
            //filling card`s data
            SubBillsOnOneSlide[i] = {
                name: this.subbills[j].communalUtility.name,
                daysLeft: this.countTotalDays(this.subbills[j].communalUtility.deadline),
                value: this.subbills[j].balance.toLocaleString() + '$',
                progress: this.subbills[j].balance * 100 / this.fillProgress(this.subbills[j]),
                deadLine: this.subbills[j].communalUtility.deadline
            };
            i++;
            //filling one slide with subBills
            if (i == this.circleOnPage) {
                single[numberOfSlides] = SubBillsOnOneSlide;
                numberOfSlides++;
                i = 0;
                SubBillsOnOneSlide = [];
            }
        }

        if (SubBillsOnOneSlide.length > 0) {
            single[numberOfSlides] = SubBillsOnOneSlide;
        }
        Object.assign(this, {single});
        console.log(single);
    }

    fillProgress(sub: ManagerSubBill): number {
        var debt = 1;
        this.billMap.forEach(function (value, key) {
            if (key.communalUtility.name == sub.communalUtility.name) {
                if (value == 0) {
                    debt = 1;
                } else {
                    debt = value;
                }
            }
        });
        return debt;
    }

    add(accumulator, a) {
        return accumulator + a;
    }

    countTotalDays(dl: Date): number {
        var deadLine = new Date(dl);
        var now = new Date();
        var days = Math.floor((deadLine.getTime() - now.getTime()) / (1000 * 3600 * 24));
        if (days < 0) {
            return 0;
        } else {
            return days;
        }
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
        this.setCirclesData();
    }
}

