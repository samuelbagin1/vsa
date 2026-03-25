/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package dbapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author edu
 */
public class Application {

    public static void main(String[] args) {

        System.out.println(novaKniha("Pipik"));
        //novaKniha("Pipik2");
        //novaKniha("Pipik3");
        //novaKniha("Pipik4");
        try {
            // Debugging output to ensure method is called
            System.out.println("Attempting to check availability for the book...");
            Dostupnost ret = zistiDostupnost("Pipik");
            System.out.println("ret = " + ret);
        } catch (Exception e) {
            // Output error if the exception is thrown
            System.out.println("Exception: " + e.getMessage());

        }
        //upravCeny();
    }

    public static Long novaKniha(String titul) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM kniha", Kniha.class);
        List<Kniha> knihy = q.getResultList();

        for (Kniha k : knihy) {
            if (k.getTitul().equals(titul)) {
                return null;
            }
        }

        Kniha k = new Kniha();
        k.setTitul(titul);

        em.persist(k);

        em.getTransaction().commit();

        em.close();

        return k.getId();
    }

    public static Dostupnost zistiDostupnost(String titul) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT k FROM Kniha k WHERE k.titul = :titul", Kniha.class);
        q.setParameter("titul", titul);
        
        Kniha k = (Kniha) q.getResultList().getFirst();

        Kniha kniha = (Kniha) q.getResultList().stream().findFirst().orElse(null);

        if (kniha != null) {
            em.close();
            return kniha.getDostupnost();
        } else {
            em.close();
            throw new Exception("neznamy titul");
        }
    }

    public static void upravCeny() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query q = em.createQuery("SELECT k FROM Kniha k", Kniha.class);
        List<Kniha> knihy = q.getResultList();

        for (Kniha k : knihy) {
            if (k.getCena() != null) {  
                k.setCena(k.getCena() * 0.8); 
                em.merge(k); 
            }
        }
        
        em.getTransaction().commit();
        em.close();
    }

}
