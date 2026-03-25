package vsa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Map;

public class Zapocet {

    public static void main(String[] args) {
        // placeholder pre NetBeans Run Project
    }

    public void aktualizujKnihu(String isbn, String nazov, Double cena) throws Exception {
        if (isbn == null || isbn.isEmpty()) {
            throw new Exception("chyba");
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            KnihaZAPOCET k = em.find(KnihaZAPOCET.class, isbn);

            if (k == null) {
                k = new KnihaZAPOCET();
                k.setIsbn(isbn);
                k.setNazov(nazov);
                k.setCena(cena);
                em.persist(k);
            } else {
                if (nazov != null) k.setNazov(nazov);
                if (cena != null) k.setCena(cena);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public int aktualizujCeny(Map<String, Double> cennik) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();

        int count = 0;
        try {
            em.getTransaction().begin();

            for (Map.Entry<String, Double> entry : cennik.entrySet()) {
                KnihaZAPOCET k = em.find(KnihaZAPOCET.class, entry.getKey());
                if (k != null) {
                    k.setCena(entry.getValue());
                    count++;
                }
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }

        return count;
    }
}
