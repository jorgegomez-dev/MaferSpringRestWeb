# Mafer webApplication

## Descripción
La aplicación es un desarrollo de **FitDigital** para Mafer.
Es una herramienta para gestionar y optimizar rutinas de entrenamiento personal. 
Está diseñada para que el personal trainer pueda hacer un seguimiento personalizado de cada
cliente y poder asi prestar sus servicios a distancia.

## Características principales
- Gestión de rutinas personalizadas
- Visualización de estadísticas de rendimiento (en desarrollo)
- Sistema de login y registro de usuarios
- Seguridad implementada con Spring Security
- Venta de productos para el entrenamiento

## Arquitectura del Proyecto
- **Frontend**: JavaScript, HTML5, CSS, Angular
- **Backend**: Spring Boot, Java
- **Base de datos**: MySQL
- **Gestion de JPA**: Hibernate
- **Seguridad**: Spring Security con autenticación por sesiones

## Instalación y configuración
1. Inicio del servidor en IntelliJ:

2. Actualizar dependencias se ser necesario al iniciar el proyecto

3. La base de datos se crea automatica con gestion de Hibernate bajo norma JPA
    - Ver el archivo `application.properties` con los detalles de conexión a la base de datos.

4. Si el proyecto aun no tiene usuario registrados, registrarse desde el login
5. El primer usuario se creara con:
   - username: admin
   - password: 1234
   - puedes cambiarlos al iniciar sesion, ya que se crea automaticamente con permisos de administrador ROLE_ADMIN
   - el resto de los usuarios que se vayan registrando, se cargaran automaticamnete como ROLE_USER
   - en caso de querer dar un rol de administrador a otro usuario, se puede hacer desde la edicion de un usuario creado o creando directamente un usuario desde el panel de administrador

6. La duracion de las sesiones se puede cambiar desde la clase
    - ValidacionesServiceImpl
    - private final int duracionSesionROLE_ADMIN = 360; // SETEAR EL TIEMPO DE SESION PARA ADMIN QUE QUEREMOS ACA (en segundos)
    - private final int duracionSesionROLE_USER = 60; // SETEAR EL TIEMPO DE SESION PARA USER QUE QUEREMOS ACA (en segundos)
    - private final int cantidadDeSesiones = 2000; // SETEAR LA CANTIDAD DE CONEXIONES SIMULTANEAS QUE QUEREMOS PERMITIR ACA

7. La aplicacion tiene habilitado el manejo de sesiones simultaneas por lo que se puede probar con varios usuarios y sesiones a la vez
8. Toda la documentacion de los endpoints y el manejo de los mismos esta visible para sesion inciada en:
    - http://localhost:8080/doc/swagger-ui/index.html#/
9. Si las acciones se interrumpen inesperadamente, no olvidar cambiar la duracion de las sesiones para que no interfieran en la etapa de testing
10. REVISAR ALGUNOS ERRORES DE AUTENTICACION DESDE EL FRONT, ESTAN MANDANDO EL FECTH PARA LOGOUT CON ALGUNA FUNCION ASYNC
   
### . **Uso de la Aplicación**

```markdown
## Uso de la Aplicación
- **Crear una rutina**: Desde el panel principal, selecciona "Nueva rutina" y completa los campos solicitados.
- **Crear usuario con permisos de admin
- **Inicio de sesión**: Ingresar usuario y contraseña para acceder al panel de control.
- **Crear otro usuario con permiso de user (puede ser desde registro o desde panel de admin)
- **Crear algunos ejercicios para poder crear los dias de ejercicio
- **Crear algunos dias de ejercicio, para poder crear las rutinas
- **Crear algunas rutinas,seleccionando los dias de ejercicio
- **Crear algunas asesorias, que son los paquetes que pueden comprar los clientes
- **Una vez hecho esto, seleccionar un cliente y asignarle una asesoria (que en la realidad deberia haber pagado previamente)
- **Luego asignarle una rutina (que puede haber sifo preparada especialmente o ser una generica de Mafer)
- **Cargar productos al stock (hay 2 variedades, que pueden ser suplemento o accesorios)
- **Gestionar los productos asignandole el stock
- **Una vez hecho esto, se puede probar con el usuario que acceda a su informacion personal y al las secciones de compra.
- **El carrito maximo permitido es de 10 variedades de productos, sin limite de cantidad por producto.
- **Lo mismo sucede para los dias de entrenamiento, que 1 dia permite hasta 10 ejercicios.
- **Para cerrar la sesion se hace logout o salir.

http://localhost:8080/doc/swagger-ui/index.html#/




 