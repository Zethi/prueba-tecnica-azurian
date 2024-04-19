import { Province } from './Province';

export interface City {
  id: number;
  province: Province;
  name: string;
}
