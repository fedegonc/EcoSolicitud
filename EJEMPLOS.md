# 💡 Ejemplos de Uso - EcoSolicitud

## 🚀 Ejecución Básica

### 1. Ejecutar la aplicación completa
```bash
mvn spring-boot:run
```

**Salida esperada:**
```
╔════════════════════════════════════════════════════════╗
║     🌿 EcoSolicitud - Optimizador de Rutas 🌿        ║
╚════════════════════════════════════════════════════════╝

📋 Total de solicitudes: 8
⚙️  Optimizando rutas...

✅ Rutas optimizadas:
  1. Reciclaje Barrio Sur → (8.70, 18.90)
  2. Recolección Sector Oeste → (9.20, 19.70)
  ...

📊 Métricas de Eficiencia:
   • Tiempo de ejecución: 7 ms
   • Distancia total: 10.78 unidades
```

### 2. Ejecutar solo tests
```bash
mvn test
```

**Salida esperada:**
```
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## 📝 Uso Programático

### Ejemplo 1: Optimizar lista de solicitudes

```java
@Autowired
private RutaService rutaService;

public void optimizarMisSolicitudes() {
    // Crear solicitudes
    List<Solicitud> solicitudes = List.of(
        new Solicitud("Punto A", 10.5, 20.3),
        new Solicitud("Punto B", 15.2, 25.8),
        new Solicitud("Punto C", 8.7, 18.9)
    );
    
    // Optimizar
    List<Solicitud> rutaOptima = rutaService.optimizarRutas(solicitudes);
    
    // Usar resultado
    rutaOptima.forEach(s -> 
        System.out.println(s.getDescripcion() + " en " + s.getLat() + "," + s.getLng())
    );
}
```

### Ejemplo 2: Calcular distancia entre puntos

```java
@Autowired
private RutaService rutaService;

public void calcularDistancias() {
    Solicitud origen = new Solicitud("Origen", 0, 0);
    Solicitud destino = new Solicitud("Destino", 3, 4);
    
    double distancia = rutaService.calcularDistancia(origen, destino);
    System.out.println("Distancia: " + distancia); // Output: 5.0
}
```

### Ejemplo 3: Calcular distancia total de ruta

```java
@Autowired
private RutaService rutaService;

public void calcularRutaTotal() {
    List<Solicitud> ruta = rutaService.optimizarRutas(solicitudes);
    double distanciaTotal = rutaService.calcularDistanciaTotal(ruta);
    
    System.out.println("Distancia total: " + distanciaTotal + " unidades");
}
```

### Ejemplo 4: Usar algoritmo Nearest Neighbor

```java
@Autowired
private RutaService rutaService;

public void usarNearestNeighbor() {
    Solicitud puntoInicio = new Solicitud("Base", 0, 0);
    List<Solicitud> solicitudes = cargarSolicitudes();
    
    List<Solicitud> rutaOptima = rutaService.optimizarRutasNearestNeighbor(
        solicitudes, 
        puntoInicio
    );
    
    System.out.println("Ruta optimizada desde base:");
    rutaOptima.forEach(System.out::println);
}
```

## 🗄️ Uso con Base de Datos

### Ejemplo 5: Guardar y recuperar solicitudes

```java
@Autowired
private SolicitudRepository solicitudRepo;

@Autowired
private RutaService rutaService;

public void trabajarConBD() {
    // Guardar solicitudes
    Solicitud s1 = new Solicitud("Tarea 1", 10, 20);
    Solicitud s2 = new Solicitud("Tarea 2", 15, 25);
    solicitudRepo.saveAll(List.of(s1, s2));
    
    // Recuperar todas
    List<Solicitud> todas = solicitudRepo.findAll();
    
    // Optimizar
    List<Solicitud> optimizadas = rutaService.optimizarRutas(todas);
    
    // Marcar como completadas
    optimizadas.forEach(s -> {
        s.setCompletada(true);
        solicitudRepo.save(s);
    });
}
```

## 🧪 Ejemplos de Tests

### Ejemplo 6: Test personalizado

```java
@SpringBootTest
class MiTest {
    
    @Autowired
    RutaService rutaService;
    
