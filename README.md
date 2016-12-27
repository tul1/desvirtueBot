telegramBot - 27/12/2016

En este repositorio se encuentra el codigo fuente del bot para la aplicacion de mensajeria telegram utilizado en el grupo de chat desvirtue.

Desvirtue_Bot tendra por objetivo gestinar la organizacion de eventos sociales del grupo (asados, reuniones, despedidas, hackatones).

El bot funciona con los siguientes de comandos.

* IniciarEvento. Las tareas que realizara este comando seran las siguientes:
    1- Crear una nueva tabla en la base de datos.
    2- Activara el resto de los comandos.

* Lugar. Define el lugar donde se realizara el evento.

* Hora. Define la hora del evento como argumento a continuacion 

* Voy. El usuario que lo ejecute se agregara a la lista de invitados. El usuario solo podra agregarse una vez a la lista. Este comando no recibira ningun argumentos.

* NoVoy. El usuario que invoque este comando se eleminara de la lista de invitados. Si el usuario no se encuentra en la lista este comando no realizara ninguna operacion. Este comando no recibira ningun argumentos.

* ImprimirEvento. Imprimira el lugar del evento, la hora y la lista de invitados. Este comando no recibira ningun argumentos.

* FinalizarEvento. Eliminara toda la informacion generada del evento. Este comando no recibira ningun argumentos.

* help.
