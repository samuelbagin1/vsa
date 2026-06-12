package banka;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

public class Banka {

    /* 
    Metóda slúži na vytvorenie nového prevodu
    Dostane sumu, ktorú treba previesť, a čísla účtov, z ktorého a na ktorý sa suma prevádza. 
    Najprv preverí či zadané účty existujú a suma je kladná.
    - Ak niektorý z účtov neexistuje alebo suma nie je kladná, vyhodí výnimku so správou: "Chybne volanie"
    - Inak vytvorí nový záznam v tabuľke prevodov so zadanými údajmi, pričom hodnota 
      v stĺpci ZAUCTOVANY nebude zadaná (NULL) a vráti ID vytvoreného prevodu.
    Poznamka. V tabuľke účtov táto metóda nerobí žiadne zmeny.    
    */
    public static long vytvorPrevod(EntityManager em, double suma, long z_id, long na_id) throws Exception {
    }
    
    /* 
    Metóda vyhľadá v databáze všetky nezrealizované prevody a ak je možné, zrealizuje ich.  
    Nezrealizovaný prevod je prevod, ktorý nemá hodnotu v stĺpci ZAUCTOVANY (je NULL).
    Prevod je možné zrealizovať, ak stav účtu, z ktorého sa SUMA prevádza (t.j. Z_ID) 
      nie je nižší ako prevádzaná SUMA.
    Realizácia prevodu sa vykoná tak, že prevádzaná SUMA sa odčíta od stavu účtu,
       z ktorého sa prevádza a pripočíta sa k stavu na druhom účte (t.j NA_ID). 
    Zároveň v prevode sa do stĺpca ZAUCTOVANY zadá aktuálny čas ako čas zaúčtovania. 
    Všetky tieto zmeny funkcia zapíše do databázy naraz v rámci jednej transakcie.  
    Návratová hodnota metódy je počet zrealizovaných prevodov.
    */
    public static int realizujPrevody(EntityManager em) {
    }
}
