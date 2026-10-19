# Comprensión del diseño — Enunciado A
**Nombre:** Juan Esteban Fuentes Hernández
**Fecha:** 19 de octubre de 2026

Responde con tus palabras, basándote en `docs/UML-final.md`. Respuestas concisas (2–4 líneas por pregunta). Este documento evidencia que entendiste el contrato de diseño **antes** de programar.

---

### 1. Herencia y polimorfismo
¿Por qué `Sensor` es una clase abstracta y no una clase concreta? Menciona qué método obliga a cada subclase a implementar y qué gana el sistema con eso.

> **Respuesta:** `Sensor` es una clase abstracta porque representa un concepto genérico que no debe instanciarse directamente. Obliga a cada subclase a implementar los métodos abstractos `generarLectura()` y `fueraDeRango(int valor)`. El sistema gana polimorfismo, permitiendo iterar y procesar uniformemente arreglos o listas de distintos tipos de sensores (`SensorTemperatura`, `SensorHumedad`, `SensorLuminosidad`) sin importar su implementación específica.

---

### 2. Composición vs. agregación
Explica con tus palabras la diferencia entre `NodoIoT` ◆— `Sensor` (composición) y `CentroMonitoreo` ◇— `NodoIoT` (agregación). Da un ejemplo de qué pasaría con los sensores si se elimina el nodo, y qué pasaría con los nodos si se elimina el centro.

> **Respuesta:** En la composición, la vida del objeto contenido depende del contenedor: si se destruye el `NodoIoT`, sus objetos `Sensor` integrados también son destruidos. En la agregación, los objetos son independientes: si se elimina el `CentroMonitoreo`, los objetos `NodoIoT` siguen existiendo en el sistema de manera autónoma.

---

### 3. Asociación
En `TecnicoMantenimiento` — `NodoIoT`, ¿quién es dueño de quién? ¿Por qué se modela como N:M y no como composición?

> **Respuesta:** Ninguno es dueño del otro; es una asociación entre entidades con ciclos de vida independientes. Se modela como N:M porque un técnico puede revisar múltiples nodos y un nodo puede ser atendido por múltiples técnicos. No es composición porque eliminar a un técnico no implica destruir los nodos asignados ni viceversa.

---

### 4. Dependencia
¿Por qué `Lectura` **depende** de `ValidadorLectura` en lugar de **tenerlo** como atributo? ¿Qué diferencia práctica hay entre pasar un `ValidadorLectura` como parámetro versus guardarlo en un campo?

> **Respuesta:** `Lectura` únicamente necesita los servicios del validador al ejecutar el método `esValida()`. Pasarlo como parámetro reduce el acoplamiento y evita gastar memoria almacenando una referencia o instancia redundante de `ValidadorLectura` en cada objeto de tipo `Lectura`.

---

### 5. Listas
En este proyecto se usan dos estructuras de datos distintas:
- `int[] ultimasLecturas` en `Sensor`
- `ArrayList<Sensor>` en `NodoIoT`

Explica por qué en cada caso se eligió esa estructura y no la otra. ¿Qué ventaja tiene el `int[]` con buffer circular frente a un `ArrayList` para el historial de lecturas?

> **Respuesta:** En `Sensor` se usa `int[]` de tamaño fijo porque el historial tiene un límite máximo de N elementos numéricos primitivos; el buffer circular permite sobreescribir datos antiguos en O(1) sin consumo dinámico de memoria. En `NodoIoT` se usa `ArrayList<Sensor>` porque la cantidad de sensores agregados a un nodo es dinámica y no se conoce un tamaño fijo de antemano.
