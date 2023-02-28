import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserRegistrationService } from '../user-registration.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  // name:any="";
  // email:any="";
  // experience:any=0;
  // domain:any="";

  // user:User = new User(this.name,this.email,this.experience,this.domain);
  user: User = new User("", "", 0, "");
  message: any;
  constructor(private service: UserRegistrationService) { }

  ngOnInit(): void {
  }

  public registerNow() {
    this.service.doRegistration(this.user).subscribe((data) => {
      console.log(data);
      this.message = data;
    }, error => console.log(error))
  }

}
