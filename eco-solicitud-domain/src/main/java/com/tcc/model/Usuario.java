package com.tcc.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String email;
    private String password;
    private String authProvider; // "google" o "local"
    private String googleId;

    @OneToMany(mappedBy = "usuario")
    private List<Solicitud> solicitudes = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Usuario(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Usuario(String nombre, String email, String password, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.authProvider = "local";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAuthProvider() { return authProvider; }
    public void setAuthProvider(String authProvider) { this.authProvider = authProvider; }

    public String getGoogleId() { return googleId; }
    public void setGoogleId(String googleId) { this.googleId = googleId; }

    public List<Solicitud> getSolicitudes() { return solicitudes; }
    public void setSolicitudes(List<Solicitud> solicitudes) { this.solicitudes = solicitudes; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", authProvider='" + authProvider + '\'' +
                '}';
    }
}
