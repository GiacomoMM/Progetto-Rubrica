

Installare postgreSQL dal sito (con tutti i programmi associati)

//abilitare comando psql su windows
Pannello di controllo->Sistema->Impostazioni Avanzate di sistema
Selezionare Avanzate
	In Variabili d'ambiente
		Selezionare Path e cliccare modifica
			Selezionare Nuovo ed aggiungere file path come ad esempio "C:\Program Files\PostgreSQL\17\bin"
Fare salva e chiudere
Verifica nel cmd con psql --version

Adesso procedere con il comando come ad esempio "psql -U postgres -f C:\Usersutente\Desktop\progettoRubrica\schema_database.sql"
inserire password usata per installare postgreSQL