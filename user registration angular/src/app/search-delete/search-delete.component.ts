import { Component, OnInit } from '@angular/core';
import { UserRegistrationService } from '../user-registration.service';

@Component({
  selector: 'app-search-delete',
  templateUrl: './search-delete.component.html',
  styleUrls: ['./search-delete.component.css']
})
export class SearchDeleteComponent implements OnInit {

  users: any;
  searchresult: any;
  email: any;
  constructor(private service: UserRegistrationService) { }

  ngOnInit(): void {
    let resp = this.service.getUsers();
    resp.subscribe((data) => this.users = data);
    console.log("users " + this.users);
  }
  public deleteUser(id: number) {
    let resp = this.service.deleteUser(id);
    resp.subscribe((data) => this.users = data);
  }

  public findUserByEmailID(email: any) {
    let resp = this.service.getUserByEmail(email)
    resp.subscribe((data) => this.searchresult = data);
  }
}
