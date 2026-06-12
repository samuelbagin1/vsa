/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author igor
 */
public class DbApp {

    /**
     * @param args the command line arguments
     *
     * Len pre vase otestovanie. Mozete si upravit.
     */
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
        throw new Exception("NEIMPLEMENTOVANE");
    }

    /* Vrati pocet predmetov, ktore prednasa osoba so zadanym menom. 
     * Ak meno nie je zadane alebo osoba s danym menom neexistuje vrati 0.
     * Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     */
    public static int pocetPrednasok(EntityManager em, String meno) throws Exception {
        throw new Exception("NEIMPLEMENTOVANE");
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
        throw new Exception("NEIMPLEMENTOVANE");
    }

}
