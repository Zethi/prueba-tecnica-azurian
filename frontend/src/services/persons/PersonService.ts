import { Person } from "@/interfaces/Person";

interface CreateRequest {
  rut: string;
  firstName: string;
  lastName: string;
  gender: string;
  civilStatus: string;
  address: {
    city_id: number;
    streetName: string;
    number: number;
  };
}

export class PersonService {
  public async getAll(): Promise<Person[]> {
    const persons = await fetch(
      `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/person`
    );

    return (await persons.json()).persons;
  }

  public create(person: CreateRequest): void {
    fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/api/person`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(person),
    });
  }
}
