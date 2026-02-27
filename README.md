Asunto: Entrega Microservicio Cuenta – Prueba Técnica

Hola, muy buenos días equipo de Devsu,

Comparto el segundo microservicio solicitado, correspondiente al módulo de cuentas y movimientos.

Este servicio gestiona la creación, actualización y consulta de cuentas, así como el registro de movimientos asociados.

Base de Datos

Siguiendo buenas prácticas de arquitectura de microservicios, se implementó una base de datos independiente para este servicio.

La base de datos se llama:

cuentaservicesdb (PostgreSQL)

Adjunto también el respaldo de la base de datos en formato .tar, lo que permite restaurarla fácilmente utilizando herramientas como DBeaver o cualquier IDE de administración de bases de datos.




La aplicación fue dockerizada para facilitar su despliegue y ejecución.

Comandos para su ejecución:

Construir la imagen:

docker build -t cuenta-service .

2) Crear y ejecutar el contenedor de la aplicación:

docker run -d -p 8082:8082 cuenta-service

3) Levantar la base de datos con Docker Compose:

docker-compose up --build


Pruebas de API

Se adjunta el archivo JSON con la colección de Postman para facilitar la validación de los endpoints del servicio.


Quedo atento a cualquier comentario o retroalimentación adicional.

Muchas gracias por la oportunidad.

Cordialmente,
Sergio Stiven Urbina Bayona
