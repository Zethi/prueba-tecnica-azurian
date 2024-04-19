import { City } from "@/interfaces/City";

export class CityService {
  public async getAll(): Promise<City[]> {
    const cities = await fetch(
      `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/city`
    );

    return (await cities.json()).cities;
  }

  public async getByProvince(provinceID: number) {
    const cities = await fetch(
      `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/city/province/${provinceID}`
    );

    return (await cities.json()).cities;
  }
}
