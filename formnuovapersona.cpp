#include "formnuovapersona.h"
#include "ui_formnuovapersona.h"
#include <QMessageBox>

formNuovaPersona::formNuovaPersona(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::formNuovaPersona)
{
    ui->setupUi(this);
    connect(this,SIGNAL(inserisciNuovaPersona(Persona*)),parent,SLOT(inserisciNuovaPersona(Persona*)));

}

formNuovaPersona::~formNuovaPersona()
{
    delete ui;
}

void formNuovaPersona::on_Salva_clicked()
{
    if(ui->editNome->text()=="" || ui->editCognome->text()=="" || ui->editIndirizzo->text()=="" || ui->editTelefono->text()=="" || ui->editEta->text().toInt()==0){
        QMessageBox::warning(this,NULL,"Inserire campi validi");
        return;
    }
    Persona* p=new Persona(ui->editNome->text(),ui->editCognome->text(),ui->editIndirizzo->text(),ui->editTelefono->text(),ui->editEta->text().toInt());
    emit inserisciNuovaPersona(p);
    on_Annulla_clicked();
}


void formNuovaPersona::on_Annulla_clicked()
{
    ui->editNome->setText("");
    ui->editCognome->setText("");
    ui->editIndirizzo->setText("");
    ui->editTelefono->setText("");
    ui->editEta->setText("");
    this->hide();
}

