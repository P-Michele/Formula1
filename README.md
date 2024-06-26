# Responsabilitá 
## Rappresentazione e Gestione dei Giocatori 
I giocatori (bot) sono rappresentati dalle classi Player e BotPlayer. Player definisce le 
funzionalità base mentre BotPlayer estende questa funzionalità con decisioni di 
movimento automatizzate basate su algoritmi casuali. 
## Definizione e Analisi del Circuito di Gara 
La configurazione fisica della pista è definita attraverso la classe Track, che memorizza 
informazioni dettagliate sulle celle che compongono il circuito. Ogni cella è gestita dalla 
classe Cell. La classe CircuitParser è responsabile per l'analisi della configurazione 
del circuito da un file, creando una rappresentazione utilizzabile nel gioco. 
## Interfacciamento con l'Utente 
L'interazione con l'utente e la gestione dell'interfaccia grafica sono coordinate dalle classi 
GameController e RaceController. GameController gestisce l'inizializzazione e la 
configurazione del gioco, mentre RaceController gestisce la logica degli eventi durante 
la gara, inclusi il controllo delle azioni degli utenti come il pulsante di avvio e la 
visualizzazione dello stato della gara. 
## Gestione della Logica di Gioco 
La logica centrale e il controllo del flusso di gioco sono gestiti attraverso la classe 
GameEngine, che si occupa di eseguire le mosse, verificare le condizioni di fine gioco e 
gestire lo stato di ogni partita. 
## Visualizzazione della Gara 
La visualizzazione grafica del circuito e dei giocatori è affidata alla classe TrackView. 
Questa classe utilizza JavaFX per rappresentare graficamente le celle della pista e la 
posizione dei giocatori, aggiornando dinamicamente lo stato visivo in risposta ai 
cambiamenti nel gioco. 
## Avvio del progetto 
Il progetto puó essere avviato, posizionandosi su di esso da terminale, attraverso le 
istruzioni "gradle build" e successivamente "gradle run". É stato inserito un tracciato di 
esempio di nome “track.txt” visualizzabile tramite la simulazione e collocato nella cartella 
delle risorse. Per poter inserire un proprio percorso é necessario sovrascrivere il file in 
questione.  
## Formato del File di configurazione della Pista 
Il file di configurazione della pista è un documento di testo che descrive le dimensioni della 
pista e specifica il tipo di cella per ogni posizione sulla griglia. Il formato è strutturato come 
segue: 
Prima Riga: Dimensioni della Pista 
• Dimensioni: La prima riga del file contiene due numeri interi separati da uno 
spazio. Il primo numero rappresenta la larghezza della pista, mentre il secondo 
numero indica l'altezza della pista. 
Corpo del File: Configurazione delle Celle 
• Dopo la prima riga, ogni riga successiva rappresenta una fila di celle sulla pista. 
Ogni carattere in una riga corrisponde a un tipo specifico di cella, definito da un 
simbolo univoco. La lunghezza di ogni riga deve corrispondere esattamente alla 
larghezza specificata nella prima riga. 
Simboli delle Celle: 
o . (punto): Indica una cella di pista normale. 
o S: Indica una cella di partenza. 
o F: Indica una cella di arrivo. 
o #: Indica una cella fuori pista. 
o O: Indica una cella con olio (ostacolo). 
Il progetto consegnato non include la logica di funzionamento per i giocatori interattivi. 
