import { Component, OnInit } from '@angular/core';
import { BikeRentalService } from 'src/app/bike-rental.service';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {

  bookings: any;
  bookingResult: boolean = false;
  outputMessage: boolean = false;
  errorMessage: any = "";
  cancelMessage: any;

  constructor(private service: BikeRentalService) { }

  ngOnInit(): void {
    let resp = this.service.getAllBookings(localStorage.getItem("username"));
    resp.subscribe((data) => { this.bookings = data, this.bookingResult = true }, (error) => { this.errorMessage = error.error, this.outputMessage = true });
    console.log(this.errorMessage);
  }

  cancel(bookingId: any) {
    this.service.cancelBooking(bookingId).subscribe(
      (data) => {
        this.cancelMessage = data,
          alert(this.cancelMessage),
          window.location.reload()
      },
      (error) => console.log(error.error));
  }

}
