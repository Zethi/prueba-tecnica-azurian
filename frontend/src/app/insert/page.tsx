import { City } from "@/interfaces/City";
import { Province } from "@/interfaces/Province";
import { Region } from "@/interfaces/Region";
import { CreatePersonForm } from "@/sections/insert/CreatePersonForm";
import { CityService } from "@/services/persons/CityService";
import { ProvinceService } from "@/services/persons/ProvinceService";
import { RegionService } from "@/services/persons/RegionService";

export default async function Insert() {
    const regions: Region[] = await new RegionService().getAll();
    const provinces: Province[] = await new ProvinceService().getAll();
    const cities: City[] = await new CityService().getAll();

    return (
        <main className="w-full flex justify-center mt-8">
            <CreatePersonForm regions={regions} provinces={provinces} cities={cities} />


        </main>
    );
}
