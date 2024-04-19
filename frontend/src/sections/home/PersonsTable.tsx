"use client";

import { Person } from "../../interfaces/Person";
import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow } from "@mui/material";
import { useState } from "react";


interface Column {
    id: string;
    label: string;
    minWidth?: number;
    format?: (value: number) => string;
}

const columns: Column[] = [
    { id: 'rut', label: 'RUT', minWidth: 100 },
    {
        id: 'firstName',
        label: 'Nombre',
        minWidth: 100,
        format: (value: number) => value.toLocaleString('es-ES'),
    },
    {
        id: 'lastName',
        label: 'Apellido',
        minWidth: 100,
        format: (value: number) => value.toLocaleString('es-ES'),
    },
    {
        id: 'gender',
        label: 'Genero',
        minWidth: 100,
        format: (value: number) => value.toFixed(2),
    },
    {
        id: 'civilStatus',
        label: 'Estado civil',
        minWidth: 100,
        format: (value: number) => value.toLocaleString('es-ES'),
    },
    {
        id: 'address.city.name',
        label: 'Ciudad',
        minWidth: 100,
        format: (value: number) => value.toLocaleString('es-ES'),
    },
    {
        id: 'address.city.province.region',
        label: 'Región',
        minWidth: 100,
        format: (value: number) => value.toLocaleString('es-ES'),
    },
];

interface Props {
    persons: Person[];
}

export function PersonsTable({ persons }: Props) {

    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [page, setPage] = useState(0);

    const handleChangePage = (event: unknown, newPage: number) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };


    return (
        <Paper sx={{ width: '100%' }}>
            <TableContainer sx={{ maxHeight: 440 }}>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead >
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                    align={"center"}
                                    style={{ top: 0, minWidth: column.minWidth }}
                                >
                                    {column.label}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>

                    <TableBody>
                        {persons
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((row: any) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.id}>
                                        <TableCell align="center">{row.rut}</TableCell>
                                        <TableCell align="center">{row.firstName}</TableCell>
                                        <TableCell align="center">{row.lastName}</TableCell>
                                        <TableCell align="center">{row.gender === 'MALE' ? 'Masculino' : row.gender === 'FEMALE' ? 'Femenino' : 'Otro'}</TableCell>
                                        <TableCell align="center">{row.civilStatus === 'UNMARRIED' ? 'Soltero/a' : 'Casado/a'}</TableCell>
                                        <TableCell align="center">{row.address.city.name}</TableCell>
                                        <TableCell align="center">{row.address.city.province.region.name}</TableCell>

                                    </TableRow>
                                );
                            })}
                    </TableBody>

                </Table>
            </TableContainer>
            <TablePagination
                translate="no"
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={persons.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
                labelDisplayedRows={
                    ({ from, to, count }) => {
                        return '' + from + '-' + to + ' de ' + count
                    }
                }
                labelRowsPerPage={"Filas por página:"}
            />
        </Paper>
    )
}