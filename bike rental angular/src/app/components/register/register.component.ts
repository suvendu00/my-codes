import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Register } from 'src/app/assets/register';
import { BikeRentalService } from 'src/app/bike-rental.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private service: BikeRentalService) { }

  ngOnInit(): void {
  }

  loginForm: any = new FormGroup({
    fname: new FormControl(''),
    email: new FormControl(''),
    phno: new FormControl(''),
    uname: new FormControl(''),
    pass: new FormControl(''),

  })

  registerUser: Register = new Register("", 0, "", "", "");

  message: any;

  register() {
    this.registerUser.fullName = this.loginForm.value.fname;
    this.registerUser.phno = this.loginForm.value.phno;
    this.registerUser.email = this.loginForm.value.email;
    this.registerUser.userName = this.loginForm.value.uname;
    this.registerUser.password = this.loginForm.value.pass;
    console.log('submitted');
    let resp = this.service.doRegister(this.registerUser);
    resp.subscribe((data: any) => {
      this.message = data;
      console.log(data);
    },
      (errors: any) => {
        console.log(errors.error);
      });
    this.loginForm.reset();
  }
}
