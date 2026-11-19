# TrackerPOO

Aplicación de escritorio en Java Swing para llevar el control de entrenamientos en el gimnasio. Permite registrar ejercicios de pesas, calistenia e isometría, calcular el volumen o tiempo total de trabajo, evaluar el rendimiento según el objetivo físico del deportista y guardar el historial en un archivo local.

## Autores
* Carlos Daniel Ramírez Perilla
* David Santiago Torres Quintero

Materia:Programación Orientada a Objetos I (Universidad Libre, Facultad de Ingeniería)  
Docente: Andrés Castaño

## Cómo funciona

Al abrir la aplicación, el usuario llena en la ventana los datos del ejercicio: fecha, modalidad (`Peso`, `Calistenia` o `Isometria`), nombre, series, RIR (repeticiones en reserva antes del fallo), carga o tiempo, meta física y notas opcionales.

Al presionar **Registrar y Calcular**, el programa ejecuta este recorrido:

1. **Validación y ensamblaje:** `ConstructorEntreno` revisa que la fecha y el nombre no estén vacíos y arma una `SesionEntrenamiento`. Si falta algún dato obligatorio, lanza `EntrenamientoInvalidoException` y muestra una alerta sin cerrar la ventana.
2. **Cálculo y diagnóstico:** Cada subclase de `Entreno` calcula su propio esfuerzo (kilos por series y repeticiones en pesas, peso corporal más lastre en calistenia, o segundos totales en isometría). Luego, la subclase elegida de `Objetivo` (`Volumen`, `PerderPeso` o `GanarPeso`) evalúa si se cumplió la meta.
3. **Notas y alertas:** `EntrenoConNotas` añade las observaciones escritas al resumen final sin alterar la sesión original. Si el volumen total llega a los 3000 kg, `GestorNotificaciones` avisa a `NotificadorAtleta`.
4. **Guardado en disco:** `RepositorioHistorial` agrega los datos al final del archivo `historial_entrenamientos.txt`.

## Requisitos y ejecución en Visual Studio Code

Necesitas tener instalado el **JDK 17** (o superior) y el **Extension Pack for Java** en Visual Studio Code.

1. Descarga el repositorio como ZIP y descomprímelo en tu equipo.
2. Abre la carpeta raíz del proyecto en **Visual Studio Code** (`Archivo > Abrir carpeta...`).
3. En el explorador de archivos izquierdo, entra a la carpeta `TakerPOO` y abre **`VentanaTracker.java`** (o `Main.java`).
4. Haz clic en el botón **Run** (el triángulo arriba a la derecha del editor o justo encima del método `main`) o presiona `F5`.
5. Si VS Code pide elegir qué clase principal ejecutar, selecciona **`VentanaTracker`** (o `Main`).

### Archivo de persistencia
No hace falta configurar motores de base de datos ni correr scripts externos. Al guardar la primera sesión desde la ventana, la aplicación crea automáticamente el archivo `historial_entrenamientos.txt` en la raíz del proyecto con este formato:

```text
SESION|2026-11-17|3200.0
PESO;Press banca;80.0;4;10;1
