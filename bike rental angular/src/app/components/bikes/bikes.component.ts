import { Component, OnInit } from '@angular/core';
import { BikeRentalService } from 'src/app/bike-rental.service';

@Component({
  selector: 'app-bikes',
  templateUrl: './bikes.component.html',
  styleUrls: ['./bikes.component.css']
})
export class BikesComponent implements OnInit {

  bikes: any;
  bikeTypes: any;
  types: any;
  bikeDetails: any;
  bikeLocationDetails: any
  searchNameOutput: boolean = false;
  searchTypeOutput: boolean = false;

  constructor(private service: BikeRentalService) { }

  ngOnInit(): void {
    let resp = this.service.getAllBikes();
    resp.subscribe((data) => this.bikes = data);

    this.service.getAllTypes().subscribe((data) => this.types = data);
    localStorage.setItem('bikes', this.bikes);
  }

  getBikes() {
    return this.bikes;
  }

  searchByName(name: any) {
    this.service.getBikeByName(name).subscribe((data) => {
      this.bikeDetails = data,
        this.bikeLocationDetails = this.bikeDetails?.locationWiseAvailability;
    });
    this.searchNameOutput = true;
    this.searchTypeOutput = false;
  }

  searchByType(type: any) {
    this.service.getBikeByType(type).subscribe((data) => this.bikeTypes = data);
    this.searchTypeOutput = true;
    this.searchNameOutput = false;
  }

  callSearchName(e: any) {
    this.searchByName(e.target.value);
  }

  callSearchType(e: any) {
    this.searchByType(e.target.value);
  }

}
