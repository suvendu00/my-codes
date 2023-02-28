export class Booking {
    constructor(
        public userName: string,
        public modelName: string,
        public licenseNo: string,
        public duration: number,
        public location: string,
        public quantity: number
    ) { }
}