## Configuración

Por defecto la aplicación usa las ceredenciales que están dentro de `application.properties`, estas mismas pueden ser remplazadas por otras y compilar el poryecto con esas credenciales, o en caso de que ya esté compilado el proyecto se puede agregar un `application.properties` en la misma carpeta donde está el JAR compilado y sobreescribir las credenciales de la base de datos.

## Inicialización

1. Clonar el repositorio `git clone https://github.com/Zethi/prueba-tecnica-azurian`
2. Navega hasta el directorio `prueba-tecnica-azurian/backend`.
3. Ejecutar el comando `mvn clean package` para construir.
4. Navegar hasta la carpeta `target/`
5. Iniciar la aplicación con `java -jar ./prueba-tecnica-azurian-1.0.0`