# Services

The objective of this lesson is to study services in the context of the web.

Is studied :

- concurrent and parallel programming
- synchronous/asynchronous programs
- handling transactions


This repo gather the exercises (tp) of the lesson.


## TP1 - Automaton as a service

The case study of this TP is to handle **stateful** and **stateless** services through the example of an automaton.

## TP2 - Register as service

The case study of this TP is to optimist concurrency control through the example of an automaton.

### Server without concurrency control
#### Server
```
- get(rep) & Ressource(x) -> rep(x) & Ressource(x)
- set(rep, y) & Ressource(x) -> rep(y) & Ressource(y)
```

### Client
```
- get(lecture) // Etat initial
- lecture(x) -> set(ecriture, x + 1)
- ecriture(x) -> Affichage(x)
```

### Server with concurrency control
```
- get(rep) & Ressource(x) & Version(n)
    -> rep(x, n) & Ressource(x) & Version(n)
// Ecriture réussie
- set(rep, y, n) & Ressource(x) & Version(n)
    -> rep(OK, y, n + 1) & Ressource(y) & Version(n + 1)
// Ecriture annulée
- set(rep, y, m) & Ressource(x) & Version(n) & (n != m)
    -> rep(KO, x, n) & Ressource(x) & Version(n)
```


### Server with client cache
```
// Lecture à réaliser en cache
- get(rep, n) & Version(n)
    -> rep(CACHE, n) & Version(n)
// Lecture de la ressource
- get(rep, m) & Ressource(x) & Version(n) & (n != m)
    -> rep(x, n) & Ressource(x) & Version(n)
// Ecriture réussie
- set(rep, y, n) & Ressource(x) & Version(n)
    -> rep(OK, y, n + 1) & Ressource(y) & Version(n + 1)
// Ecriture annulée
- set(rep, y, m) & Ressource(x) & Version(n) & (n != m)
  -> rep(KO, x, n) & Ressource(x) & Version(n)
```


### Client with cache
#### Client
```
// Etat initial
- getI(lecture)

// Incrémentation
- lecture(x) -> setI(ecriture, x + 1)
// Echec avec reprise
- ecriture(KO, x) -> setI(ecriture, x + 1)
// Succès
- ecriture(OK, x) -> Affichage(x)
```

#### Interceptor
```
// Etat initial
- Cache(NON_DEFINI, NON_DEFINI)

// Interception d'une requête en lecture
- getI(k) & Cache(x, n) -> get(lectureI, n) & Cache(x, n)
// Réponse demandant la lecture en cache
- lectureI(CACHE, n) & Cache(x, n) -> k(x) & Cache(x, n)
// Réponse transmettant le résultat et mise en cache
- lectureI(y, m) & Cache(x, n) -> k(y) & Cache(y, m)

// Interception d'une requête en écriture
- setI(k, y) & Cache(x, n) -> set(ecritureI, y, n) & Cache(x, n)
// Ecriture réussie avec mise à jour du cache
- ecritureI(OK, y, m) & Cache(x, n) -> k(OK, y) & Cache(y, m)
// Ecriture annulée avec mise à jour du cache
- ecritureI(KO, y, m) & Cache(x, n) -> k(KO, y) & Cache(y, m)
```
