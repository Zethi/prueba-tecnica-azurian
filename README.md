## Configuración

Dentro del archivo `.env` se puede configurar las credenciales y base de datos que utilizará la MariaDB, no se recomienda editar a menos que se vaya a cambiar en todos los lugares.

## Inicialización usando docker

1. Compilar el Backend utilizando las instrucciones que están en el `README` del proyecto.
2. Utilizar el comando `docker compose up` o `docker compose up -d` para abrirlo en segundo plano.

los servicios se levantarán con los siguientes puertos:

- `3306` Para la MariaDB
- `3000` Para el Frontend
- `8080` Para el Backend
