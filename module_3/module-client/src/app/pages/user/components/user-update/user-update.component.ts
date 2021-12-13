import {Component, OnInit, Input} from '@angular/core';
import {UserRequestDto} from "../../../../model/user-request-dto";
import {Observable} from "rxjs";
import {Location} from "@angular/common";
import {UserResponseDto} from "../../../../model/user-response-dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserApiService} from "../../../../service/user-api.service";

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  id: number = 0;
  user?: UserRequestDto;
  @Input() userResponseDto?: Observable<UserResponseDto>;

  userForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.email),
    phone: new FormControl('', Validators.required)
  });

  constructor(private _userApiService: UserApiService,
              private _router: Router,
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getUser();
  }

  update(): void {
    let user = this.userForm.value as UserRequestDto;

    this._userApiService.update(this.id, user).subscribe(() => {
      this._router.navigateByUrl('users');
    });
  }

  getUser(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.userResponseDto = this._userApiService.getUser(this.id);
    this.userResponseDto.subscribe(userResponseDto =>
      this.userForm.setValue({
        firstName: userResponseDto.firstName,
        lastName: userResponseDto.lastName,
        email: userResponseDto.email,
        phone: userResponseDto.phone
      }))
  }

  goBack(): void {
    this._location.back();
  }

}
