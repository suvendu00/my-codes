import { DrugLocation } from "./drugLocation";

export class Drug{
    constructor(public drugId:any,
        public drugName:string,
        public manufacturer:string,
        public manufactureDate:string,
        public expiryDate:string,
        public unitsInAPackage:number,
        public costPerPackage:number,
        public locationWiseQuantity:Array<DrugLocation>){}
}