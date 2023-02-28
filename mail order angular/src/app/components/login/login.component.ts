import { Component, OnInit } from '@angular/core';

import { RefillService } from 'src/app/services/refill-service.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService:RefillService) { }

  ngOnInit(): void {
  }


  tokens:any=""

 
  loginForm = new FormGroup({
    name: new FormControl(''),
    pass: new FormControl('')
  })
  condition:boolean=this.loginService.isLoggedIn();
   
  

  onSubmit(){
    
    
    this.loginService.generateToken(this.loginForm.value.name,this.loginForm.value.pass).subscribe(
     
        (response:any)=> {
          
            this.loginService.loginUser(response.token);
            
           window.location.href="/home";
        },
        (error: any) => {
          // console.log(error)
          // alert("Inalid credentials")
      })
  }

}
