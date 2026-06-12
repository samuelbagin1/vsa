/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author igor
 */
public class DbApp {
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
    static EntityManager em = emf.createEntityManager();

    /**
     * @param args the command line arguments
     *
     * Len pre vase otestovanie. Mozete si upravit.
     */
    public static void main(String[] args) throws Exception {
//        persist(new Osoba());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();

        novyPredmet(em, "Hrach", "OOP", Odbor.ME );
        novyPredmet(em, "Mrkva", "VSA", Odbor.ME);
        novyPredmet(em, "Mrkva", "ASOS", Odbor.ME);

        System.out.println("Mrkvov uvazok: " + pocetPrednasok(em, "Mrkva"));    // vypise 2  

        Osoba vyuc = vyucujuci(em, "VSA");
        System.out.println("Prednasajuci VSA: " + vyuc.getMeno());              // vypise Mrkva

    }

    /* Vrati osobu prednasajuceho predmetu so zadanym kodom
     * Ak kod nie je zadany alebo predmet s danym kodom neexistuje vrati null.
     */
    public static Osoba vyucujuci(EntityManager em, String kodPredmetu) throws Exception {
        if (kodPredmetu == null || "".equals(kodPredmetu)) {
            return null;
        }
        
        Predmet p = em.find(Predmet.class, kodPredmetu);
        
        if (p == null) {
            return null;
        }
        
        return p.getPrednasajuci();
    }

    /* Vrati pocet predmetov, ktore prednasa osoba so zadanym menom prednasajuceho. 
     * Ak menoPrednasajuceho nie je zadane alebo osoba s danym menom neexistuje vrati 0.
     * Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     */
    public static int pocetPrednasok(EntityManager em, String menoPrednasajuceho) throws Exception {
        if (menoPrednasajuceho == null || "".equals(menoPrednasajuceho)) {
            return 0;
        }
        
        Osoba o  = em.createQuery("SELECT o FROM Osoba o WHERE o.meno = :meno", Osoba.class).setParameter("meno", menoPrednasajuceho).getResultList().stream().findFirst().orElse(null);
        if (o == null) {
            return 0;
        }
        
        if (o.getPrednasky() == null) {
            return 0;
        }
        
        return o.getPrednasky().size();
    }

    /* Vytvori novy predmet.
     *
     * @param em                    entity manager
     * @param menoPrednasajuceho    meno prednasajuceho predmet
     * @param kodPredmetu           kod predmetu
     * @param odbor                 odbor 
     *
     * Metoda naprv zisti ci predmet s danym kodom uz neexistuje.
     * Ak existuje nerobi nic viac a vrati null.
     * Ak predmet s danym kodom neexistuje, vytvori novy predmet a vlozi ho do DB, pricom:
     *   Odbor nastavi ho ako odbor predmetu.
     *   Ak je meno prednasajuceho zadane, vyhlada osobu s danym menom a 
     *     nastavi tuto osobu ako prednasajuceho noveho predmetu. 
     *   Ak osoba v DB neexistuje vytvori ju a nastavi ako prednasajuceho.
     *   Pozn. ak meno nebolo zadane, prednasajuci ostane null.   
     *   Pozn. metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne.
     *
     * Navratova hodnota:   
     *   Novovytvoreny objekt predmetu 
     *   null, ak predmet uz existoval alebo kod predmetu nebol zadany.
     */
    // Varianty: odbor, semester, rocnik, poradie
    public static Predmet novyPredmet(EntityManager em, String menoPrednasajuceho, String kodPredmetu, Odbor odbor) throws Exception {
        if (kodPredmetu == null || "".equals(kodPredmetu)) {
            return null;
        }
        
        em.getTransaction().begin();
        
        Predmet p  = em.find(Predmet.class, kodPredmetu);
        if (p == null) {
            p = new Predmet();
            p.setOdbor(odbor);
            p.setKod(kodPredmetu);
            
            if (menoPrednasajuceho != null && !"".equals(menoPrednasajuceho)) {
                Osoba o  = em.createQuery("SELECT o FROM Osoba o WHERE o.meno = :meno", Osoba.class).setParameter("meno", menoPrednasajuceho).getResultList().stream().findFirst().orElse(null);
                if (o != null) {
                    p.setPrednasajuci(o);
                }
                else {
                    o = new Osoba();
                    o.setMeno(menoPrednasajuceho);
                    p.setPrednasajuci(o);
                }
                
                o.getPrednasky().add(p);
                
                em.persist(o);
            }
            
        } else {
            return null;
        }
        
        em.persist(p);
        
        em.getTransaction().commit();
        
        return p;
    }

    public static void persist(Object object) {
        
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    
    

}
