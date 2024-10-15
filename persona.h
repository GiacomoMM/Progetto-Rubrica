#ifndef PERSONA_H
#define PERSONA_H

#include <QString>

class Persona
{
private:
    QString nome;
    QString cognome;
    QString indirizzo;
    QString telefono;
    int eta;

public:
    Persona(QString n,QString c,QString i,QString t,int e);

    void setNome(QString n);
    void setCognome(QString c);
    void setIndirizzo(QString i);
    void setTelefono(QString t);
    void setEta(int e);

    const QString getNome();
    const QString getCognome();
    const QString getIndirizzo();
    const QString getTelefono();
    int getEta();

    bool operator==(const Persona& other) const;
};




#endif // PERSONA_H
