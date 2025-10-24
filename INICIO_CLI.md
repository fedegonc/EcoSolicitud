# 🚀 Inicio Rápido - CLI de EcoSolicitud

## Forma más fácil de ejecutar

### Windows (Opción 1 - Script Batch)
```bash
ejecutar-cli.bat
```

### Windows (Opción 2 - PowerShell)
```powershell
.\ejecutar-cli.ps1
```

### Forma Manual
1. Abre `eco-solicitud-infrastructure\src\main\resources\application.properties`
2. Cambia `app.cli.enabled=false` a `app.cli.enabled=true`
3. Ejecuta:
   ```bash
   mvn spring-boot:run -pl eco-solicitud-infrastructure
   ```

## 📝 Flujo de Prueba Recomendado

### Paso 1: Crear algunas solicitudes
```
Opción: 1
Descripción: Recolección de plásticos
Latitud: -30.9064
Longitud: -55.5508
Entidad: Entidad Recicladora 1
```

### Paso 2: Ver la lista
```
Opción: 2
```

### Paso 3: Actualizar estado de una solicitud
```
Opción: 4
ID: 1
Nuevo estado: 2 (ACEPTADA)
```

### Paso 4: Ver estadísticas
```
Opción: 10
```

### Paso 5: Buscar por ID
```
Opción: 3
ID: 1
```

### Paso 6: Listar por estado
```
Opción: 7
Estado: 2 (ACEPTADA)
```

## 🎯 Menú Completo

```
1.  Crear Solicitud                    → Crea nueva solicitud
2.  Listar Solicitudes                 → Muestra todas en tabla
3.  Buscar Solicitud por ID            → Busca una específica
4.  Actualizar Estado de Solicitud     → Cambia estado (RECIBIDA→ACEPTADA→etc)
5.  Eliminar Solicitud                 → Borra una solicitud
6.  Contar Solicitudes por Estado      → Resumen de cantidades
7.  Listar Solicitudes por Estado      → Filtra por estado
8.  Actualizar Descripción             → Modifica descripción
9.  Actualizar Ubicación               → Modifica lat/lng
10. Estadísticas Generales             → Vista completa del sistema
0.  Salir                              → Cierra el menú
```

## ⚡ Atajos de Teclado

- Números del 0-10 para seleccionar opciones
- Enter para confirmar
- S/N para confirmaciones

## 🔍 Ejemplo Completo de Sesión

```
╔════════════════════════════════════════════════╗
║       ECOSOLICITUD - MENÚ PRINCIPAL            ║
╚════════════════════════════════════════════════╝
Seleccione una opción: 1

═══ CREAR NUEVA SOLICITUD ═══
Descripción: Recolección de cartón
Latitud: -30.9064
Longitud: -55.5508
Entidad: Recicladora Norte

✅ Solicitud creada exitosamente con ID: 1

Seleccione una opción: 2

═══ LISTA DE SOLICITUDES ═══
┌──────┬─────────────────────────────┬──────────────────────┬─────────────────────┬─────────────┐
│  ID  │       Descripción           │     Ubicación        │  Entidad Recol.     │   Estado    │
├──────┼─────────────────────────────┼──────────────────────┼─────────────────────┼─────────────┤
│ 1    │ Recolección de cartón       │ -30.9064, -55.5508   │ Recicladora Norte   │ RECIBIDA    │
└──────┴─────────────────────────────┴──────────────────────┴─────────────────────┴─────────────┘
Total: 1 solicitud(es)

Seleccione una opción: 4

═══ ACTUALIZAR ESTADO ═══
Ingrese el ID: 1
Estado actual: RECIBIDA

Estados disponibles:
1. RECIBIDA
2. ACEPTADA
3. RECHAZADA
4. COMPLETADA
Seleccione nuevo estado: 2

✅ Estado actualizado a: ACEPTADA

Seleccione una opción: 10

═══ ESTADÍSTICAS GENERALES ═══
Total de solicitudes: 1

Por estado:
  RECIBIDA    : 0 (0.0%)
  ACEPTADA    : 1 (100.0%)
  RECHAZADA   : 0 (0.0%)
  COMPLETADA  : 0 (0.0%)

Solicitudes completadas: 0

Seleccione una opción: 0

¡Hasta luego!
```

## 💡 Tips

1. **Primero crea datos**: Usa la opción 1 varias veces para tener datos de prueba
2. **Prueba el ciclo completo**: Crea → Lista → Actualiza Estado → Estadísticas
3. **Usa IDs reales**: Cuando busques o modifiques, usa los IDs que viste en la lista
4. **Verifica cambios**: Después de cada modificación, usa la opción 2 para ver los cambios

## 🐛 Solución de Problemas

### El menú no aparece
- Verifica que `app.cli.enabled=true` en `application.properties`
- Asegúrate de ejecutar desde la raíz del proyecto

### Error al compilar
```bash
mvn clean install -DskipTests
```

### La aplicación web interfiere
- El CLI y la web pueden correr juntos
- El CLI solo aparece si `app.cli.enabled=true`
