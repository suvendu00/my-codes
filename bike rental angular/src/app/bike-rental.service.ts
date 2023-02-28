import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Register } from './assets/register';

@Injectable({
  providedIn: 'root'
})
export class BikeRentalService {

  token: any = localStorage.getItem('token');

  headers_object = new HttpHeaders({
    'Content-Type': 'application/json',
    // 'Accept': 'application/json, text/plain',
    'Accept': 'text/html,application/json, application/xhtml+xml, */*',
    'Authorization': "Bearer " + this.token
  });

  httpOptions = {
    headers: this.headers_object
  };



  constructor(private http: HttpClient) { }

  doLogin(username: any, password: any) {
    return this.http.get(`http://localhost:8093/authenticate?username=${username}&password=${password}`);
  }

  loginUser(token: any) {
    localStorage.setItem("token", token);
    return true;
  }

  getToken() {
    return localStorage.getItem("token");
  }

  logout() {
    localStorage.removeItem("token");
    return true;
  }

  doRegister(user: Register): Observable<any> {
    return this.http.post("http://localhost:8093/register", user, { responseType: 'text' });
  }

  getAllBikes() {
    return this.http.get("http://localhost:9091/bikeservice/getAllBikes", this.httpOptions);
  }

  getBikeById(id: any) {
    return this.http.get("http://localhost:9091/bikeservice/getBikeById/" + id, this.httpOptions);
  }

  getBikeByName(name: any) {
    return this.http.get("http://localhost:9091/bikeservice/getBikeByName/" + name, this.httpOptions);
  }

  getBikeByType(type: any) {
    return this.http.get("http://localhost:9091/bikeservice/getBikeByType/" + type, this.httpOptions);
  }

  getAllBookings(username: any) {
    return this.http.get("http://localhost:9092/bookingservice/getBookingDetails/" + username, this.httpOptions);
  }

  getAllTypes() {
    return this.http.get("http://localhost:9091/bikeservice/types", this.httpOptions);
  }

  doBooking(book: any) {
    return this.http.post("http://localhost:9092/bookingservice/book", book, this.httpOptions);
  }

  cancelBooking(bookingId: any) {
    return this.http.get("http://localhost:9092/bookingservice/cancelBooking/" + bookingId, this.httpOptions);
  }

}
