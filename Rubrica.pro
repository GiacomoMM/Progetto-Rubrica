QT       += core gui sql

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

CONFIG += c++17

# You can make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    formmodificapersona.cpp \
    formnuovapersona.cpp \
    main.cpp \
    mainwindow.cpp \
    persona.cpp

HEADERS += \
    formmodificapersona.h \
    formnuovapersona.h \
    mainwindow.h \
    persona.h

FORMS += \
    formmodificapersona.ui \
    formnuovapersona.ui \
    mainwindow.ui

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

DISTFILES += \
    C:/Users/giaco/OneDrive/Desktop/progettoRubricaDatabase/credenziali_database.properties
