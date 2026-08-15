\# Servidor de Padrón Electoral



Proyecto desarrollado en Java que implementa un servidor para realizar consultas al padrón electoral de Costa Rica mediante conexiones TCP y HTTP.



\## Ejecución del servidor



El proyecto puede ejecutarse desde NetBeans.



1\. Abrir el proyecto `Servidor Padron` en NetBeans.

2\. Compilar el proyecto.

3\. Ejecutar la clase principal `App.java`.

4\. El servidor iniciará los servicios TCP y HTTP.

5\. Una vez iniciado, estará listo para recibir consultas.



\## Puertos utilizados



El servidor utiliza los siguientes puertos:



\- TCP: `5000`

\- HTTP: `8080`



\## Arquitectura del proyecto



El proyecto está organizado en diferentes paquetes para separar las responsabilidades de la aplicación.



\- `app`: contiene la clase principal encargada de iniciar la aplicación.

\- `Presentacion`: contiene los servidores TCP y HTTP.

\- `Logica`: contiene la lógica utilizada para procesar las consultas al padrón.

\- `Repositorio`: contiene las clases encargadas del acceso y búsqueda de información.

\- `entidades`: contiene las entidades utilizadas por el sistema, como Persona y Distrito Electoral.

\- `dto`: contiene los objetos utilizados para transferir los datos de las respuestas.

\- `utilidades`: contiene clases auxiliares para configuración y manejo de JSON.



Esta organización permite separar la presentación, la lógica y el acceso a los datos.



## Ejemplo de consulta mediante TCP

El cliente puede conectarse al servidor utilizando el puerto `5000` y enviar una consulta utilizando el siguiente formato:

```text
GET|703250014
```

Si la persona existe en el padrón, el servidor devuelve la información en formato JSON.

Ejemplo de respuesta:

```json
{
  "cedula": "703250014",
  "nombre": "JERSON ALFREDO",
  "primerApellido": "WYNTER",
  "segundoApellido": "WILLIAMSON",
  "codigoElectoral": "701019",
  "provincia": "LIMON",
  "canton": "CENTRAL",
  "distrito": "SANTA EDVIGIS"
}
```

## Ejemplo de consulta mediante HTTP

El servidor HTTP utiliza el puerto `8080`.

La consulta se realiza mediante una solicitud GET utilizando la siguiente ruta:

```text
http://localhost:8080/padron/703250014
```

Si la persona existe, el servidor devuelve sus datos en formato JSON.

## Manejo de errores

El servidor devuelve respuestas de error en formato JSON cuando una solicitud no puede ser procesada correctamente.

Ejemplo de una cédula inexistente:

```json
{
  "error": true,
  "codigo": 404,
  "mensaje": "No se encontro una persona con la cedula indicada."
}
```

También se manejan solicitudes con formato incorrecto, rutas inexistentes y métodos HTTP no permitidos.

Ejemplo de solicitud TCP inválida:

```json
{
  "error": true,
  "codigo": 400,
  "mensaje": "Solicitud TCP incompleta o invalida."
}
```

Ejemplo de método HTTP no permitido:

```json
{
  "error": true,
  "codigo": 405,
  "mensaje": "Metodo HTTP no permitido."
}
```

## Concurrencia

El servidor TCP utiliza un pool de 10 hilos para atender las conexiones de los clientes.

Esto permite procesar múltiples solicitudes sin detener el funcionamiento del servidor.

## Formato de respuesta

Las respuestas del servidor se generan en formato JSON.

Cuando una persona es encontrada se devuelve la información correspondiente a:

- Cédula
- Nombre
- Primer apellido
- Segundo apellido
- Código electoral
- Provincia
- Cantón
- Distrito

Cuando ocurre un error se devuelve un objeto que contiene:

- `error`
- `codigo`
- `mensaje`

## Tecnologías utilizadas

- Java
- Apache NetBeans
- TCP Sockets
- HTTP Server
- JSON
- Git
- GitHub
