#include "formmodificapersona.h"
#include "ui_formmodificapersona.h"
#include <QMessageBox>

formModificaPersona::formModificaPersona(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::formModificaPersona)
{
    ui->setupUi(this);
    connect(this,SIGNAL(eliminaPersona()),parent,SLOT(on_Elimina_clicked()));
    connect(this,SIGNAL(salvaPersona(Persona*)),parent,SLOT(inserisciNuovaPersona(Persona*)));
}

formModificaPersona::~formModificaPersona()
{
    delete ui;
}

void formModificaPersona::on_Salva_clicked()
{
    if(ui->editNome->text()=="" || ui->editCognome->text()=="" || ui->editIndirizzo->text()=="" || ui->editTelefono->text()=="" || ui->editEta->text().toInt()==0){
        QMessageBox::warning(this,NULL,"Inserire campi validi");
        return;
    }
    Persona* p=new Persona(ui->editNome->text(),ui->editCognome->text(),ui->editIndirizzo->text(),ui->editTelefono->text(),ui->editEta->text().toInt());
    emit eliminaPersona();
    emit salvaPersona(p);
    on_Annulla_clicked();
}


void formModificaPersona::on_Annulla_clicked()
{
    ui->editNome->setText("");
    ui->editCognome->setText("");
    ui->editIndirizzo->setText("");
    ui->editTelefono->setText("");
    ui->editEta->setText("");
    this->hide();
}

void formModificaPersona::campiModifica(Persona* p){
    qDebug()<<"riempio campi";
    ui->editNome->setText(p->getNome());
    ui->editCognome->setText(p->getCognome());
    ui->editIndirizzo->setText(p->getIndirizzo());
    ui->editTelefono->setText(p->getTelefono());
    ui->editEta->setText(QString::number(p->getEta()));
}

