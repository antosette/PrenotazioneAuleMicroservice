# API
/login
Permette di eseguire il login di un utente registrato nel database

# Prenotazione Aule Microservice - A simple RESTful Microservice

Sviluppato a scopo didattico.

## Getting Started

L'applicazione può essere avviata utilizzando la main class.
è basata sul framework Spring boot e utilizza un in-memory database (H2).
Alcuni dati di prova sono inseriti prima di ogni avvio dell'applicazione (vedere classe main --> metodo run di CommandLineRunner)
La gestione dell'autenticazione e autorizzazione è basata sui token JWT (JSON Web Token --> https://jwt.io/)

### Testing project

Dopo l'avvio si possono utilizzare le API:
```
 /login --> metodo POST per eseguire il login - per provare il servizio utilizzare i dati di test (username: Docente1 password: Doc_01)

 /prenotazioni --> metodo POST per aggiungere una prenotazione (proprietà per inserire la prenotazione da inserire nel body http)

 /utenti/{utente}/prenotazioni --> metodo GET per ottenere la lista delle prenotazioni di un utente
```

### IDE

Progetto sviluppato con IntelliJ.


## Built With

* [SpringBoot](https://projects.spring.io/spring-boot/) - framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2](http://www.h2database.com/html/main.html) - In-Memory database


## Versioning

Uso di [SemVer](http://semver.org/) per il versioning. Per le versioni disponibili consultare [tags on this repository](https://github.com/antosette/PrenotazioneAuleMicroservice/tags).

## Authors

* **Antonio Sette** - *Initial work* - [AntoSette](https://github.com/antosette)