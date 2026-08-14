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



\## Ejemplo de consulta mediante TCP



El cliente puede conectarse al servidor utilizando el puerto `5000` y enviar una consulta al padrón electoral.



Ejemplo:



```text

123456789

