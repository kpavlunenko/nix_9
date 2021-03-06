import {Component, OnInit, Input} from '@angular/core';
import {UserResponseDto} from "../../../../model/user-response-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {UserApiService} from "../../../../service/user-api.service";
import {BankAccountApiService} from "../../../../service/bank-account-api.service";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  id?: number;

  @Input() user?: UserResponseDto;

  constructor(private route: ActivatedRoute,
              private _userApiService: UserApiService,
              private _bankAccountApiService: BankAccountApiService,
              private _location: Location,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getUser();
  }

  getUser(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._userApiService.getUser(this.id)
      .subscribe(user => this.user = user);
  }

  goBack(): void {
    this._router.navigate(['users']);
  }

  deleteBankAccountById(id: number): void {
    this._bankAccountApiService.deleteById(id).subscribe(() => {
      this.getUser();
    });
  }

  createBankAccount(): void {
    this._router.navigate(['bankAccounts/new'],{
      queryParams: {user: this.id}
    });
  }

  downloadAccountStatement(id: number, iban: string): void {
    this._bankAccountApiService.getAccountStatement(id, new HttpParams())
      .subscribe( response => {
          const link = document.createElement('a');
          link.href = window.URL.createObjectURL(new Blob([response], {type: 'text/csv'}));
          link.download = iban + '.csv';
          link.click();
        }
      );
  }

  bankAccountDetails(bankAccountId: number): void {
    this._router.navigate(['bankAccounts/details/' + bankAccountId],{
      queryParams: {user: this.id}
    });
  }

  updateUser(): void {
    this._router.navigateByUrl('users/update/' + this.id);
  }

}
