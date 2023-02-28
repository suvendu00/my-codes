import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { BikeRentalService } from 'src/app/bike-rental.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private service: BikeRentalService) { }

  ngOnInit(): void {
  }

  loginForm: any = new FormGroup({
    name: new FormControl(''),
    pass: new FormControl('')
  })

  loginError: string = "";

  login() {
    console.log(this.loginForm.value.name);
    let resp = this.service.doLogin(this.loginForm.value.name, this.loginForm.value.pass);
    resp.subscribe(
      (data: any) => {
        this.service.loginUser(data.token);
        window.location.href = "/home";
        localStorage.setItem("username", this.loginForm.value.name);

      },
      (error) => {
        this.loginError = error.error;
      }
    )
  }


}
