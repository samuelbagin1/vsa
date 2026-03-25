package vsa;

import java.util.List;
import jakarta.persistence.*;
import java.util.HashSet;

public class Application {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
    private static final EntityManager em = emf.createEntityManager();

    public static void main(String[] args)  {
        System.out.println("" + noveJedlo("P1", 33.3));
        System.out.println("" + noveJedlo("P2", null));
        System.out.println("" + noveJedlo("P3", null));
        // System.out.println("" + jedlaBezCeny().size());
        // odstranJedlaBezCeny();
    }
    
    public static Long noveJedlo(String nazov, Double cena) {
        if (nazov==null || nazov.isEmpty()) {
            return null;
        }
        
        Query q = em.createQuery("SELECT j FROM Jedlo j WHERE j.nazov = :nazov", Jedlo.class);
        q.setParameter("nazov", nazov);
        
        List<Jedlo> jedla = q.getResultList();
        Jedlo jedlo = !jedla.isEmpty() ? jedla.get(0) : null;
        
        if (jedlo == null) {
            em.getTransaction().begin();
            Jedlo j = new Jedlo();
            
            j.setCena(cena);
            j.setNazov(nazov);
            
            em.persist(j);
            
            em.getTransaction().commit();
            return j.getId();
            
        } else {
            return null;
        }
    }

    public static List<Jedlo> jedlaBezCeny() {
        Query q = em.createQuery("SELECT j FROM Jedlo j WHERE j.cena IS NULL", Jedlo.class);
        
        List<Jedlo> jedla = q.getResultList();
        return jedla;
    }

    public static void odstranJedlaBezCeny()  {
        Query q = em.createQuery("SELECT j FROM Jedlo j WHERE j.cena IS NULL", Jedlo.class);
        
        List<Jedlo> jedla = q.getResultList();
        
        em.getTransaction().begin();
        for (Jedlo j : jedla) {
            if (j.getCena() == null) em.remove(j);
        }
        em.getTransaction().commit();
    }
    
}
