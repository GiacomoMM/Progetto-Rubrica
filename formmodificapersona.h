#ifndef FORMMODIFICAPERSONA_H
#define FORMMODIFICAPERSONA_H

#include <QDialog>
#include <QLineEdit>
#include "persona.h"

namespace Ui {
class formModificaPersona;
}

class formModificaPersona : public QDialog
{
    Q_OBJECT

public:
    explicit formModificaPersona(QWidget *parent = nullptr);
    ~formModificaPersona();

signals:
    void eliminaPersona();
    void salvaPersona();
    void salvaPersona(Persona*);

public slots:
    void campiModifica(Persona* p);

private slots:
    void on_Salva_clicked();

    void on_Annulla_clicked();

private:
    Ui::formModificaPersona *ui;
};

#endif // FORMMODIFICAPERSONA_H
