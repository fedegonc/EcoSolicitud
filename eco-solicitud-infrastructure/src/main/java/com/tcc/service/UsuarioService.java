package com.tcc.service;

import com.tcc.model.Usuario;
import com.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Usuario registrar(String nombre, String email, String password, String telefono) {
        // Verificar si el email ya existe
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        Usuario usuario = new Usuario(nombre, email, passwordEncoder.encode(password), telefono);
        return usuarioRepository.save(usuario);
    }
    
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public Optional<Usuario> buscarPorGoogleId(String googleId) {
        return usuarioRepository.findByGoogleId(googleId);
    }
    
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
