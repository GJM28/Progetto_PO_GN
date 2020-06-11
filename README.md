# Twitter Analytics 
## Foramato dei dati
In seguito ad una richiesta, l'applicazione scarica un JSON. Nel file è presente un vettore 'users' (in questo caso il vettore users sta ad indicare i friends delll'utente che stiamo andando ad analizzare). In questo vettore sono presenti i vari parametri che descrivono un utente di Twitter.
![Alt Text](JSON.PNG) 

Una volta prese queste informazioni il programma andava ad effettuare un parsing dei dati in modo da ottenere solamente i parametri che servivano a noi ("name" e "followers_count").
![Alt Text](JSONParsing.PNG) 

In particolare:
* **name** rappresenta il nome dell'utente (friend)
* **followers_count** rappresenta il numero dei follower che quell'utente ha

## Rotte dell'applicazione
All'interno dell'applicazione è possibili andare ad effettuare quattro richieste GET(metodo che viene utilizzato principalmente per richiedere una risorsa al server) e una richiesta POST(nasce invece con l'idea di creare o modificare dei dati sul server).

![Alt Text](get_data.PNG)

![Alt Text](get_metadata.PNG)

![Alt Text](metadata_results.PNG)

![Alt Text](get_stats.PNG)

![Alt Text](stats_result.PNG)

![Alt Text](get_top10.PNG)

![Alt Text](top10_results.PNG)

![Alt Text](get_last10.PNG)

![Alt Text](last10_results.PNG)

![Alt Text](getbrakets.PNG)

