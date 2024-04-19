import { Province } from "@/interfaces/Province";

export class ProvinceService {
  public async getAll(): Promise<Province[]> {
    const provinces = await fetch(
      `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/province`
    );

    return (await provinces.json()).provinces;
  }

  public async getByRegion(regionID: number): Promise<Province[]> {
    const regions = await fetch(
      `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/province/region/${regionID}`
    );

    return (await regions.json()).regions;
  }
}
