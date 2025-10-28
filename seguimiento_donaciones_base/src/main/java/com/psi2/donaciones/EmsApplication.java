package com.psi2.donaciones;

import com.psi2.donaciones.config.service.EmailService;
import com.psi2.donaciones.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.util.TimeZone;
// ✨ ADD THESE:
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.psi2.donaciones.repository.DestinoRepository;
import com.psi2.donaciones.repository.DonacionRepository;
import com.psi2.donaciones.repository.HistorialSeguimientoDonacionesRepository;
import com.psi2.donaciones.repository.SolicitanteRepository;
import com.psi2.donaciones.repository.SolicitudRepository;
import com.psi2.donaciones.repository.UbicacionRepository;
import com.psi2.donaciones.repository.UsuarioRepository;

import com.psi2.donaciones.repository.MetricasRepository;
import com.psi2.donaciones.repository.NotificacionesRepository;
import com.psi2.donaciones.repository.SeguimientoDonacionRepository;
import com.psi2.donaciones.repository.SolicitudesSinResponderRepository;

@SpringBootApplication
@EnableScheduling

@EnableJpaRepositories(basePackageClasses = {
    DestinoRepository.class,
    DonacionRepository.class,
    HistorialSeguimientoDonacionesRepository.class,
    SolicitanteRepository.class,
    SolicitudRepository.class,
    UbicacionRepository.class,
    UsuarioRepository.class
})
@EnableMongoRepositories(basePackageClasses = {
    MetricasRepository.class,
    NotificacionesRepository.class,
    SeguimientoDonacionRepository.class,
    SolicitudesSinResponderRepository.class
})

@EntityScan(basePackages = "com.psi2.donaciones.entities.entitySQL")
public class EmsApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("America/La_Paz"));

        var context = SpringApplication.run(EmsApplication.class, args);

    }
}
