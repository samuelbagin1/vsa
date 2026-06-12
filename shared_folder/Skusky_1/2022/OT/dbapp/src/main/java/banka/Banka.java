/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka;


import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Banka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();
        
        UCET ucet = new UCET();
        ucet.setId(1L);
        ucet.setStav(500);
        UCET ucet2 = new UCET();
        ucet2.setId(51L);
        ucet2.setStav(500);
        
        em.getTransaction().begin();

        em.persist(ucet);
        em.persist(ucet2);
        em.getTransaction().commit();
        
        System.out.println("PID " + zadajPrevod(em, 1.0, 1L, 51L));
        System.out.println("PID " + realizujPrevody(em));
        em.close();
    }
    /*
    Metóda slúži na zadávanie nového prevodu
    Dostane sumu, ktorú treba previesť, a čísla účtov, z ktorého a na ktorý sa suma prevádza.
    Najprv preverí či zadané účty existujú a suma je kladná.
    - Ak niektorý z účtov neexistuje alebo suma nie je kladná,
      vyhodí výnimku so správou ZLE_DATA
    - Inak vytvorí nový záznam v tabuľke prevodov so zadanými údajmi, pričom hodnota
      v stĺpci REALIZOVANY nebude zadaná (NULL).
    Návratová hodnota metódy je ID vytvoreného záznamu.
    Poznamka. V tabuľke účtov táto metóda nerobí žiadne zmeny.
    */
    public static long zadajPrevod(EntityManager em, double suma, long zUctu, long naUcet) throws Exception {
        if (suma <= 0) {
            throw new Exception("ZLE_DATA");
        }
        UCET ucet = em.find(UCET.class,zUctu);
        if (ucet == null) {
            throw new Exception("ZLE_DATA");
        }
        UCET ucet2 = em.find(UCET.class,naUcet);
        if (ucet2 == null) {
            throw new Exception("ZLE_DATA");
        }

        PREVOD prevod = new PREVOD();
        prevod.setNaucet_id(ucet2);
        prevod.setZuctu_id(ucet);
        prevod.setSuma(suma);
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
    public static int realizujPrevody(EntityManager em) throws Exception {
        Query q = em.createNativeQuery ("SELECT * from PREVOD where REALIZOVANY IS NULL ", PREVOD.class);
        List<PREVOD> prevody =  q.getResultList();

        em.getTransaction().begin();
        int counter =0;
        for (PREVOD s: prevody){
            if (s.getZuctu_id().getStav()>s.getSuma()){
                s.getZuctu_id().setStav(s.getZuctu_id().getStav()-s.getSuma());
                s.getNaucet_id().setStav(s.getNaucet_id().getStav()+s.getSuma());
                s.setRealizovany(new Date());
                counter++;
            }
        }

        em.getTransaction().commit();
        return counter;
    }

}
