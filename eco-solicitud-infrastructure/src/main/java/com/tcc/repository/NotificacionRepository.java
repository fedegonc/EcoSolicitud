package com.tcc.repository;

import com.tcc.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByLeidaFalseOrderByFechaCreacionDesc();
    List<Notificacion> findAllByOrderByFechaCreacionDesc();
}
