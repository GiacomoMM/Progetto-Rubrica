#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "persona.h"
#include "formnuovapersona.h"
#include "formmodificapersona.h"


QT_BEGIN_NAMESPACE
namespace Ui {
class MainWindow;
}
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

public slots:
    void inserisciNuovaPersona(Persona* p);
    void on_Elimina_clicked();

signals:
    void campiModifica(Persona*p);

private slots:
    void on_Nuovo_clicked();
    void on_Modifica_clicked();

private:
    Ui::MainWindow *ui;
    void inserisciInLista(QList<Persona>* lista_persone);
    void eliminaPersonaLista(QString* temp_tel);
    void eliminaPersonaDatabase(QString* temp_tel);
    formNuovaPersona* nuovaPersona;
    void inserisciPersonaDatabase(Persona * p);
    void inserisciPersonaLista(Persona* p);
    formModificaPersona* modificaPersona;
};
#endif // MAINWINDOW_H
