/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import entities.Docent;
import entities.Person;
import entities.Predmet;
import entities.Ucitel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author edu
 */
public class NewMain {

    // Ilustruje generovany kluc
    public static void main(String[] args) {
//        Person p;
//        p = new Person();
//        p.setName("Fero");
//        p.setBorn(toDate(1985, 10, 20));
//        p.setMarried(true);
//        
//        //p.setId(9L);
//        persist(p);

        Predmet p1 = new Predmet();
        Ucitel u1 = new Ucitel();
        Docent d = new Docent();
        
        u1.setMeno("Igorko");
        d.setMeno("Marček");
        p1.setNazov("VSA");
        p1.setPrednasajuci(d);
        List<Predmet> predmety = new ArrayList<>();
        predmety.add(p1);
        u1.setCvicenie(predmety);
        d.setPrednaska(predmety);
        
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(u1);
            em.persist(d);
            em.persist(p1);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
           
        }   
        
        
        em.close();
        emf.close();
    }
    
    public static Predmet novyPredmet(EntityManager em, String nazov, String meno) throws Exception {
       
        // ak nie je zadany nazov predmetu
        if(nazov == null) {
            throw new Exception("chyba");
        }
        
        else {
            Predmet p = em.find(Predmet.class, nazov);
            //ak predmet s danym nazvom existuje
            if(p != null) {
                throw new Exception("duplicita");
            }
            
            else {
                p = new Predmet();
                p.setNazov(nazov);
                
                if(meno == null) {
                    p.setCviciaci(null);
                    p.setPrednasajuci(null);
                    em.getTransaction().begin();
                    try {
                        em.persist(p);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        em.getTransaction().rollback();
                    } finally {

                    }
                    return p;
                }
                
                else {
//                    Query q = em.createQuery("select d from Docent d where d.meno = :m");
//                    q.setParameter("m", meno);
                    
                    Docent d = new Docent();
                    d.setMeno(meno);
                    List<Predmet> predmety = new ArrayList<>();
                    predmety.add(p);
                    d.setCvicenie(predmety);
                    d.setPrednaska(predmety);
                    List<Ucitel> cviciaci = new ArrayList<>();
                    cviciaci.add((Ucitel) d);
                    p.setCviciaci(cviciaci);
                    p.setPrednasajuci(d);
                    em.getTransaction().begin();
                    try {
                        em.persist(p);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        em.getTransaction().rollback();
                    } finally {
                         
                    }
                    return p;
                }
            }
        }
    }

//    private static void persist(Object object) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        try {
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        } finally {
//        TypedQuery<Person> q = em.createNamedQuery("Person.findAll", Person.class);
//        List<Person> pl = q.getResultList();
//        for (Person p1 : pl) {
//          System.out.println(p1.getName() + " narodeny=" + p1.getBorn());
//        }
//            em.close();
//        }
//    }
//    
//    private static LocalDate toDate(int y, int m, int d) {
//        return LocalDate.of(y, m, d);
//    }
}
