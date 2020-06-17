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

La prima richiesta GET che andiamo a vedere è quella che permette di restituire tutti i dati parsati del file JSON.
<p align="center">
  <img src="get_data.PNG">
</p>

Una rotta GET che siamo andati ad implementare è stata la seguente. Quest'ultima permette di ottenere i metadati presenti nel dataset. In particolare:
* **name** di tipo String
* **followers_count** di tipo Integer
<p align="center">
  <img src="get_metadata.PNG">
</p>
<p align="center">
  <img src="metadata_results.PNG">
</p>

Le statistiche possono essere effettuate soltanto sul campo followers_count.
<p align="center">
  <img src="get_stats.PNG">
</p>

In questo caso le statistiche riguardano:
* **Min** indicano le persone che hanno il numero di follower più basso
* **Max** indicano le persone che hanno il numero di follower più alto
* **Media_follower** indica una media di tutti i follower presenti nella lista
* **Somma_tot_follower** indica la somma totate di tutti i follower 
* **Deviazione_standard** dà un’idea di come siano distribuiti i dati nel campione rispetto alla media. Detto in un altro modo, consente    di sapere se la media è affidabile per dare una rappresentazione significativa dei dati.

<p align="center">
  <img src="stats_result.PNG">
</p>

Con questa rotta l'utente può visualizzare una classifica dei primi 10 utenti che hanno più follower o ,viceversa, gli ultimi 10 utenti che hanno meno follower. Più precisamente, in questa rotta si deve specificare attraverso il campo field, che classifica si vuole andare a vedere. In caso di campo errato o nullo viene restituito un errore. 
<table class="center">
  <tr>
    <td><img src="get_top10.PNG"></td>
    <td><img src="get_last10.PNG"></td>
  </tr>
    <tr>
    <td><img src="top10_results.PNG"></td>
    <td><img src="last10_results.PNG"></td>
  </tr>
 </table>
 
L'ultima rotta che analizziamo e una chiamata POST che permette di ottenere nome utente Twitter e numero di follower che rientrano su una fascia impostata dall'utente. Infatti, attraverso una RequestBody si può decidere su quale campo applicare il filtro e il filtro stesso. Ovviamente se si sbaglia a digitare il filtro o il campo, il programma restituirà un messaggio di errore. Qua sotto sono riportate immagini riguardanti un esempio di chiamata usando questa rotta ed esempi di filtro. Nel nostro caso siamo andati ad applicare i filtri solamente su un'unico campo, tuttavia se si vuole cambiare campo la logica rimane la stessa.  
<p align="center">
  <img src="getbrakets.PNG">
</p>

**Esempi di filtri della rotta getBrackets:** 
<p align="center">
  <img src="gt.PNG">
</p>
<p align="center">
  <img src="bt.PNG">
</p>
<p align="center">
  <img src="lt.PNG">
</p>
 

## UML
## User Diagram
![Alt Text](UserDatagram.PNG)
## Class Diagram
![Alt Text](classdiagram.PNG) 
## Sequence Diagram
* **GET/ranking**

![Alt Text](ranking.png) 

* **GET/getStatsOnObject**

![Alt Text](getstatsonobject.png) 

* **GET/getData**

![Alt Text](getdata.PNG) 

* **POST/getBracket**

![Alt Text](getBracket.png)


## Riflessioni sull'approccio al progetto
*“In online social networks, the audience size commanded by an organization or an individual is a critical measure of that entity’s popularity and this measure has important economic and/or political implications. Such efforts to measure popularity of users or exploit knowledge about their audience are complicated by the presence of fake profiles onthese networks.”*

