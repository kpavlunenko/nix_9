import {Component, OnInit} from '@angular/core';
import {CompanyApiService} from "../../../../service/company-api.service";
import {SalesIncomeApiService} from "../../../../service/sales-income-api.service";
import {HttpParams} from "@angular/common/http";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };

  public barChartLabels = [''];
  public barChartLegend = true;

  public barChartData = [
    {data: [0], label: 'revenue'},
    {data: [0], label: 'profit'}
  ];

  constructor(private _salesIncomeApiService: SalesIncomeApiService) {
  }

  ngOnInit() {
    this.getSalesIncome();
  }

  getSalesIncome(): void {
    // this.barChartLabels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
    this._salesIncomeApiService.getSalesIncomes(this.initHttpParams())
      .subscribe(salesIncomes => {
        let dates: string[] = [];
        let revenue: number[] = [];
        let profit: number[] = [];
        for (let salesIncome of salesIncomes) {
          dates.push(formatDate(salesIncome.date, 'dd-MM', 'en-US'));
          revenue.push(salesIncome.revenue);
          profit.push(salesIncome.profit);
        }
        this.barChartLabels = dates;
        this.barChartData = [
          {data: revenue, label: 'revenue'},
          {data: profit, label: 'profit'}
        ]
      });
  }

  initHttpParams(): any {
    return new HttpParams();
  }

}
