# Progetto-Rubrica

Realizzazione di un progetto in Java che rappresenti una rubrica telefonica. La rubrica è stata realizzata con una tabella dinamica nella quale ogni riga rappresenta una diversa persona, le persone sono gestite attraverso
la classe "Persona" costituita dagl'attributi:
- nome: stringa
- cognome: stringa
- indirizzo: stringa
- telefono: stringa
- eta: intero

La gestione della persistenza dei dati è stata gestita creando una cartella principale ed ogni classe "Persona" viene salvata al suo interno in un file.txt. Il nome del file.txt associato ad ogni "Persona" viene generato
tramite hashCode del numero di telefono(essendo esso univoco). Il formato dei file.txt è il seguento come da esempio: 
- Steve;Jobs;via Cupertino 13;0612344;56
- Bill;Gates;via Redmond 10;06688989;60
- Babbo;Natale;via del Polo Nord;00000111;99

La gestione della rubrica cioè inserimento/modifica di un elemento dalla rubrica avviene attraverso una seconda GUI, costituita da diversi campi associati agli attributi della classe "Persona".
L'eliminazione di un elemento viene gestito tramite conferma di eliminazione.
