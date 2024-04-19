"use client";

import { City } from "@/interfaces/City";
import { Province } from "@/interfaces/Province";
import { Region } from "@/interfaces/Region";
import { PersonService } from "@/services/persons/PersonService";
import { Button, FormGroup, MenuItem, Select, SelectChangeEvent, TextField } from "@mui/material";
import { useRouter } from "next/navigation";
import { ChangeEvent, ChangeEventHandler, useState } from "react";


interface Props {
    regions: Region[]
    provinces: Province[]
    cities: City[]
}

export function CreatePersonForm({ regions, provinces, cities }: Props) {
    const router = useRouter()
    const [rut, setRut] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [genderSelected, setGenderSelected] = useState('');
    const [civilStatusSelected, setCivilStatusSelected] = useState('');
    const [regionSelected, setRegionSelected] = useState<number>();
    const [provinceSelected, setProvinceSelected] = useState<number>();
    const [citySelected, setCitySelected] = useState<number>();
    const [streetName, setStreetName] = useState('');
    const [houseNumber, setHouseNumber] = useState<number>();



    const handleRutChange = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setRut(event.target.value);
    };

    const handleFirstNameChange = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setFirstName(event.target.value);
    };

    const handleLastNameChange = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setLastName(event.target.value);
    };

    const handleGenderChange = (event: SelectChangeEvent) => {
        setGenderSelected(event.target.value);
    };

    const handleCivilStatusChange = (event: SelectChangeEvent) => {
        setCivilStatusSelected(event.target.value);
    };

    const handleRegionChange = (event: SelectChangeEvent) => {
        setRegionSelected(event.target.value as unknown as number);
        setProvinceSelected(undefined)
        setCitySelected(undefined);
    };

    const handleProvinceChange = (event: SelectChangeEvent) => {
        setProvinceSelected(event.target.value as unknown as number);
        setCitySelected(undefined);
    };

    const handleCityChange = (event: SelectChangeEvent) => {
        setCitySelected(event.target.value as unknown as number);
    };

    const handleStreetNameChange = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setStreetName(event.target.value);
    };
    const handleHouseNumberChange = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setHouseNumber(event.target.value as unknown as number);
    };

    const handleSubmit = () => {
        const person = {
            rut: rut,
            firstName: firstName, lastName: lastName, gender: genderSelected || "OTHER",
            civilStatus: civilStatusSelected || "UNMARRIED",
            address: {
                city_id: citySelected || 1,
                streetName: streetName,
                number: houseNumber || 0
            }
        }
        new PersonService().create(person)
        router.push('/')
    }

    const numbers = Array.from({ length: 150 }, (_, index) => index + 1);

    return (
        <div className="w-full flex flex-col items-center">
            <FormGroup sx={{ width: "50%" }} onSubmit={handleSubmit}>
                <TextField
                    label="RUT"
                    variant="filled"
                    fullWidth
                    margin="normal"
                    onChange={handleRutChange}
                />
                <div className="gap-5 flex">
                    <TextField
                        label="Nombres"
                        variant="filled"
                        fullWidth
                        margin="normal"
                        sx={{ width: "50%" }}
                        onChange={handleFirstNameChange}
                    />
                    <TextField
                        label="Apellidos"
                        variant="filled"
                        fullWidth
                        margin="normal"
                        sx={{ width: "50%" }}
                        onChange={handleLastNameChange}

                    />
                </div>

                <p>Genero</p>
                <Select
                    labelId="gender"
                    id="gender"
                    value={genderSelected}
                    onChange={handleGenderChange}
                    size="small"
                    MenuProps={{ PaperProps: { sx: { maxHeight: 150 } } }}
                >
                    <MenuItem key={"MALE"} value={"MALE"}>Masculino</MenuItem>
                    <MenuItem key={"FEMALE"} value={"FEMALE"}>Femenino</MenuItem>
                    <MenuItem key={"OTHER"} value={"OTHER"}>Otro</MenuItem>
                </Select>

                <p>Estado civil</p>
                <Select
                    labelId="civilStatus"
                    id="civilStatus"
                    value={civilStatusSelected}
                    onChange={handleCivilStatusChange}
                    size="small"
                    MenuProps={{ PaperProps: { sx: { maxHeight: 150 } } }}
                >
                    <MenuItem key={"UNMARRIED"} value={"UNMARRIED"}>Soltero/a</MenuItem>
                    <MenuItem key={"MARRIED"} value={"MARRIED"}>Casado/a</MenuItem>
                </Select>
                <p>Región</p>
                <Select
                    labelId="region"
                    id="region"

                    onChange={handleRegionChange}
                    size="small"
                    MenuProps={{ PaperProps: { sx: { maxHeight: 150 } } }}
                >
                    {regions.map((region: Region) => {
                        return (
                            <MenuItem key={region.name} value={region.id}>{region.name}</MenuItem>
                        )
                    })
                    }
                </Select>
                <p>Provincia</p>
                <Select
                    labelId="province"
                    id="province"

                    onChange={handleProvinceChange}
                    size="small"
                    MenuProps={{ PaperProps: { sx: { maxHeight: 150 } } }}
                >
                    {regionSelected === undefined ? [] : provinces.filter(province => province.region.id == regionSelected).map((province) => {
                        return (
                            <MenuItem key={province.name} value={province.id}>{province.name}</MenuItem>
                        )
                    })}
                </Select>
                <p>Ciudad</p>
                <Select
                    labelId="city"
                    id="city"
                    onChange={handleCityChange}
                    size="small"
                    MenuProps={{ PaperProps: { sx: { maxHeight: 150 } } }}
                >
                    {regionSelected === undefined ? [] : cities.filter(cities => cities.province.id == provinceSelected).map((city) => {
                        return (
                            <MenuItem key={city.name} value={city.id}>{city.name}</MenuItem>
                        )
                    })}
                </Select>

                <div className="flex gap-5">
                    <TextField
                        label="Dirección"
                        variant="filled"
                        fullWidth
                        margin="normal"
                        sx={{ width: "50%" }}
                        onChange={handleStreetNameChange}

                    />
                    <TextField
                        label="Número de casa"
                        variant="filled"
                        fullWidth
                        margin="normal"
                        sx={{ width: "50%" }}
                        onChange={handleHouseNumberChange}

                    />
                </div>

                <div className="flex justify-end gap-5 mt-5">
                    <Button variant="contained" color="error" onClick={() => { router.push('/') }}>Cancelar</Button>
                    <Button variant="contained" color="inherit" style={{ background: "#0dce0d", color: "white" }} onClick={handleSubmit}>Crear</Button>
                </div>
            </FormGroup>
        </div>
    )
}