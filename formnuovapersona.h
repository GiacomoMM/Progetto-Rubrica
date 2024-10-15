#ifndef FORMNUOVAPERSONA_H
#define FORMNUOVAPERSONA_H

#include <QDialog>
#include "persona.h"

namespace Ui {
class formNuovaPersona;
}

class formNuovaPersona : public QDialog
{
    Q_OBJECT

public:
    explicit formNuovaPersona(QWidget *parent = nullptr);
    ~formNuovaPersona();

signals:
    void inserisciNuovaPersona(Persona* p);

private slots:
    void on_Salva_clicked();

    void on_Annulla_clicked();

private:
    Ui::formNuovaPersona *ui;
};

#endif // FORMNUOVAPERSONA_H
