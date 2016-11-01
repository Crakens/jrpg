### Historia de usuario 1 ###
*Como* jugador *quiero* crear mi personaje, editando sus características principales como raza y casta *para* verme reflejado en mi avatar  
**Criterio de Aceptación** 
Dado un jugador y un personaje cuando el jugador crea el personaje entonces se definen las características iniciales del personaje.  

### Historia de usuario 2 ###
*Como* personaje *quiero* ingresar a un mundo *para* adquirir experiencia, ítems y habilidades nuevas.  
**Criterio de Aceptación**
Dado un personaje cuando selecciona el mapa entonces éste es ubicado en dicho mundo.   

### Historia de usuario 3 ###
*Como* personaje *quiero* eliminar enemigos *para* aumentar mi experiencia.  
**Criterio de Aceptación**
Dado un personaje y un enemigo cuando el personaje vence al enemigo entonces aumenta su experiencia.

### Historia de usuario 4 ###
 *Como* personaje *quiero* acumular experiencia *para* poder subir de nivel.  
 **Criterio de Aceptación**
 Dado un personaje cuando el personaje supere el valor máximo de experiencia del nivel en el que se encuentra entonces éste sube de nivel.      
 
### Historia de usuario 5 ###
*Como* personaje *quiero* subir de nivel *para* asignar puntos adicionales a mis habilidades.  
**Criterio de Aceptación**  
Dado un personaje cuando el mismo suba de nivel entonces éste adquiere puntos para aumentar las características.

### Historia de usuario 6 ###
*Como* personaje *quiero* equipar ítems *para* potenciar mis habilidades  
**Criterio de Aceptación**  
Dado un personaje cuando el personaje agarra los items entonces éste aumenta sus puntos de habilidad.   

### Historia de usuario 7 ###
*Como* personaje *quiero* encontrarme con otros personajes en el mismo mundo *para* aliarme a ellos.  
**Criterio de Aceptación**
Dado dos personajes cuando se encuentren en el mismo radio y ambos seleccionen aliarse entonces éstos se alían. 

### Historia de usuario 8 ###
*Como* personaje *quiero* encontrarme con otros personajes en el mismo mundo *para* combatir contra ellos.  
**Criterio de Aceptación**
Dado dos personajes cuando se encuentren en el mismo radio y ambos seleccionen enfrentarse o uno elija dicha opción entonces éstos se enfrentan en batalla.

### Historia de usuario 9 ###
*Como* personaje *quiero* aliarme con otro jugador *para* duplicar la experiencia que recolectamos en ese tiempo.  
**Criterio de Aceptación**
Dado un personaje que forme parte de una alianza cuando derrote a los enemigos entonces éste duplica la experiencia ganada en ese tiempo.    

### Historia de usuario 10 ###
*Como* personaje *quiero* combatir contra otro personaje *para* obtener su mejor ítem al derrotarlo.  
**Criterio de Aceptación**  
Dado un personaje y un enemigo cuando el personaje derrota al enemigo entonces el personaje obtiene el item más valioso del derrotado.

### Historia de usuario 11 ###
*Como* personaje *quiero* cambiar las alianzas establecidas cada cierta cantidad de tiempo *para* poder traicionar a mis aliados.   
**Criterio de Aceptación**  
Dado un personaje que forme parte de una alianza cuando se aleje a una distancia mayor al establecido de el/los personajes aliado/s o elige atacar a uno de sus aliados entonces deja de formar parte de la alianza.  
 
### Historia de usuario 12 ###
*Como* personaje *quiero* que se reestablezca por completo mi energía después de cada batalla *para* seguir jugando  
**Criterio de Aceptación**
Dado un personaje cuando finalice una batalla con vida entonces su indicador de energía es reestablecido automáticamente.

### Historia de usuario 13 ###
 *Como* personaje *quiero* poder equiparme ítems sin restricción alguna *para* tener más poderes.   
 **Criterio de Aceptación**  
 Dado un personaje y un item cuando el personaje agarre el nuevo item entonces el mismo es acumulado a los ya existentes como así también sus efectos. 
 
### Historia de usuario 13.1 ###
*Como* personaje *quiero* no perder items *para* acumularlos.   
**Criterio de Aceptación**  
Dado un personaje y un item cuando el personaje agarre el nuevo item entonces los items agarrados previamente no deben ser desequipados.

### Historia de usuario 14 ###
*Como* personaje *quiero* poder combatir contra un batallón de enemigos *para* obtener items.  
**Criterio de Aceptación**  
Dado un personaje cuando éste derrote a un conjunto de enemigos en la misma batalla entonces obtiene un item por cada uno de ellos.
 
### Historia de usuario 15 ###
*Como* jugador *quiero* que la magia sea restringida solo para algunos personajes *para* que no todos gocen de este beneficio.  
**Criterio de Aceptación**  
Dado un personaje cuando este es del tipo mago sólo entonces puede utilizar magia.

### Historia de usuario 16 ###
*Como* jugador *quiero* que, al derrotar un enemigo, estando en una alianza, el enemigo arroje tantos ítems como personajes haya en la alianza *para* que cada uno de personajes de la alianza obtenga un item.  
**Criterio de Aceptación**  
Dada una alianza y un enemigo, cuando la alianza derrote al enemigo entonces éste arroja tantos items iguales como personajes de esa alianza formaron parte de la batalla.

