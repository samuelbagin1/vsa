/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banka;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ubuntu
 */
public class Dbapp {
    
    private static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        em.close();
    }
    
    
    /* Vytvorí nový (zatiaľ nezrealizovaný) prevod medzi dvoma účtami.
    *
    * @param em    entity manager
    * @param suma  suma, ktorá sa má previesť
    * @param z     ID účtu, z ktorého sa prevádza  (uloží sa do Z_ID)
    * @param na    ID účtu, na ktorý sa prevádza   (uloží sa do NA_ID)
    *
    * Metóda vytvorí v DB nový záznam prevodu so zadanými údajmi, pričom:
    *   SUMA        nastaví na zadanú sumu,
    *   Z_ID        nastaví na zdrojový účet (z),
    *   NA_ID       nastaví na cieľový účet (na),
    *   CAS_PREVODU ostane NULL — prevod je vytvorený, ale ešte NIE je zrealizovaný.
    *   ID prevodu je autogenerované databázou.
    *
    * Kontrola vstupných údajov:
    *   - suma musí byť kladná (> 0),
    *   - oba účty (z aj na) musia v DB existovať.
    *   Zostatok na účte sa pri vytváraní prevodu NEkontroluje
    *   (to je úlohou metódy realizujPrevody).
    *
    * Ak je niektorý z údajov chybný (záporná suma alebo neexistujúci účet),
    * metóda vyhodí výnimku, ktorej správa začína reťazcom "Chybne udaje",
    * a v databáze nesmie vzniknúť žiadny prevod ani iná zmena.
    *
    * Návratová hodnota:
    *   autogenerovaný kľúč (ID) novovytvoreného prevodu — kladné číslo (> 0).
    */
    public static long novyPrevod(EntityManager em, double suma, long zUctu, long naUcet) throws Exception {
        Ucet z_uctu = em.find(Ucet.class, zUctu);
        Ucet na_ucet = em.find(Ucet.class, naUcet);
        if (suma <= 0 || z_uctu == null || na_ucet == null) {
            throw new Exception("Chybne udaje");
        }
        
        Prevod prevod = new Prevod();
        
        prevod.setSuma(suma);
        prevod.setZ_id(z_uctu);
        prevod.setNa_id(na_ucet);
        prevod.setCas_prevodu(null);
        
        em.getTransaction().begin();
        em.persist(prevod);
        em.getTransaction().commit();
        
        return prevod.getId();
    }
    
    public static int realizujPrevody(EntityManager em) {
        List<Prevod> prevody = em.createQuery("SELECT p FROM Prevod p WHERE p.cas_prevodu IS NULL", Prevod.class).getResultList();
        if (prevody == null || prevody.isEmpty()) {
            return 0;
        }
        
        em.getTransaction().begin();
        int pocet = 0;
        for (Prevod p : prevody) {
            if (p.getZ_id().getStav() >= p.getSuma()) {
                p.getZ_id().setStav(p.getZ_id().getStav() - p.getSuma());
                p.getNa_id().setStav(p.getNa_id().getStav() + p.getSuma());
                p.setCas_prevodu(new Date());
                pocet++;
            }
        }
        em.getTransaction().commit();
        return pocet;
    }
}
