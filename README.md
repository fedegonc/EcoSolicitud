# 🌿 EcoSolicitud - Sistema de Optimización de Rutas

Sistema de optimización de rutas para solicitudes de servicios ambientales, desarrollado con **TDD (Test-Driven Development)** para garantizar eficiencia y calidad.

## 📋 Características

- ✅ **Entidad Solicitud** con campos esenciales (id, descripción, lat, lng, completada)
- ✅ **Repositorio JPA** con H2 Database en memoria
- ✅ **Servicio de Optimización** con múltiples algoritmos
- ✅ **Tests TDD** con métricas de eficiencia
- ✅ **CLI** para validación en terminal

## 🏗️ Estructura del Proyecto

```
src/main/java/com/tcc/
    ├── model/          # Entidades JPA
    ├── repository/     # Repositorios Spring Data
    ├── service/        # Lógica de negocio
    └── cli/            # Interfaz de línea de comandos

src/test/java/com/tcc/
    └── service/        # Tests TDD
```

## 🚀 Tecnologías

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (en memoria)
- **JUnit 5** (para tests)
- **Maven**

## 📦 Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/EcoSolicitud.git
cd EcoSolicitud
```

2. **Compilar el proyecto**
```bash
mvn clean install
```

3. **Ejecutar tests**
```bash
mvn test
```

4. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

## 🧪 Tests TDD

El proyecto incluye tests completos que validan:

- ✅ Optimización básica de rutas
- ✅ Ordenamiento correcto por latitud
- ✅ Manejo de casos edge (lista vacía, un elemento)
- ✅ **Métricas de eficiencia** (< 5000ms para 1000 solicitudes)
- ✅ Escalabilidad (10,000 solicitudes)
- ✅ Cálculo de distancias euclidianas

### Ejecutar tests específicos

```bash
# Todos los tests
mvn test

# Solo tests de RutaService
mvn test -Dtest=RutaServiceTest

# Test específico de eficiencia
mvn test -Dtest=RutaServiceTest#testTiempoEjecucion
```

## 📊 Métricas de Eficiencia

El sistema cumple con los siguientes criterios:

- ⏱️ **Tiempo de ejecución**: < 5000ms para 1000 solicitudes
- 📈 **Escalabilidad**: < 10000ms para 10,000 solicitudes
- 🎯 **Precisión**: Algoritmo de ordenamiento por latitud
- 🔄 **Algoritmos disponibles**:
  - Ordenamiento simple por latitud (O(n log n))
  - Nearest Neighbor para rutas más complejas

## 🖥️ Uso del CLI

Al ejecutar la aplicación, el CLI muestra:

```
╔════════════════════════════════════════════════════════╗
║     🌿 EcoSolicitud - Optimizador de Rutas 🌿        ║
╚════════════════════════════════════════════════════════╝

📋 Total de solicitudes: 8
─────────────────────────────────────────────────────────

⚙️  Optimizando rutas...

✅ Rutas optimizadas:

  1. Reciclaje Barrio Sur → (8.70, 18.90)
  2. Recolección Sector Oeste → (9.20, 19.70)
  3. Recolección Parque Central → (10.50, 20.30)
  ...

─────────────────────────────────────────────────────────
📊 Métricas de Eficiencia:
   • Tiempo de ejecución: 15 ms
   • Distancia total: 25.43 unidades
   • Solicitudes procesadas: 8
   • Velocidad: 0.53 solicitudes/ms
─────────────────────────────────────────────────────────

✅ Criterio de eficiencia cumplido (< 5000ms)

🎯 Optimización completada exitosamente!
```

## 🔧 Configuración

### Base de datos H2

La configuración está en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:ecosolicitud
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
```

Para acceder a la consola H2: http://localhost:8080/h2-console

## 📈 Próximas Mejoras

- [ ] Algoritmo TSP (Traveling Salesman Problem) para optimización avanzada
- [ ] API REST para integración con frontend
- [ ] Persistencia en base de datos real (PostgreSQL)
- [ ] Visualización de rutas en mapa
- [ ] Métricas de consumo de combustible/tiempo

## 👨‍💻 Desarrollo con TDD

Este proyecto sigue el ciclo **Red-Green-Refactor**:

1. **Red**: Escribir test que falla
2. **Green**: Implementar código mínimo para pasar el test
3. **Refactor**: Mejorar el código manteniendo los tests verdes

## 📝 Licencia

MIT License

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

Desarrollado con ❤️ para el TCC - Sistema de Optimización de Rutas Ambientales
