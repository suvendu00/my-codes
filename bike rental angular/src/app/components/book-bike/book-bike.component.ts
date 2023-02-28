import { JsonPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Booking } from 'src/app/assets/booking';
import { BikeRentalService } from 'src/app/bike-rental.service';
import { BikesComponent } from '../bikes/bikes.component';

@Component({
  selector: 'app-book-bike',
  templateUrl: './book-bike.component.html',
  styleUrls: ['./book-bike.component.css']
})
export class BookBikeComponent implements OnInit {

  bikes: any;
  usernName: any = localStorage.getItem('username');
  book: any = new Booking('', '', '', 0, '', 0);
  responseMessage: any;
  errorMessage: any;

  bookingForm: any = new FormGroup({
    bikeName: new FormControl(''),
    location: new FormControl(''),
    licenseNo: new FormControl(''),
    duration: new FormControl(''),
    quantity: new FormControl(''),
  })

  constructor(private service: BikeRentalService) {

  }

  ngOnInit(): void {
    this.service.getAllBikes().subscribe((data) => this.bikes = data);
  }

  submit() {
    this.book.userName = this.usernName;
    this.book.modelName = this.bookingForm.value.bikeName;
    this.book.licenseNo = this.bookingForm.value.licenseNo;
    this.book.duration = this.bookingForm.value.duration;
    this.book.location = this.bookingForm.value.location;
    this.book.quantity = this.bookingForm.value.quantity;

    this.service.doBooking(this.book).subscribe((data) => { this.responseMessage = data },
      (error) => this.errorMessage = error.error);
    console.log("response " + this.responseMessage);
    console.log("error " + this.errorMessage);
    this.bookingForm.reset();
  }

}
