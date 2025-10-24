# ⚡ Inicio Rápido - EcoSolicitud

## 🚀 En 3 Pasos

### 1️⃣ Compilar
```bash
mvn clean install
```

### 2️⃣ Ejecutar Tests
```bash
mvn test
```

### 3️⃣ Ejecutar Aplicación
```bash
mvn spring-boot:run
```

## ✅ Resultado Esperado

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
   • Tiempo de ejecución: 4 ms
   • Distancia total: 10.78 unidades
   • Solicitudes procesadas: 8

✅ Criterio de eficiencia cumplido (< 5000ms)
🎯 Optimización completada exitosamente!
```

## 📚 Documentación

| Archivo | Descripción |
|---------|-------------|
| `README.md` | 📖 Documentación completa |
| `COMANDOS.md` | 💻 Lista de comandos Maven |
| `EJEMPLOS.md` | 💡 Ejemplos de código |
| `TDD_APPROACH.md` | 🧪 Explicación del TDD |
| `ESTRUCTURA.md` | 📁 Estructura del proyecto |
| `RESUMEN_TCC.md` | 📊 Resumen ejecutivo |

## 🧪 Tests Incluidos

- ✅ `testOptimizarRutasSimple()` - Test básico
- ✅ `testOptimizarRutasOrdenamiento()` - Validación de orden
- ✅ `testOptimizarRutasVacia()` - Caso edge: lista vacía
- ✅ `testOptimizarRutasUnaUnica()` - Caso edge: un elemento
- ✅ `testTiempoEjecucion()` - Eficiencia 1000 items
- ✅ `testTiempoEjecucionGranEscala()` - Eficiencia 10k items
- ✅ `testOptimizacionNoModificaOriginal()` - Integridad
- ✅ `testDistanciaCalculada()` - Cálculo correcto

## 🎯 Requisitos

- ☕ **Java 17** o superior
- 📦 **Maven 3.6+**
- 💾 **2GB RAM** mínimo
- 💿 **100MB** espacio en disco

## 🔧 Verificar Instalación

```bash
# Verificar Java
java -version
# Debe mostrar: java version "17.x.x"

# Verificar Maven
mvn -version
# Debe mostrar: Apache Maven 3.x.x
```

## 🐛 Solución de Problemas

### Error: "JAVA_HOME not set"
```bash
# Windows
set JAVA_HOME=C:\Program Files\Java\jdk-17

# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-17
```

### Error: "Maven not found"
```bash
# Instalar Maven
# Windows: Descargar de https://maven.apache.org
# Linux: sudo apt install maven
# Mac: brew install maven
```

### Error en tests
```bash
# Limpiar y recompilar
mvn clean install -U
```

## 📞 Ayuda

Si tienes problemas:
1. Revisa `README.md` para documentación completa
2. Verifica que Java 17 y Maven estén instalados
3. Ejecuta `mvn clean install` para recompilar

## 🎓 Para el TCC

Este proyecto demuestra:
- ✅ Desarrollo con TDD
- ✅ Arquitectura en capas
- ✅ Tests con métricas de eficiencia
- ✅ Documentación completa
- ✅ Código limpio y mantenible

---

**¡Listo para usar!** 🚀
