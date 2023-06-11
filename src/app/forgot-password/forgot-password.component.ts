import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../services/user.service";
import {MatDialogRef} from "@angular/material/dialog";
import {NgxUiLoaderService} from "ngx-ui-loader";
import {SnackbarService} from "../services/snackbar.service";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
  forgotPasswordForm:any =FormGroup;
  responseMessage:any;

  constructor(private formBuilder :FormBuilder,
              private userService : UserService,
              public dialogRef:MatDialogRef<ForgotPasswordComponent>,
              private ngxService:NgxUiLoaderService,
              private snackbarService:SnackbarService) { }

  ngOnInit(): void {
  }

}
