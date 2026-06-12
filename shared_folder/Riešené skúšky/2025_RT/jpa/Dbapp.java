package banka;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Dbapp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();      
        em.close();
         
    }
    
    /* 
    Metóda slúži na vytvorenie nového prevodu.
    argumenty:
    - em: entity manager (je pripravený na použitie. Pozor! nezatvárať ho!)
    - suma : suma, ktorá sa bude prevádzať
    - z_uctu: číslo účtu, z ktorého sa suma prevádza 
    - na_ucet: číslo účtu, na ktorý sa suma prevádza 
    Metóda najprv preverí či zadané účty existujú a suma je kladná.
    - Ak niektorý z účtov neexistuje alebo suma nie je kladná, vyhodí výnimku 
      so správou: "Zle udaje"
    - Inak vytvorí nový záznam v tabuľke prevodov so zadanými údajmi, pričom hodnota 
      v stĺpci REALIZOVANY bude NULL. 
    Metóda vráti ID vytvoreného prevodu.
    Poznamka. V tabuľke účtov táto metóda nerobí žiadne zmeny.    
    */
    public static long vytvorPrevod(EntityManager em, double suma, long z_uctu, long na_ucet) throws Exception {
        if (suma <= 0) {
            throw new Exception("Zle udaje");
        }
        
        Ucet zUctu = em.find(Ucet.class, z_uctu);
        Ucet naUcet = em.find(Ucet.class, na_ucet);
        
        if (zUctu == null || naUcet == null) {
            throw new Exception("Zle udaje");
        }
        
        Prevod prevod = new Prevod();
        prevod.setSuma(suma);
        prevod.setZ_id(zUctu);
        prevod.setNa_id(naUcet);
        prevod.setRealizovany(null);
        
        em.getTransaction().begin();
        em.persist(prevod);
        em.getTransaction().commit();
        
        return prevod.getId();
    }
    /* 
    Metóda vyhľadá v databáze všetky nezrealizované prevody a ak je možné, zrealizuje ich.  
    Nezrealizovaný prevod je prevod, ktorý nemá hodnotu v stĺpci REALIZOVANY.
    Prevod je možné zrealizovať, ak stav účtu, z ktorého sa SUMA prevádza (t.j. ZUCTU_ID) 
      nie je nižší ako prevádzaná SUMA.
    Realizácia prevodu sa vykoná tak, že prevádzaná SUMA sa odčíta od stavu účtu,
       z ktorého sa prevádza a pripočíta sa k stavu na druhom účte (t.j NAUCET_ID). 
    Zároveň v prevode sa do stĺpca REALIZOVANY zadá aktuálny čas ako čas realizácie. 
    Všetky tieto zmeny funkcia zapíše do databázy naraz v rámci jednej transakcie.  
    Návratová hodnota metódy je počet zrealizovaných prevodov.
    */
    public static int realizujPrevody(EntityManager em) {
        List<Prevod> prevody = em.createQuery("SELECT p FROM Prevod p WHERE p.realizovany IS NULL", Prevod.class).getResultList();
        int count = 0;
        
        em.getTransaction().begin();
        if (!prevody.isEmpty()) {
            for (Prevod prevod : prevody) {
                if (prevod.getZ_id().getStav() >= prevod.getSuma()) {
                    prevod.getZ_id().setStav(prevod.getZ_id().getStav() - prevod.getSuma());
                    prevod.getNa_id().setStav(prevod.getNa_id().getStav() + prevod.getSuma());
                    prevod.setRealizovany(new Date());
                    count++;
                }
            }
        }
        em.getTransaction().commit();
        return count;
        
    }

}
