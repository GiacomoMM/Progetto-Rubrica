#include "mainwindow.h"
#include "ui_mainwindow.h"

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <QSettings>
#include <QSqlRecord>
#include <QMessageBox>

QList<Persona> lista_persone;
formNuovaPersona* nuovaPersona;
QSqlDatabase db;

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    QSettings settings("C:/Users/giaco/OneDrive/Desktop/progettoRubricaDatabase/credenziali_database.properties", QSettings::IniFormat);

    QString host = settings.value("localhost").toString();
    int port = settings.value("db.port").toInt();
    QString dbname = settings.value("db.name").toString();
    QString user = settings.value("db.username").toString();
    QString password = settings.value("db.password").toString();

    db = QSqlDatabase::addDatabase("QPSQL");
    db.setHostName(host);
    db.setPort(port);
    db.setDatabaseName(dbname);
    db.setUserName(user);
    db.setPassword(password);

    if (!db.open()) {
        qDebug() << "Errore: impossibile connettersi al database!";
        qDebug() << "Dettagli dell'errore:" << db.lastError().text();
        exit(1);
    }


    inserisciInLista(&lista_persone);

    QStringList header;
    header.append("Nome");
    header.append("Cognome");
    header.append("Telefono");
    ui->tableWidget->setHorizontalHeaderLabels(header);

    for(int i=0;i<lista_persone.size();i++){
        ui->tableWidget->insertRow(ui->tableWidget->rowCount());
        ui->tableWidget->setItem(ui->tableWidget->rowCount()-1,0,new QTableWidgetItem(lista_persone[i].getNome()));
        ui->tableWidget->setItem(ui->tableWidget->rowCount()-1,1,new QTableWidgetItem(lista_persone[i].getCognome()));
        ui->tableWidget->setItem(ui->tableWidget->rowCount()-1,2,new QTableWidgetItem(lista_persone[i].getTelefono()));
    }

    nuovaPersona=new formNuovaPersona(this);
    modificaPersona=new formModificaPersona(this);


    connect(this,SIGNAL(campiModifica(Persona*)),modificaPersona,SLOT(campiModifica(Persona*)));
    ui->tableWidget->sortItems(0,Qt::SortOrder::AscendingOrder);
}

MainWindow::~MainWindow()
{
    db.close();
    delete ui;
}

void MainWindow::on_Nuovo_clicked()
{
    if(nuovaPersona->isHidden())
        nuovaPersona->show();
}


void MainWindow::on_Modifica_clicked()
{

    if(ui->tableWidget->selectedItems().count()==0){
        QMessageBox::warning(this,NULL,"Selezionare una riga");
        return;
    }
    if(modificaPersona->isHidden())
        modificaPersona->show();
    QString* temp_tel =new QString(ui->tableWidget->item(ui->tableWidget->currentRow(), 2)->text());
    for(Persona p:lista_persone){
        if(p.getTelefono()==*temp_tel){
            emit campiModifica(&p);
        }
    }
    delete(temp_tel);
}


void MainWindow::on_Elimina_clicked()
{
    if(ui->tableWidget->selectedItems().count()==0){
        QMessageBox::warning(this,NULL,"Selezionare una riga");
        return;
    }
    QString* temp_tel =new QString(ui->tableWidget->item(ui->tableWidget->currentRow(), 2)->text());
    eliminaPersonaLista(temp_tel);
    eliminaPersonaDatabase(temp_tel);
    ui->tableWidget->removeRow(ui->tableWidget->currentRow());
    delete(temp_tel);
}


void MainWindow::inserisciInLista(QList<Persona>* lista_persone){
    QSqlQuery query("SELECT * FROM persone");
    QList<QString>* temp_list=new QList<QString>;
    while(query.next()){
        for(int i=0;i<query.record().count();i++){
            temp_list->insert(i,query.value(i).toString());
        }
        lista_persone->append(Persona(temp_list->at(0),temp_list->at(1),temp_list->at(2),temp_list->at(3),temp_list->at(4).toInt()));
    }
    delete(temp_list);
}

void MainWindow::eliminaPersonaLista(QString* temp_tel){
    for(Persona p:lista_persone){
        if(p.getTelefono()==*temp_tel){
            lista_persone.removeOne(p);
        }
    }
}

void MainWindow::eliminaPersonaDatabase(QString* temp_tel){
    QSqlQuery query;
    query.prepare("DELETE FROM persone WHERE telefono = :telefono");
    query.bindValue(":telefono",*temp_tel);
    if(query.exec())
        qDebug()<<"eliminato da database";
}

void MainWindow::inserisciPersonaDatabase(Persona* p){
    QSqlQuery query;
    query.prepare("INSERT INTO persone (nome,cognome,indirizzo,telefono,eta) VALUES (:valore1,:valore2,:valore3,:valore4,:valore5)");
    query.bindValue(":valore1",p->getNome());
    query.bindValue(":valore2",p->getCognome());
    query.bindValue(":valore3",p->getIndirizzo());
    query.bindValue(":valore4",p->getTelefono());
    query.bindValue(":valore5",p->getEta());
    if(!query.exec()){
        qDebug() << "Errore nell'inserimento dei dati:" << query.lastError().text();
        return;
    }
    qDebug()<<"dati inseriti in databse";

}

void MainWindow::inserisciPersonaLista(Persona* p){
    lista_persone.append(Persona(p->getNome(),p->getCognome(),p->getIndirizzo(),p->getTelefono(),p->getEta()));
}

void MainWindow::inserisciNuovaPersona(Persona* p){
    qDebug()<<"inserisco persona";
    for(Persona p1:lista_persone){
        if(p1.getTelefono()==p->getTelefono()){
            QMessageBox::warning(this,NULL,"Persona gia presente");
            return;
        }
    }
    inserisciPersonaLista(p);
    inserisciPersonaDatabase(p);
    ui->tableWidget->insertRow(ui->tableWidget->rowCount());
    ui->tableWidget->setItem(ui->tableWidget->rowCount()-1,0,new QTableWidgetItem(p->getNome()));
    ui->tableWidget->setItem(ui->tableWidget->rowCount()-1,1,new QTableWidgetItem(p->getCognome()));
    ui->tableWidget->setItem(ui->tableWidget->rowCount()-1,2,new QTableWidgetItem(p->getTelefono()));
    delete(p);
    ui->tableWidget->sortItems(0,Qt::SortOrder::AscendingOrder);
}

