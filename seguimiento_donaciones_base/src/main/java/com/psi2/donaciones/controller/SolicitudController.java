package com.psi2.donaciones.controller;

import com.psi2.donaciones.dto.SolicitudConPersonalDto;
import com.psi2.donaciones.dto.SolicitudDonacionDto;
import com.psi2.donaciones.dto.SolicitudDto;
import com.psi2.donaciones.dto.SolicitudListaDto;
import com.psi2.donaciones.dto.VoluntarioInfoDto;
import com.psi2.donaciones.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/solicitudes")
@CrossOrigin("*")
@Slf4j
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;


    @GetMapping
    public ResponseEntity<List<SolicitudDto>> getAllSolicitudes() {
        List<SolicitudDto> solicitudes = solicitudService.getAllSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/aprobadas/almacen")
    public ResponseEntity<List<SolicitudDonacionDto>> obtenerSolicitudesConDonacionesPendientes() {
        List<SolicitudDonacionDto> resultado = solicitudService.obtenerSolicitudesConDonacionesPendientes();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/apoyo")
    public ResponseEntity<List<SolicitudConPersonalDto>> obtenerSolicitudesConPersonal() {
        List<SolicitudConPersonalDto> resultado = solicitudService.getSolicitudesConPersonal();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<SolicitudListaDto>> obtenerResumenSolicitudes() {
        try{
        List<SolicitudListaDto> resultado = solicitudService.obtenerSolicitudesCompletas();
        return ResponseEntity.ok(resultado);
        }
        catch (Exception e) {
            log.error("/solicitudes/resumen failed: {}", org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage(e), e);
        throw e;
        }
    }

    @GetMapping("/aprobadas")
    public ResponseEntity<List<SolicitudDto>> obtenerSolicitudesAprobadas() {
        List<SolicitudDto> aprobadas = solicitudService.getSolicitudesAprobadas();
        return ResponseEntity.ok(aprobadas);
    }

    @PostMapping("/aceptar-ayuda/{id}")
    public ResponseEntity<String> aceptarAyuda(@PathVariable Integer id) {
        try {
            solicitudService.aceptarAyuda(id);
            return ResponseEntity.ok("Ayuda aceptada exitosamente para la solicitud ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping("/enviar-info-voluntario/{id}")
    public ResponseEntity<String> enviarInfoVoluntario(@PathVariable Integer id, 
                                                      @RequestBody VoluntarioInfoDto voluntarioInfo) {
        try {
            solicitudService.enviarInfoVoluntarioAlSolicitante(id, voluntarioInfo);
            return ResponseEntity.ok("Información del voluntario enviada exitosamente al solicitante de la solicitud ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }

}
