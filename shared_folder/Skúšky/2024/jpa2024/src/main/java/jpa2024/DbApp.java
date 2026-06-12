/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author ubuntu
 */
public class DbApp {
        public static void main(String[] args) throws Exception {
//        persist(new Osoba());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();

        novyPredmet(em, "OOP", Odbor.AI, "Hrach" );
        novyPredmet(em, "VSA", Odbor.AI, "Mrkva");
        novyPredmet(em, "ASOS", Odbor.AI, "Mrkva");

        System.out.println("Mrkvov uvazok: " + pocetPrednasok(em, "Mrkva"));    // vypise 2  

        Osoba vyuc = prednasajuci(em, "VSA");
        System.out.println("Prednasajuci VSA: " + vyuc.getMeno());              // vypise Mrkva
    }

    /* Vrati osobu prednasajuceho predmetu so zadanym kodom
     * Ak kod nie je zadany alebo predmet s danym kodom neexistuje vrati null.
     */
    public static Osoba prednasajuci(EntityManager em, String kodPredmetu) throws Exception {
        
        if (kodPredmetu == null || kodPredmetu.isEmpty()) {
            return null;
        }
        
        Predmet predmet = em.find(Predmet.class, kodPredmetu);
        if (predmet == null) {
            return null;
        }
        
        Osoba osoba = predmet.getOsoby().get(0);
        return osoba;
    }

    /* Vrati pocet predmetov, ktore prednasa osoba so zadanym menom. 
     * Ak meno nie je zadane alebo osoba s danym menom neexistuje vrati 0.
     * Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     */
    public static int pocetPrednasok(EntityManager em, String meno) throws Exception {
        if (meno == null || meno.isEmpty()) {
            return 0;
        }
        
        List<Osoba> osoby = em.createQuery("SELECT o FROM Osoba o WHERE o.meno = :meno", Osoba.class).setParameter("meno", meno).getResultList();
        Osoba osoba = osoby.isEmpty() ? null : osoby.get(0);
        
        if (osoba == null) {
            return 0;
        }
        
        int pocet = osoba.getPredmety().size();
        return pocet;
    }

    /* Vytvori novy predmet.
     *
     * @param em            entity manager
     * @param kodPredmetu   kod predmetu
     * @param odbor         odbor 
     * @param meno          meno prednasajuceho profesora
     *
     * Metoda naprv zisti ci predmet s danym kodom uz neexistuje.
     * Ak existuje nerobi nic viac a vrati null.
     * Ak predmet s danym kodom neexistuje, vytvori novy predmet a vlozi ho do DB, pricom:
     *   Odbor nastavi ho ako odbor predmetu.
     *   Ak je meno prednasajuceho zadane, vyhlada osobu s danym menom a 
     *     nastavi tuto osobu ako profesora, ktory prednasa novy predmet. 
     *   Ak osoba v DB neexistuje vytvori ju a nastavi ako profesora.
     *   Pozn. ak meno nebolo zadane, prednasajuci profesor ostane null.   
     *   Pozn. metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne.
     *
     * Navratova hodnota:   
     *   Novovytvoreny objekt predmetu 
     *   null, ak predmet uz existoval alebo kod predmetu nebol zadany.
     */
    public static Predmet novyPredmet(EntityManager em, String kodPredmetu, Odbor odbor, String meno) throws Exception {
        if (kodPredmetu == null) {
            return null;
        }
        
        Predmet predmet = em.find(Predmet.class, kodPredmetu);
        if (predmet != null) {
            return null;
        }
        
        em.getTransaction().begin();
        predmet = new Predmet();
        predmet.setOdbor(odbor);
        predmet.setKod(kodPredmetu);
        predmet.setOsoby(new ArrayList<>());
        if (meno != null && !meno.isEmpty()) {
            List<Osoba> osoby = em.createQuery("SELECT o FROM Osoba o WHERE o.meno = :meno", Osoba.class).setParameter("meno", meno).getResultList();
            Osoba prednasajuci = osoby.isEmpty() ? null : osoby.get(0);
            
            if (prednasajuci == null) {
                prednasajuci = new Osoba();
                prednasajuci.setMeno(meno);
            }
            
            if (prednasajuci.getPredmety() == null) {
                prednasajuci.setPredmety(new ArrayList<>());
            }
            
            prednasajuci.getPredmety().add(predmet);
            predmet.getOsoby().add(prednasajuci);
            em.persist(prednasajuci);
        }
        em.persist(predmet);
        em.getTransaction().commit();
        
        return predmet;
    }
}
