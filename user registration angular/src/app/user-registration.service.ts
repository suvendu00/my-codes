import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  constructor(private http: HttpClient) { }

  public doRegistration(user: any): Observable<any> {
    return this.http.post("http://localhost:8097/register", user);
  }

  // public doRegistration(user: any){
  //   return this.http.post("http://localhost:8087/register",user, {responseType:'text' as 'json'});
  // }

  public getUsers() {
    return this.http.get("http://localhost:8097/getAllUsers")
  }

  public getUserByEmail(email: any) {
    return this.http.get("http://localhost:8087/finduser/" + email);
  }

  public deleteUser(id: number) {
    return this.http.delete("http://localhost:8087/cancel/" + id);
  }

}
