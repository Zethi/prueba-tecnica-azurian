import { Region } from "@/interfaces/Region";

export class RegionService {
  public async getAll(): Promise<Region[]> {
    const regions = await fetch(
      `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/region`
    );

    return (await regions.json()).regions;
  }
}
