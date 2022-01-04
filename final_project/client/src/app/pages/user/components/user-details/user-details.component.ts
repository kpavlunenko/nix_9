import {Component, Input, OnInit} from '@angular/core';
import {CurrencyResponseDto} from "../../../../model/currency/currency-response-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {CurrencyApiService} from "../../../../service/currency-api.service";
import {Location} from "@angular/common";
import {UserResponseDto} from "../../../../model/user/user-response-dto";
import {UserApiService} from "../../../../service/user-api.service";
import {TokenStorageService} from "../../../../service/token-storage.service";
import {appConstRole} from "../../../../app.const.role";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  email:string = "";
  @Input() user?: UserResponseDto;

  constructor(private _route: ActivatedRoute,
              private tokenStorageService: TokenStorageService,
              private _userApiService: UserApiService,
              private _location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser(): void {
    if (this.tokenStorageService.getUser().roles.length && this.tokenStorageService.getUser().roles[0] == appConstRole.admin) {
      this.email = String(this._route.snapshot.paramMap.get('email'));
    } else {
      this.email = this.tokenStorageService.getUser().username;
    }
    this._userApiService.getUser(this.email)
      .subscribe(user => this.user = user);
  }

  goBack(): void {
    this._location.back();
  }

  updateUser(): void {
    this._router.navigateByUrl('users/update/' + this.email);
  }

}
