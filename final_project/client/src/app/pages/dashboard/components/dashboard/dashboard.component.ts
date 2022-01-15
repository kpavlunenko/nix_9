import {Component, OnInit} from '@angular/core';
import {CompanyApiService} from "../../../../service/company-api.service";
import {SalesIncomeApiService} from "../../../../service/sales-income-api.service";
import {HttpParams} from "@angular/common/http";
import {formatDate} from "@angular/common";
import { appConstRole } from 'src/app/app.const.role';
import {NgxPermissionsService} from "ngx-permissions";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  public appConstRole = appConstRole;

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

  constructor(private _salesIncomeApiService: SalesIncomeApiService,
              private permissionsService: NgxPermissionsService) {
  }

  ngOnInit() {
    this.getSalesIncome();
  }

  getSalesIncome(): void {
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
        if (this.permissionsService.getPermission(this.appConstRole.sales_manager)) {
          this.barChartData = [
            {data: revenue, label: 'revenue'}
          ]
        } else {
          this.barChartData = [
            {data: revenue, label: 'revenue'},
            {data: profit, label: 'profit'}
          ]
        }

      });
  }

  initHttpParams(): any {
    return new HttpParams();
  }

}
