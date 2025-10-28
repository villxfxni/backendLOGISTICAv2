package com.psi2.donaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlmacenDto {
    private Integer id_almacen;
    private String nombre_almacen;
    private String ubicacion;
    private Double latitud;
    private Double longitud;
}