Con queste parole si apre l’abstract dell’articolo di ricerca “ Profile characteristics of fakeTwitter accounts” a cura di Supraja Gurajala, Joshua S White, Brian Hudson,Brian R Voter and Jeanna N Matthews, pubblicato dal “Big Data & Society” journal. 
Abbiamo colto l’occasione per poter studiare ed iniziare ad implementare questo progetto con alcuni filtri ed alcune combinazioni di filtri finalizzate alla possibile individuazione di profili potenzialmente fake, quelli che un tempo su Twitter venivano chiamati “eggheads”, soprannome legato all’immagine del profilo assegnata di default da Twitter ad ogni profilo, che solitamente un fake non badava a sostituire o personalizzare. 
Abbiamo infatti trovato spunto con questo progetto, per un lavoro di approfondimento, o forse, vista la pregressa esperienza praticamente nulla, di conoscenza dell’ interessante mondo dei “Big Data” e di tutto ciò che concerne APi, dati, analisi degli stessi etc.
Questo progetto per noi non si conclude con la sua “consegna”, ma abbiamo intenzione di proseguire ed approfondire gli argomenti di cui sopra. 

Le variabili per scovare un possibile “fake”, sono molte, come analizzate appunto nell’articolo sopracitato (ed in seguito linkato); una forte rilevanza hanno infatti, ovviamente, i nomi di questi profili, nella ricerca studiati e collezionati tramite l’entropia di Shannon, ma anche gli orari ed i giorni di pubblicazione, per continuare con la qualità e la quantità dei tweet pubblicati. 

Quello di cui ci siamo occupati e di cui ci continueremo ad occupare invece in questo programma, sta nella qualità dei follower di un determinato account, ( in questo specifico caso, dei follower dei friends di un dato account). Elementi discriminanti, partendo dagli studi della ricerca e da alcune altre nostre ricerche, sono stati:
* Il numero di follower (profili filtrati se numero di follower <= 5)
* Deviazione standard
* Following to follow ratio (da implementare)
* Immagine del profilo preimpostata (eggheads) (da implementare)
* Nessuna biografia (da implementare)

È evidente che un profilo che abbia un numero di follower inferiore o uguale a 5 possa dare un forte sentore di “profilo fake”, per questo abbiamo pensato che fosse la prima delle nostre discriminanti per un egghead.
Anche il “following to follow” ratio è indicativo di un potenziale profilo fake: infatti un profilo che segua molti altri profili ma che a sua volta abbia pochi follower è potenzialmente un egghead. Stando alla ricerca un valore soglia di questa ratio è “30”, al momento non abbiamo ancora implementato questo filtro, più che per motivi di tempo, per il nostro desiderio di approfondire maggiormente questa cifra, dal momento che nel caso di profili “atipici” come quelli di persone famose, questo valore sarebbe sicuramente “anomalo”.
Una forte deviazione standard invece, presa singolarmente, potrebbe essere fuorviante, infatti il suo valore “anomalo” potrebbe, come sopra, essere dato da un account di una “celebrità”, che per ovvi motivi potrebbe avere un variegato tipo di follower (seguaci molto seguiti (altre celebrità) e seguaci poco seguiti). Potrebbe invece essere utile in combinazione con gli altri filtri.
Abbiamo inoltre intenzione di implementare un filtro che paragoni l’immagine standard che Twitter assegna ai nuovi profili (https://abs.twimg.com/sticky/default_profile_images/default_profile_400x400.png) e la biografia vuota, che un profilo fake tende a non modificare, che sarebbe dunque uguale a “ “ .

Preso singolarmente nessuno di questi indicatori può ovviamente dare alcuna sentenza, mentre una loro combinazione potrebbe essere invece piuttosto attendibile.
Questo progetto è appunto in divenire, sarà la nostra “cavia” per sperimentare questo interessante ambito dell’informatica, oltre all’implementazione dei vari filtri elencati sopra, studieremo la loro interazione per poter ottenere un risultato attendibile. Cercheremo anche di valutare anche i vari profili “speciali” come appunto quelli di personaggi noti.

## Software utilizzati
* **Eclipse:** è un ambiente di sviluppo integrato multi-linguaggio e multipiattaforma.
* **Maven** è uno strumento di gestione di progetti software basati su Java.
* **Spring Boot** è un framework open source per lo sviluppo di applicazioni su piattaforma Java.

## Autori
* [Nicolò Rossini](https://github.com/nicorossini)
* [Gregory James MacDonald](https://github.com/GJM28)


## Bibliografia:
* *Profile characteristics of fakeTwitter accounts: https://journals.sagepub.com/doi/pdf/10.1177/2053951716674236*
* *https://stackoverflow.com*
* *https://developer.twitter.com/en*






