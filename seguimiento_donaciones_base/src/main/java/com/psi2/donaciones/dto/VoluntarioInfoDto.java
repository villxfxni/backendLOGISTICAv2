package com.psi2.donaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoluntarioInfoDto {

    private String nombre;
    private String apellido;
    private String telefono;
    private String area;
    private String informacionAdicional;
    private String fechaEstimadaLlegada;

} 