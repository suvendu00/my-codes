import { Component, OnInit } from '@angular/core';
import { RefillService } from 'src/app/services/refill-service.service';
// import { DrugServiceService } from '../drugassets/';
import { DrugLocation } from '../drugassets/drugLocation';
import { Drug } from '../drugassets/drug';
@Component({
  selector: 'app-drug',
  templateUrl: './drug.component.html',
  styleUrls: ['./drug.component.css']
})
export class DrugComponent implements OnInit {

  drugs: any;
  id: any;
  name:any;
  drugDetails: any;
  drugLocations: any;
  output: string = "";
  loacion: string = "";
  outputDiv:boolean = false;
  error_div = false;
  errorMessage:string="";

  constructor(private service: RefillService) { }

  ngOnInit(): void {
    let resp = this.service.getDrugs();
    resp.subscribe((data) => this.drugs = data);
  }
  

  public findDrugById(id: any) {
    // window.location.reload();

    let resp = this.service.getDrugById(id);
    resp.subscribe((data) => {this.drugDetails = data, 
      this.drugLocations = this.drugDetails?.locationWiseQuantity
    },
    (error) => {this.errorMessage = error.error, alert(this.errorMessage)});

    // console.log(this.drugDetails)
    // console.log(this.errorMessage);
    if(this.errorMessage === "Drug Not Found"){
      console.log("Inside if");
            this.outputDiv = false;
    }
    else{
      console.log("hello inside else");
      this.outputDiv = true;
    }
  }

  public findDrugByName(name:any){
    let resp = this.service.getDrugByName(name);
    resp.subscribe((data) => {this.drugDetails = data, 
      this.drugLocations = this.drugDetails?.locationWiseQuantity
    },
    (error) =>{this.errorMessage = error.error, alert(this.errorMessage),window.location.reload()});

    if(this.errorMessage === "Drug Not Found"){
      this.outputDiv = false;
    }
    else{
      this.outputDiv = true;
    }

  }

  public callSearchName(e:any){
    console.log(e.target.value);
    this.findDrugByName(e.target.value);
  }

  public callSearchId(e:any){
    console.log(e.target.value);
    this.findDrugById(e.target.value);
  }

}