### Historia de usuario 17 ###
*Como* jugador *quiero* que existan puntos de restauración de salud y magia *para* recuperar salud y magia de mi personaje.  
**Criterio de Aceptación**  
Dado un personaje cuando éste se ubique en una posada dentro del mapa entonces sus puntos de salud y magia, si fuese mago, se ven restaurados en su totalidad.

### Historia de usuario 18 ###
*Como* jugador *quiero* que en el mapa estén dispersos objetos que puedan romperse y que contengan restauraciones parciales de salud o magia *para* que el personaje incremente sus puntos de vida y/o magia.  
**Criterio de Aceptación**  
Dado un personaje y un objeto atacable cuando el personaje destruya el objeto entonces se incrementa en una cierta cantidad mínima los untos de salud y/o magia del personaje sin superar el máximo.

### Historia de usuario 19 ###
*Como* jugador *quiero* que se conecten más de 10 jugadores al mismo mapa *para* formar alianzas o pelear contra ellos.  
**Criterio de Aceptación**  
Dados diez jugadores cuando se conecten todos al mismo mapa entonces pueden formar alianzas o batallar entre ellos.

### Historia de usuario 20 ###
*Como* jugador *quiero* que los turnos de los batallones en los combates se decidan en base a la suma de los puntos de agilidad de los personajes que conforman el batallón *para* que haya un orden al atacar.  
**Criterio de Aceptación**  
Dados dos batallones que entran en batalla cuando la suma de los puntos de agilidad de los personajes de un batallón supera a la suma del otro, entonces ese ataca primero.

### Historia de usuario 21 ###
*Como* jugador *quiero* que los turnos de los personajes dentro del batallón en los combates se decidan en base a los puntos de agilidad de cada personaje *para* que haya un orden al atacar.  
**Criterio de Aceptación**  
Dado un batallón cuando se encuentran en combate se ordenan los personajes de acuerdo a sus puntos de agilidad entonces el de mayor cantidad de puntos de agilidad es el primero en atacar.

### Historia de usuario 22 ###
*Como* jugador *quiero* que un mago pueda atacar sin necesitar de la magia *para* que no esté indefenso al momento de la batalla.  
**Criterio de Aceptación**  
Dado un mago cuando se encuentra involucrado en un combate entonces ataca sin necesidad de magia, como cualquier otro personaje.

### Historia de usuario 23 ###
*Como* jugador *quiero* poder huir de un combate siempre y cuando no este aliado con nadie *para* poder seguir mi camino.  
**Criterio de Aceptación**  
Dado un personaje y un combate del que forma parte cuando no se encuentra en alianza con otro personaje del combate y selecciona abandonar combate entonces abandona el combate.

### Historia de usuario 24 ###
*Como* jugador *quiero* que si un personaje, que no se encuentra en una alianza, abandona un combate pierda un item *para* que sea penalizado por su accionar.  
**Criterio de Aceptación**  
Dado un personaje cuando este abandona un combate en cualquier momento entonces se le quita uno de sus items como penalización.

### Historia de usuario 25 ###
*Como* jugador *quiero* que al huir de un combate estando en una alianza, todos los miembros de la alianza elijan huir *para* que se de por finalizada la batalla.  
**Criterio de Aceptación**  
Dado una alinza y un combate del que forma parte la misma cuando todos los personajes que forman parte de la alianza elijen huir de la batalla entonces se da por terminada la batalla y se le quita un item a cada uno de los personajes.

### Historia de usuario 26 ###
*Como* jugador *quiero* que al aliarme con un personaje que es miembro de una alianza, automáticamente me alíe con los demás miembros de esa alianza *para* luchar con ellos.  
**Criterio de Aceptación**  
Dado un personaje y una alianza de la que no forma parte cuando el personaje selecciona aliarse con uno de los personajes que forman la otra alianza entonces se alía con todo el resto de los personajes que conforman la alianza.

### Historia de usuario 27 ###
*Como* jugador *quiero* que durante una batalla un personaje pueda atacar siempre y cuando posea la energía suficiente *para* efectuar su ataque.  
**Criterio de Aceptación**  
Dado un personaje en combate cuando quiere atacar y sus puntos de energia son iguales o superan a los puntos de energía necesarios para efectuar el ataque, entonces ataca.

### Historia de usuario 28 ###
*Como* jugador *quiero* que el mago pueda utilizar magia siempre y cuando posea la magia suficiente *para* realizar ese hechizo.  
**Criterio de Aceptación**  
Dado un mago cuando quiere selecciona un hechizo y sus puntos de magia  son iguales o superan a los puntos de energía necesarios para su realización entonces se aplica el hechizo.

### Historia de usuario 29 ###
*Como* jugador *quiero* un personaje si no posee los puntos necesarios de ataque se saltee su turno de ataque *para* continuar con el siguiente personaje.  
**Criterio de Aceptación**  
Dado un personaje en combate cuando quiere atacar y sus puntos de energia son inferiores a los puntos de energía necesarios para efectuar el ataque, entonces pierde su turno de ataque y continúa con el siguiente personaje.
