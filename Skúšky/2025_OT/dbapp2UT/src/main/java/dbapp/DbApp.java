/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

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
       
    }

    /** Vrati zoznam vyucujucich predmetu so zadanym kodom.
     * 
     * @param em            entity manager
     * @param kodPredmetu   kod predmetu
     * 
     * Ak kod nie je zadany alebo predmet s danym kodom neexistuje vrati null.
     */
    public static Set<Ucitel> vyucujuci(EntityManager em, String kodPredmetu) throws Exception {

        throw new Exception("NEIMPLEMENTOVANE");
    }

    /** Vrati pocet predmetov, ktore vyucuje ucitel so zadanym menom. 
     *      
     * @param em:   entity manager
     * @param meno  meno ucitela
     * 
     * Ak meno nie je zadane alebo ucitel s danym menom neexistuje vrati 0.
     * Metoda sa moze spolahnut na to, ze v DB je meno ucitela jedinecne
     */
    public static int pocetPredmetov(EntityManager em, String meno) throws Exception {
        throw new Exception("NEIMPLEMENTOVANE");
    }

    /** prida predmetu vyucujuceho.
     *
     * @param em:           entity manager
     * @param meno          meno ucitela
     * @param kodPredmetu   kod predmetu
     *
     * Metoda vyhlada ucitela podla mena a predmet podla kodu. 
     * Ak ucitel s danym menom v DB neexistuje vytvori ho, pricom 
     *   datum narodenia nebude zadany (ostane null) 
     * Ak predmet s danym kodom neexistuje, vytvori ho, pricom odbor bude "BIS". 
     * Nasledne zaradi ucitela medzi vyucujucich predmetu. 
     *
     * Ak meno alebo kodPredmetu nie su zadane vrati false inak vrati true. 
     * V pripade inej chyby vyhodi vynimku.
     */
    public static boolean pridajVyucujuceho(EntityManager em, String kodPredmetu, String meno) throws Exception {
        throw new Exception("NEIMPLEMENTOVANE");
    }

    // 1. ucitel ani predmet neexistuje: Vybavene
    // 2. existuje ucitel ale neexistuje predmet: Vybavene
    // 3. neexistuje ucitel ale existuje predmet: 

}
