import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { RefillService } from 'src/app/services/refill-service.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './refill.component.html',
  styleUrls: ['./refill.component.css']
})

export class RefillComponent implements OnInit {

  constructor(private refill: RefillService) {

  }

  response="bye"
  show=false
  statusClass = true
  resultsShowHide = false
  collapsible = [false, false, false]
  class = ["changeCSS", "changeCSS", "changeCSS"]

  res: Array<{
    id: number,
    subscriptionId: number,
    quantity: number,
    refilledDate: String,
    memberId: string,
    payStatus: boolean,
  }> = [];

  fillDiv: Array<string> = []



  oneInputForm = new FormGroup({
    subsciptionid: new FormControl('')
  })



  fiveInputForm = new FormGroup({
    subsciptionid: new FormControl(''),
    quantity: new FormControl(''),
    location: new FormControl(''),
    memberid: new FormControl(''),
    payStatus: new FormControl('')
  })

  twoInputForm = new FormGroup({
    date: new FormControl(''),
    memberid: new FormControl('')

  })

  fetchWithMemId() {
    console.log(this.refill.getToken())
    this.refill.getData(this.oneInputForm.value.subsciptionid).subscribe(


      (d) => {
        this.res = JSON.parse(JSON.stringify(d));
        this.resultsShowHide = true;

      },

      (e) => {
            this.show=true;
            this.response=e.error
      }
    )


  }
  twoInputFunc() {

   
    this.refill.getData3(
      this.twoInputForm.value.memberid, this.twoInputForm.value.date
    ).subscribe((d) => {

      this.res = JSON.parse(JSON.stringify(d))
      // this.res = JSON.parse(JSON.stringify(d));
      this.resultsShowHide = true;
    },

      (e) => { 
                this.show=true;
               this.response=e.error
       })


  }

  fiveInputFunc() {

    this.refill.getData2(this.fiveInputForm.value.subsciptionid,
      this.fiveInputForm.value.quantity,
      this.fiveInputForm.value.location,
      this.fiveInputForm.value.memberid,
      this.fiveInputForm.value.payStatus).subscribe((d) => {

        this.res = []
        this.res.push(JSON.parse(JSON.stringify(d)))
        console.log(this.res)
        this.resultsShowHide = true;
      },
        (e) => { 
          this.show=true;
          this.response=e.error
        
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
