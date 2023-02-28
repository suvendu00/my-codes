import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';




@Injectable({
  providedIn: 'root'
})


export class RefillService {

  constructor(private http: HttpClient) {

  }

  getData(subsciptionid: any) {
    var t = localStorage.getItem("token");

    var head = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer " + t
    });

    const httpOptions = {
      headers: head
    };
    let url = "http://localhost:8094/viewRefillStatus/";

    return this.http.get(url + subsciptionid,httpOptions);
  }

  getData2(subsciptionid: any, quantity: any, location: any, memberId: any, payStatus: any) {

    var t = localStorage.getItem("token");

    var head = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer " + t
    });

    const httpOptions = {
      headers: head
    };

    let url = `http://localhost:8094/requestAdhocRefill/${subsciptionid}/${quantity}/${location}/${memberId}/${payStatus}`;


    return this.http.post<any>(url, { limit: 10 }, httpOptions);

  }

  getData3(memberid: any, date: any) {
    var t = localStorage.getItem("token");

    var head = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer " + t
    });

    const httpOptions = {
      headers: head
    };
    let url = `http://localhost:8094/getRefillDuesAsOfDate/${memberid}/${date}`;

    return this.http.get<any>(url, httpOptions);
  }




  getSub1(id: any, memberId: any, memberLocation: any, policyNumber: any,
    insuranceProvider: any, date: any, dosage: any, quantity: any, drugName: any, doctorDetails: any, courseDuration: any) {
    var t = localStorage.getItem("token");

    var head = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer " + t
    });

    const httpOptions = {
      headers: head
    };

  

    let url = `http://localhost:8095/api/subscribe`;

    return this.http.post<any>(url,
      {
        "id": id,
        "memberId": memberId,
        "memberLocation": memberLocation,
        "policyNumber": policyNumber,
        "insuranceProvider": insuranceProvider,
        "date": date,
        "dosage": dosage,
        "quantity": quantity,
        "drugName": drugName,
        "doctorDetails": doctorDetails,
        "courseDuration": courseDuration
      },
      httpOptions);


  }

  getSub2(memberid: any, subsciptionid: any) {
    var t = localStorage.getItem("token");

    var head = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer " + t
    });

    const httpOptions = {
      headers: head
    };

    let url = `http://localhost:8095/api/unsubscribe/${memberid}/${subsciptionid}`;

    return this.http.post<any>(url, { limit: 10 }, httpOptions);
  }







  // sujit
  
  url = "http://localhost:9093/";

  generateToken(username: any, password: any) {


    return this.http.get(`http://localhost:9093/authenticate?username=${username}&password=${password}`);

  }



  loginUser(token: any) {
    localStorage.setItem("token", token)
    return true;
  }

  isLoggedIn() {
    let token = localStorage.getItem("token");
    if (token == undefined || token === '' || token == null) {
      return false;
    }
    else {
      return true;
    }

  }

  getToken() {
    return localStorage.getItem("token");
  }

  logout() {
    localStorage.removeItem("token")
    return true;
  }






  // suvendu

  public getDrugs() {
    var t = localStorage.getItem("token");

    var head = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer " + t
    });

    const httpOptions = {
      headers: head
    };

    return this.http.get("http://localhost:8087/drugservice/getAllDrugs", httpOptions);
  }

  public getDrugById(id: any) {
    var t = localStorage.getItem("token");

    var head = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer " + t
    });

    const httpOptions = {
      headers: head
    };

    return this.http.get("http://localhost:8087/drugservice/searchDrugsById/" + id, httpOptions);
  }

  public getDrugByName(name: any) {
    var t = localStorage.getItem("token");

    var head = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer " + t
    });

    const httpOptions = {
      headers: head
    };

    return this.http.get("http://localhost:8087/drugservice/searchDrugsByName/" + name, httpOptions);
  }


}
