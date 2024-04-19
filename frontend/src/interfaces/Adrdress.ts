import { City } from "./City";
import { Province } from "./Province";
import { Region } from "./Region";

export interface Address {
  id: number;
  city: City;
  streetName: string;
  number: number;
  region: Region;
  province: Province;
}

export class AddressEntity implements Address {
  id: number;
  city: City;
  streetName: string;
  number: number;
  region: Region;
  province: Province;

  constructor(
    id: number,
    city: City,
    streetName: string,
    number: number,
    region: Region,
    province: Province
  ) {
    this.id = id;
    this.city = city;
    this.streetName = streetName;
    this.number = number;
    this.region = region;
    this.province = province;
  }
}