    @Test
    void testMiCasoEspecifico() {
        // Arrange
        List<Solicitud> misSolicitudes = List.of(
            new Solicitud("A", 5, 10),
            new Solicitud("B", 3, 8),
            new Solicitud("C", 7, 12)
        );
        
        // Act
        List<Solicitud> resultado = rutaService.optimizarRutas(misSolicitudes);
        
        // Assert
        assertEquals("B", resultado.get(0).getDescripcion());
        assertEquals("A", resultado.get(1).getDescripcion());
        assertEquals("C", resultado.get(2).getDescripcion());
    }
}
```

### Ejemplo 7: Test de performance personalizado

```java
@Test
void testMiCriterioDePerformance() {
    List<Solicitud> muchasSolicitudes = generarSolicitudes(5000);
    
    long inicio = System.currentTimeMillis();
    rutaService.optimizarRutas(muchasSolicitudes);
    long fin = System.currentTimeMillis();
    
    long duracion = fin - inicio;
    System.out.println("Tiempo: " + duracion + "ms");
    
    assertTrue(duracion < 3000, "Debe procesar 5000 en menos de 3s");
}
```

## 🎯 Casos de Uso Reales

### Caso 1: Optimizar ruta de recolección diaria

```java
@Service
public class RecoleccionService {
    
    @Autowired
    private RutaService rutaService;
    
    @Autowired
    private SolicitudRepository solicitudRepo;
    
    public List<Solicitud> planificarRecoleccionDiaria() {
        // Obtener solicitudes pendientes
        List<Solicitud> pendientes = solicitudRepo.findAll()
            .stream()
            .filter(s -> !s.isCompletada())
            .toList();
        
        // Optimizar ruta
        List<Solicitud> rutaOptima = rutaService.optimizarRutas(pendientes);
        
        // Calcular métricas
        double distanciaTotal = rutaService.calcularDistanciaTotal(rutaOptima);
        System.out.println("Ruta diaria: " + distanciaTotal + " km");
        
        return rutaOptima;
    }
}
```

### Caso 2: Comparar algoritmos

```java
public void compararAlgoritmos() {
    List<Solicitud> solicitudes = cargarSolicitudes();
    Solicitud base = new Solicitud("Base", 0, 0);
    
    // Algoritmo 1: Ordenamiento simple
    long t1 = System.currentTimeMillis();
    List<Solicitud> ruta1 = rutaService.optimizarRutas(solicitudes);
    long tiempo1 = System.currentTimeMillis() - t1;
    double dist1 = rutaService.calcularDistanciaTotal(ruta1);
    
    // Algoritmo 2: Nearest Neighbor
    long t2 = System.currentTimeMillis();
    List<Solicitud> ruta2 = rutaService.optimizarRutasNearestNeighbor(solicitudes, base);
    long tiempo2 = System.currentTimeMillis() - t2;
    double dist2 = rutaService.calcularDistanciaTotal(ruta2);
    
    System.out.println("Ordenamiento: " + tiempo1 + "ms, " + dist1 + " unidades");
    System.out.println("Nearest Neighbor: " + tiempo2 + "ms, " + dist2 + " unidades");
}
```

### Caso 3: Generar reporte de eficiencia

```java
public void generarReporte() {
    List<Solicitud> solicitudes = solicitudRepo.findAll();
    List<Solicitud> rutaOptima = rutaService.optimizarRutas(solicitudes);
    
    System.out.println("=== REPORTE DE OPTIMIZACIÓN ===");
    System.out.println("Total solicitudes: " + solicitudes.size());
    System.out.println("Distancia total: " + 
        rutaService.calcularDistanciaTotal(rutaOptima));
    System.out.println("Orden de visita:");
    
    for (int i = 0; i < rutaOptima.size(); i++) {
        Solicitud s = rutaOptima.get(i);
        System.out.printf("%d. %s (%.2f, %.2f)\n", 
            i + 1, s.getDescripcion(), s.getLat(), s.getLng());
    }
}
```

## 🔧 Configuración Personalizada

### Cambiar criterio de ordenamiento

```java
// En RutaService.java, modificar el Comparator:

// Por latitud (actual)
.sorted(Comparator.comparingDouble(Solicitud::getLat))

// Por longitud
.sorted(Comparator.comparingDouble(Solicitud::getLng))

// Por distancia desde origen
.sorted(Comparator.comparingDouble(s -> 
    calcularDistancia(origen, s)))
```

## 📊 Visualización de Resultados

### Exportar a CSV

```java
public void exportarRutaCSV(List<Solicitud> ruta, String archivo) {
    try (PrintWriter writer = new PrintWriter(archivo)) {
        writer.println("Orden,Descripcion,Latitud,Longitud");
        for (int i = 0; i < ruta.size(); i++) {
            Solicitud s = ruta.get(i);
            writer.printf("%d,%s,%.2f,%.2f\n", 
                i + 1, s.getDescripcion(), s.getLat(), s.getLng());
        }
    }
}
```

---

**Nota**: Todos estos ejemplos asumen que tienes las dependencias inyectadas correctamente con `@Autowired` y que estás dentro de un contexto Spring.
