<a name="readme-top"></a>

# Taller Grupal - Centro de Atención al Estudiante (CAE)

> Sistema que simula la atención de trámites estudiantiles en un Centro de Atención al Estudiante (CAE). Permite gestionar tickets por orden de llegada, registrar observaciones, cambiar estados, y aplicar acciones de rehacer y deshacer mediante estructuras de datos personalizadas.

---

## Built With

* Java (JDK 24+)
* Estructuras de datos personalizadas (Cola, Pila, Lista enlazada)

---

## Repositorio

[Repositorio en GitHub](https://github.com/Alex-Francis-07/TallerGrupal-CAE-)

---

## Getting Started

Para obtener una copia local del proyecto y ejecutarlo, sigue estos pasos:

### Prerequisitos

* JDK 24+
* Git instalado

### Instalación

1.  Clona el repositorio:
    ```sh
    git clone (https://github.com/Alex-Francis-07/TallerGrupal-CAE-.git)
    ```
2.  Abre el proyecto en tu editor Java preferido (como IntelliJ).
3.  Ejecuta la clase `Main.java` para iniciar el sistema.

### Uso

1.  Ejecuta `Main.java`.
2.  Usa el menú por consola para:
    * Recibir tickets
    * Atender casos (el ticket anterior se marca automáticamente como COMPLETADO)
    * Agregar notas
    * Cambiar estados
    * Deshacer/Rehacer acciones
    * Consultar historial y cola
    * Exportar tickets a archivo
    * Ver Top-K tickets con más notas

---

## Decisiones de diseño 

Se implementaron estructuras de datos personalizadas que son:
* **Cola:** para almacenar los casos en espera y atenderlos en orden de llegada (FIFO).
* **Pila:** para undo/redo: registrar acciones de la sesión de atención para poder deshacer y rehacer cambios.
* **Lista Enlazada Simple de notas por caso:** insertar al inicio, eliminar la primera coincidencia y recorrer para listar.
* **Persistencia:** los tickets se guardan en archivo y se restauran al iniciar el sistema, incluyendo el ticket en atención.

---


## Casos Borde

* **Atender sin tickets en la cola**

**Situación:** Se intenta ejecutar atenderTicket() cuando la cola está vacía.  
**Comportamiento esperado:** Se muestra el mensaje “No hay tickets en la cola” y no ocurre ningún cambio en el sistema.

* **Agregar nota vacía o nula**

**Situación:** El usuario ingresa una cadena vacía al agregar nota.  
**Comportamiento esperado:** El sistema muestra un mensaje de error indicando que la nota no puede estar vacía.

* **Mostrar historial de tickets**

**Situación:** Se selecciona la opción de mostrar historial.  
**Comportamiento esperado:** Se muestra el historial del ticket en atención. Si no hay ninguno, se muestra el mensaje "no hay ticket en atención".

* **Restaurar ticket en atención al iniciar**

**Situación:** El sistema se cerró con un ticket en estado EN_ATENCION.  
**Comportamiento esperado:** Al reiniciar, el ticket se restaura automáticamente como ticket en atención.

* **Archivo de tickets vacío o malformado**

**Situación:** El archivo `tickets.txt` está vacío o tiene líneas incompletas.  
**Comportamiento esperado:** El sistema ignora las líneas inválidas y continúa sin lanzar errores.

---


## Componentes principales

* **Ticket:** representa un trámite con ID, estado, tipo, descripción y notas.
* **Nota:** observación textual agregada al ticket.
* **PilaUR:** núcleo del sistema, gestiona la lógica de atención y acciones reversibles.
* **Domain:** capa intermedia que expone operaciones para la interfaz.
* **Persistencia:** guarda y carga los tickets desde archivo.
* **Main:** menú interactivo por consola para simular el flujo de atención.

---


## Estados posibles de un ticket

* **EN_COLA:** recién recibido  
* **EN_ATENCION:** en proceso de atención  
* **EN_PROCESO:** trámite en curso  
* **PENDIENTE_DOCS:** falta documentación  
* **COMPLETADO:** atención finalizada

---


## Autores

**Alex Sigcho**
* GitHub: [@Alex-Francis-07](https://github.com/Alex-Francis-07)

**Julián Vega**
* GitHub: [@Servio-Julian-Vega-Jimenez](https://github.com/ServioVega)

**Mark Gonzales**
* GitHub: [@mark777](https://github.com/Mark777g)

**Darwin Jimbo**
* GitHub: [@Darwin-J5](https://github.com/Darwin-J5)

---

## Contribuciones

¡Las contribuciones, sugerencias y mejoras son bienvenidas!

---

## Apóyanos

Dale una estrella al repositorio si te gustó el proyecto o te fue útil.
