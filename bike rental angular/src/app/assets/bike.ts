import { BikeLocation } from "./bikeLocation";

export class Bike {
    constructor(public bikeId: any,
        public modelName: string,
        public manufacturer: string,
        public type: string,
        public rentPrice: number,
        public availability: Array<BikeLocation>
    ) { }
}