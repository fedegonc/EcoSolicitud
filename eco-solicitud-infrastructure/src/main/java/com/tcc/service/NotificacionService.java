package com.tcc.service;

import com.tcc.model.Notificacion;
import com.tcc.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificacionService {
    
    @Autowired
    private NotificacionRepository notificacionRepository;
    
    public void crear(String titulo, String mensaje, Long solicitudId) {
        Notificacion notif = new Notificacion(titulo, mensaje, solicitudId);
        notificacionRepository.save(notif);
    }
    
    public List<Notificacion> obtenerNoLeidas() {
        return notificacionRepository.findByLeidaFalseOrderByFechaCreacionDesc();
    }
    
    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAllByOrderByFechaCreacionDesc();
    }
    
    public void marcarComoLeida(Long id) {
        Notificacion notif = notificacionRepository.findById(id).orElse(null);
        if (notif != null) {
            notif.setLeida(true);
            notificacionRepository.save(notif);
        }
    }
    
    public long contarNoLeidas() {
        return notificacionRepository.findByLeidaFalseOrderByFechaCreacionDesc().size();
    }
}
