#include "persona.h"

Persona::Persona(QString n,QString c,QString i,QString t,int e):nome(n),cognome(c),indirizzo(i),telefono(t),eta(e) {}

const QString Persona::getNome(){
    return this->nome;
}

const QString Persona::getCognome(){
    return this->cognome;
}

const QString Persona::getIndirizzo(){
    return this->indirizzo;
}

const QString Persona::getTelefono(){
    return this->telefono;
}

int Persona::getEta(){
    return this->eta;
}

void Persona::setNome(QString n){
    this->nome=n;
}

void Persona::setCognome(QString c){
    this->cognome=c;
}

void Persona::setIndirizzo(QString i){
    this->indirizzo=i;
}

void Persona::setTelefono(QString t){
    this->telefono=t;
}

void Persona::setEta(int e){
    this->eta=e;
}

bool Persona::operator==(const Persona& other) const{
    return this->telefono==other.telefono;
}
