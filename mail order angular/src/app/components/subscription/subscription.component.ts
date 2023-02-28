import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { RefillService } from 'src/app/services/refill-service.service';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})

export class SubscriptionComponent implements OnInit {
  res = "hi"
  shows = false
  constructor(private subscription: RefillService) {
  }
  statusClass = true
  resultsShowHide = false
  collapsible = [false, false, false]
  class = ["changeCSS", "changeCSS", "changeCSS"]


  fillDiv: Array<string> = []


  elevenInputForm = new FormGroup({
    id: new FormControl(''),
    memberId: new FormControl(''),
    memberLocation: new FormControl(''),
    policyNumber: new FormControl(''),
    insuranceProvider: new FormControl(''),
    date: new FormControl(''),
    dosage: new FormControl(''),
    quantity: new FormControl(''),
    drugName: new FormControl(''),
    doctorDetails: new FormControl(''),
    courseDuration: new FormControl('')

  })


  twoInputForm = new FormGroup({
    subsciptionid: new FormControl(''),
    memberid: new FormControl('')

  })

  ns = {
    id: 1,
    memberId: null,
    memberLocation: null,
    policyNumber: null,
    insuranceProvider: null,
    date: null,
    dosage: null,
    quantity: null,
    drugName: null,
    doctorDetails: null,
    courseDuration: null
  }
  fetchWithMemId() {




    this.subscription.getSub1(this.elevenInputForm.value.id,
      this.elevenInputForm.value.memberId,
      this.elevenInputForm.value.memberLocation,
      this.elevenInputForm.value.policyNumber,
      this.elevenInputForm.value.insuranceProvider,
      this.elevenInputForm.value.date,
      this.elevenInputForm.value.dosage,
      this.elevenInputForm.value.quantity,
      this.elevenInputForm.value.drugName,
      this.elevenInputForm.value.doctorDetails,
      this.elevenInputForm.value.courseDuration)
      .subscribe(


        (d) => {
          console.log(JSON.parse(JSON.stringify(d)));
          this.res = d;
          console.log(this.res);
          this.shows = true
          this.resultsShowHide = true;


        },
        (e) => {
          if (e.error.text) this.res = e.error.text
          else {


            this.res = "Id already in our records"
            this.shows = true
          }

        }
      )


  }


  twoInputFunc() {


    this.subscription.getSub2(
      this.twoInputForm.value.memberid, this.twoInputForm.value.subsciptionid
    ).subscribe((d) => {

      this.res = JSON.parse(JSON.stringify(d))
      this.shows = true
    },

      (e) => {

        if (e.error.text) {

          this.res = e.error.text
          this.shows = true
        }
        else {

          this.res = "No subscription left to unsubscribe"
          this.shows = true
        }

      }
    )

  }


  changeStyle(index: any) {

    for (let i = 0; i < 3; i++) {

      if (i == index) {
        this.collapsible[i] = this.collapsible[i] == true ? false : true;
      }
      else {
        this.collapsible[i] = false;
        this.class[i] = "changeCSS"
      }

    }
    this.class[index] = this.class[index] == "activeCSS" ? "changeCSS" : "activeCSS"
  }


  ngOnInit(): void {
  }

}
