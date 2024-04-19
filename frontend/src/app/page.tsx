import { Person } from "@/interfaces/Person";
import { PersonsTable } from "@/sections/home/PersonsTable";
import { PersonService } from "@/services/persons/PersonService";
import { Button } from "@mui/material";
import Link from "next/link";

export default async function Home() {
  const persons: Person[] = await new PersonService().getAll();

  return (
    <main className="flex justify-center my-24 flex-col items-center h-full">
      <div className="w-3/4">
        <PersonsTable persons={persons} />
        <div className="mt-8 flex justify-end">
          <Button variant="contained" color="inherit" style={{ background: "#0dce0d", color: "white" }} className="w-24"><Link href={"/insert"}>Crear</Link></Button>

        </div>
      </div>
    </main>
  );
}
