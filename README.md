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
    * Atender casos
    * Agregar notas
    * Cambiar estados
    * Deshacer/Rehacer acciones
    * Consultar historial y cola

---

## Desiciones de diseño 

Se implementaron estructuras de datos personalizadas que son:
* **Cola:** para almacenar los casos en espera y atenderlos en orden de llegada (FIFO).
* **Pila:** para undo/redo: registrar acciones de la sesión de atención para poder deshacer y rehacer cambios.
* **Lista Enlazada Simple de notas por caso:** insertar al inicio, eliminar la primera coincidencia y recorrer para listar.

---

## Casos Borde

* **Atender sin tickets en la cola**
**Situación:** Se intenta ejecutar atenderTicket() cuando Cola está vacía.

**Comportamiento esperado:** Se muestra el mensaje “La cola está vacía” y no ocurre ningún cambio en el sistema.

* **Agregar nota vacía o nula**
**Situación** El usuario ingresa una cadena vacía al agregar nota.

**Comportamiento esperado:** La clase Nota lanza una excepción controlada IllegalArgumentException indicando que el contenido no puede ser vacío.



---

## Componentes principales

* **Ticket:** representa un trámite con ID, estado y historial de notas.
* **Nota:** observación textual agregada al ticket.
* **PilaUR:** núcleo del sistema, gestiona la lógica de atención y acciones reversibles.
* **Domain:** capa intermedia que expone operaciones para la interfaz.
* **Main:** menú interactivo por consola para simular el flujo de atención

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

**Cristhian Davila**
* GitHub: [@cristhiandavila1938](https://github.com/cristhiandavila1938)

---

## Contribuciones

¡Las contribuciones, sugerencias y mejoras son bienvenidas!

---

## Apóyanos

Dale una estrella al repositorio si te gustó el proyecto o te fue útil.
