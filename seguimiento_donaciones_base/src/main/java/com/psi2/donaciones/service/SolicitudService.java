package com.psi2.donaciones.service;

import com.psi2.donaciones.dto.SolicitudConPersonalDto;
import com.psi2.donaciones.dto.SolicitudDonacionDto;
import com.psi2.donaciones.dto.SolicitudDto;
import com.psi2.donaciones.dto.SolicitudListaDto;
import com.psi2.donaciones.dto.VoluntarioInfoDto;

import java.util.List;
import java.util.Map;

public interface SolicitudService {
    List<SolicitudDto> getAllSolicitudes();
    long totalSolicitudes();
    double calcularTiempoPromedioAprobacion();
    Map<String, Integer> obtenerTop5ProductosMasSolicitados();
    Map<String, Integer> obtenerSolicitudesPorProvincia();
    Map<String, Integer> obtenerSolicitudesPorMes();
    List<SolicitudListaDto> obtenerSolicitudesCompletas();
    List<SolicitudDto> getSolicitudesAprobadas();
    List<SolicitudDonacionDto> obtenerSolicitudesConDonacionesPendientes();
    List<SolicitudConPersonalDto> getSolicitudesConPersonal();
    void aceptarAyuda(Integer idSolicitud);
    void enviarInfoVoluntarioAlSolicitante(Integer idSolicitud, VoluntarioInfoDto voluntarioInfo);

}
