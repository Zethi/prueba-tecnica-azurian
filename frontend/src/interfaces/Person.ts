import { Address } from "./Adrdress";

export interface Person {
  id: number;
  firstName: string;
  lastName: string;
  gender: string;
  civilStatus: string;
  address: Address;
  rut: string;
}

export class PersonEntity implements Person {
  id: number;
  firstName: string;
  lastName: string;
  gender: string;
  civilStatus: string;
  address: Address;
  rut: string;

  constructor(
    id: number,
    firstName: string,
    lastName: string,
    gender: string,
    civilStatus: string,
    address: Address,
    rut: string
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.civilStatus = civilStatus;
    this.address = address;
    this.rut = rut;
  }
}
