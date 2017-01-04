#telegramBot - 27/12/2016

##Descripcion

En este repositorio se encuentra el codigo fuente del bot para la aplicacion de mensajeria telegram utilizado en el grupo de chat desvirtue.

Desvirtue_Bot tendra por objetivo gestinar la organizacion de eventos sociales del grupo (asados, reuniones, despedidas, hackatones).

El bot funciona con los siguientes de comandos.

* iniciarEvento. Las tareas que realizara este comando seran las siguientes:
    1- Crear una nueva tabla en la base de datos.
    2- Activara el resto de los comandos.

* lugar. Define el lugar donde se realizara el evento. Este comando recibira la direccion como un argumento. Ejemplo de invocacion: '/lugar paseo colon 850'

* hora. Define la hora del evento como argumento a continuacion del comando. Ejemplo de invocacion: '/hora 20:00'. El formato de este dato no sera validado.

* voy. El usuario que lo ejecute se agregara a la lista de invitados. El usuario solo podra agregarse una vez a la lista. Este comando no recibira ningun argumentos.

* noVoy. El usuario que invoque este comando se eleminara de la lista de invitados. Si el usuario no se encuentra en la lista este comando no realizara ninguna operacion. Este comando no recibira ningun argumentos.

* imprimirEvento. Imprimira el lugar del evento, la hora y la lista de invitados. Este comando no recibira ningun argumentos.

* finalizarEvento. Eliminara toda la informacion generada del evento. Este comando no recibira ningun argumentos.

* help. Imprimira los comandos disponibles.

## Base de datos:

La base de datos tendra 2 tablas. 
La primera contendra datos generales del evento:

| Id | Fecha | Hora | Lugar | Id tabla invitados |

La segunda tabla tendra cargada los datos de los invitados:

| Id | fecha de registro | Nombre y apellido |
